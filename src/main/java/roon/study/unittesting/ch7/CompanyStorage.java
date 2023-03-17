package roon.study.unittesting.ch7;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CompanyStorage {
    private static Map<String, Company> storage = new HashMap<>();

    public Company getCompanyByName(String companyName) {
        return storage.get(companyName);
    }

    public void save(String companyName, Company company) {
        storage.put(companyName, company);
    }
}