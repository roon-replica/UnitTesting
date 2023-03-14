package roon.study.unittesting.ch6.audit_example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDateTime;

@RestController
public class AuditController {

    private AuditPersistApplicationService applicationService;

    public AuditController(AuditPersistApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @GetMapping("/visit/{visitorName}")
    public String visit(@PathVariable String visitorName) throws IOException {
        applicationService.addRecord(visitorName,LocalDateTime.now());

        return visitorName + " " + LocalDateTime.now();
    }
}
