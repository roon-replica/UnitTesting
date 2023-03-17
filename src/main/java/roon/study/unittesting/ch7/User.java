package roon.study.unittesting.ch7;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class User {
    private int userId;
    private String email;
    private UserType type;

    public int changeEmail(String newEmail, String companyEmailDomain, int existingEmployeesCount) {
        if (email.equals(newEmail)) return existingEmployeesCount;

        String newEmailDomain = newEmail.split("@")[1];
        boolean isEmailCorporate = newEmailDomain.equals(companyEmailDomain);
        UserType newUserType = isEmailCorporate ? UserType.Employee : UserType.Customer;

        int employeesCount = existingEmployeesCount;
        if (!type.equals(newUserType)) {
            int delta = newUserType.equals(UserType.Employee) ? 1 : -1;
            int modifiedEmployeesCount = existingEmployeesCount + delta;
            employeesCount = modifiedEmployeesCount;
        }

        this.email = newEmail;
        this.type = newUserType;

        return employeesCount;

    }
}
