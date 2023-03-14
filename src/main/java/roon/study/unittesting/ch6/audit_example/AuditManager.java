package roon.study.unittesting.ch6.audit_example;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@NoArgsConstructor
@Component
public class AuditManager {

    @Value("${ch6.audit.maxEntriesPerFile}")
    private int maxEntriesPerFile;

    public AuditManager(int maxEntriesPerFile) {
        this.maxEntriesPerFile = maxEntriesPerFile;
    }

    public FileUpdate addRecord(List<FileContent> fileContents, String visitorName, LocalDateTime timeOfVisit) {
        fileContents.sort(Comparator.comparing(FileContent::getFilename));
        var sortedExistingFilePaths = fileContents;

        String contents = visitorName + " " + timeOfVisit.toString();

        if (sortedExistingFilePaths.isEmpty()) {
            return new FileUpdate("audit_1.txt", contents);

        } else {
            int fileCount = sortedExistingFilePaths.size();
            int fileLineCount = sortedExistingFilePaths.get(fileCount - 1).getFileLineCount();

            if (fileLineCount >= maxEntriesPerFile) {
                return new FileUpdate("/audit_" + (fileCount + 1) + ".txt", contents);
            } else {
                return new FileUpdate("/audit_" + fileCount + ".txt", contents);
            }
        }
    }
}
