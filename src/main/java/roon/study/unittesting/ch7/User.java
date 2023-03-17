package roon.study.unittesting.ch7;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class User {
    private int userId;
    private String email;
    private UserType type;

    public void changeEmail(String newEmail, Company company) {
        if (email.equals(newEmail)) return;

        UserType newUserType = company.isEmailCorporate(newEmail) ? UserType.Employee : UserType.Customer;

        if (!type.equals(newUserType)) {
            int delta = newUserType.equals(UserType.Employee) ? 1 : -1;
            company.changeNumberOfEmployees(delta);
        }

        this.email = newEmail;
        this.type = newUserType;
    }
}
