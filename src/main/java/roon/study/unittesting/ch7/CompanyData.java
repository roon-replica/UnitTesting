package roon.study.unittesting.ch7;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Getter
public class CompanyData {
    private String name;
    private String emailDomain;
    private int numberOfEmployees;
}
