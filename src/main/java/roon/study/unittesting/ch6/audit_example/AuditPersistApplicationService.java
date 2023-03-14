package roon.study.unittesting.ch6.audit_example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class AuditPersistApplicationService {
    private String directoryName;
    private AuditManager auditManager;
    private Persister persister;

    public AuditPersistApplicationService(@Value("${ch6.audit.directoryName}") String directoryName, AuditManager auditManager, Persister persister) {
        this.directoryName = directoryName;
        this.auditManager = auditManager;
        this.persister = persister;
    }

    public void addRecord(String visitorName, LocalDateTime timeOfVisit) throws IOException {
        List<FileContent> fileContents = persister.readDirectory(directoryName);
        FileUpdate updateCommand = auditManager.addRecord(fileContents, visitorName, timeOfVisit);
        persister.applyUpdate(updateCommand.getFilename(), updateCommand.getContents());
    }
}
