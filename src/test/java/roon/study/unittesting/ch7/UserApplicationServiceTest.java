package roon.study.unittesting.ch7;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserApplicationServiceTest {

    @Test
    public void changing_email_from_non_corporate_to_corporate() {
        var company = new Company("naver", "corp.com", 5000);
        var sut = new User(1, "roon@naver.com", UserType.Customer);

        sut.changeEmail("new_roon@corp.com", company);

        assertEquals(company.getNumberOfEmployees(), 5000 + 1);
        assertEquals(sut.getEmail(), "new_roon@corp.com");
        assertEquals(sut.getType(), UserType.Employee);
    }

    // (email, userType) 변경
    // email: corp -> non corp
    // email: non corp -> corp
    // email: change email not changing userType
    @Test
    public void change_email_from_corporate_to_non_corporate() {
        var company = new Company("naver", "corp.com", 5000);
        var sut = new User(1, "roon@corp.com", UserType.Employee);

        sut.changeEmail("new_roon@non_corp.com", company);

        assertEquals(company.getNumberOfEmployees(), 5000 - 1);
        assertEquals(sut.getEmail(), "new_roon@non_corp.com");
        assertEquals(sut.getType(), UserType.Customer);
    }

    private static List<Arguments> changingEmailOnly() {
        return List.of(
                Arguments.of("email@naver.com", UserType.Customer),
                Arguments.of("email2@corp.com", UserType.Employee)
        );
    }

    @MethodSource("changingEmailOnly")
    @ParameterizedTest
    public void change_email_only_not_changing_user_type(String newEmail, UserType type){
        var company = new Company("naver", "corp.com", 5000);
        var sut = new User(1, newEmail, type);

        sut.changeEmail(newEmail, company);

        assertEquals(sut.getType(), type);
    }


}