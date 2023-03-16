package roon.study.unittesting.ch7;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class User {
    private int userId;
    private String email;
    private UserType type;

    public void changeEmail(int userId, String newEmail, UserStorage userStorage, CompanyStorage companyStorage) {
        User user = userStorage.getUserById(userId); // 외부 협력자 1
        if (email.equals(newEmail)) return;

        String sampleCompanyName = "naver";
        CompanyData companyData = companyStorage.getCompanyByName(sampleCompanyName); // 외부 협력자 2

        String companyName = companyData.getName();
        String companyEmailDomain = companyData.getEmailDomain();
        int existingEmployeesCount = companyData.getNumberOfEmployees();

        String newEmailDomain = newEmail.split("@")[1];
        boolean isEmailCorporate = newEmailDomain.equals(companyEmailDomain);
        UserType newUserType = isEmailCorporate ? UserType.Employee : UserType.Customer; // 의사 결정 1

        if (!user.type.equals(newUserType)) {
            int delta = newUserType.equals(UserType.Employee) ? 1 : -1; // 의사 결정 2
            int modifiedEmployeesCount = existingEmployeesCount + delta;
            companyStorage.save(companyName, new CompanyData(companyName, newEmailDomain, modifiedEmployeesCount));
        }

        this.email = newEmail;
        this.type = newUserType;

        userStorage.save(userId, new User(userId, newEmail, newUserType));
        MessageBus.sendEmailChangedCommand(userId, newEmail); // 외부 협력자 3

    }
}
