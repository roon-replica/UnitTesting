package roon.study.unittesting.ch7;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Company {
    private String name;
    private String emailDomain;
    private int numberOfEmployees;

    public void changeNumberOfEmployees(int delta) {
        this.numberOfEmployees += delta;
    }

    public boolean isEmailCorporate(String email) {
        String emailDomain = email.split("@")[1];
        return emailDomain.equals(this.emailDomain);
    }
}
