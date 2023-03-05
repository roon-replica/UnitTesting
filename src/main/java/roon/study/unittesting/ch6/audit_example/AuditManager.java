package roon.study.unittesting.ch6.audit_example;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.Arrays;

@AllArgsConstructor
@NoArgsConstructor
@Component
public class AuditManager {

    @Autowired
    private AuditProperties auditProperties;

    public AuditManager(int maxEntriesPerFile, String directoryName) {
        this.auditProperties = new AuditProperties(maxEntriesPerFile, directoryName);
    }

    public void addRecord(String visitorName, LocalDateTime timeOfVisit) throws IOException {
        // dir exist?
        // get files. exist at least one?
        // get last
        // over max entries count?
        // create new file or else append

        // 디렉토리, 파일 생성: https://www.baeldung.com/java-io-file
        // 파일에 쓰기: https://www.baeldung.com/java-append-to-file

        File directory = new File("./" + auditProperties.getDirectoryName());
        if (!directory.isDirectory()) {
            directory.mkdir();
        }

        var sortedExistingFiles = Arrays.stream(directory.listFiles())
                .sorted(File::compareTo).toList();
        String contents = visitorName + " " + timeOfVisit.toString();

        if (sortedExistingFiles.isEmpty()) {
            String filename = "audit_1.txt";
            File firstFile = new File(directory, filename);
            firstFile.createNewFile();

            writeFile(firstFile.getAbsolutePath(), contents);

        } else {
            int fileCount = sortedExistingFiles.size();
            var lastFile = sortedExistingFiles.get(fileCount - 1);

            int fileLineCount = (int) Files.lines(lastFile.toPath()).count();

            if (fileLineCount >= auditProperties.getMaxEntriesPerFile()) {
                String filename = "audit_" + (fileCount + 1) + ".txt";
                File newFile = new File(directory, filename);
                newFile.createNewFile();

                writeFile(newFile.getAbsolutePath(), contents);
            } else {
                writeFile(lastFile.getAbsolutePath(), contents);
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
