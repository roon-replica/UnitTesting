package roon.study.unittesting.ch7;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CompanyStorage {
    private static Map<String, CompanyData> storage = new HashMap<>();

    public CompanyData getCompanyByName(String companyName) {
        return storage.get(companyName);
    }

    public void save(String companyName, CompanyData company) {
        storage.put(companyName, company);
    }
}