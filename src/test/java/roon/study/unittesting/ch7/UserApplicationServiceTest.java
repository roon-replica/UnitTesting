package roon.study.unittesting.ch7;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserApplicationServiceTest {

    @Test
    public void changing_email_from_non_corporate_to_corporate() {
        var company = new Company("naver", "corp.com", 5000);
        var sut = new User(5001, "roon@naver.com", UserType.Customer);

        sut.changeEmail("new_roon@corp.com", company);

        assertEquals(company.getNumberOfEmployees(), 5000 + 1);
        assertEquals(sut.getEmail(), "new_roon@corp.com");
        assertEquals(sut.getType(), UserType.Employee);
    }




}