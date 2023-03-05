package roon.study.unittesting.ch6.audit_example;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Component
public class AuditManager {

    @Autowired
    private AuditProperties auditProperties;

    @Autowired
    private FileInterface fileInterface;

    public AuditManager(int maxEntriesPerFile, String directoryName, FileInterface fileInterface) {
        this.auditProperties = new AuditProperties(maxEntriesPerFile, directoryName);
        this.fileInterface = fileInterface;
    }

    public void addRecord(String visitorName, LocalDateTime timeOfVisit) throws IOException {
        // dir exist?
        // get files. exist at least one?
        // get last
        // over max entries count?
        // create new file or else append

        // 디렉토리, 파일 생성: https://www.baeldung.com/java-io-file
        // 파일에 쓰기: https://www.baeldung.com/java-append-to-file


        Path directoryPath = Path.of("./" + auditProperties.getDirectoryName());
        fileInterface.mkdir(directoryPath);

        var sortedExistingFilePaths = fileInterface.getAllFilePaths(directoryPath)
                .sorted().toList();

        String contents = visitorName + " " + timeOfVisit.toString();

        if (sortedExistingFilePaths.isEmpty()) {
            Path filePath = Path.of(directoryPath + "/audit_1.txt");
            fileInterface.writeToFile(filePath, contents);

        } else {
            int fileCount = sortedExistingFilePaths.size();
            var lastFilePath = sortedExistingFilePaths.get(fileCount - 1);

            int fileLineCount = fileInterface.getLineCount(lastFilePath);

            if (fileLineCount >= auditProperties.getMaxEntriesPerFile()) {
                Path filePath = Path.of(directoryPath + "/audit_" + (fileCount + 1) + ".txt");
                fileInterface.writeToFile(filePath, contents);
            } else {
                fileInterface.writeToFile(lastFilePath, contents);
            }
        }
    }

    private void writeFile(String filename, String contents) throws IOException {
        FileWriter fileWriter = new FileWriter(filename, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        System.out.println(contents);

        bufferedWriter.write(contents);
        bufferedWriter.newLine();
        bufferedWriter.flush();
        bufferedWriter.close();
    }
}
