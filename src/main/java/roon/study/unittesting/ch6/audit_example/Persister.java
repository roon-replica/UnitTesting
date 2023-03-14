package roon.study.unittesting.ch6.audit_example;

import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Component
public class Persister {

    private AuditProperties auditProperties;
    private FileInterface fileInterface;

    public Persister(AuditProperties auditProperties, FileInterface fileInterface) {
        this.auditProperties = auditProperties;
        this.fileInterface = fileInterface;

        Path directoryPath = Path.of("./" + auditProperties.getDirectoryName());
        fileInterface.mkdir(directoryPath);
    }

    public List<FileContent> readDirectory(String directoryName) throws IOException {
        Path directoryPath = Path.of(directoryName);
        var filePaths = Files.list(directoryPath);
        //File firstFile = new File(String.valueOf(filePath));

        return filePaths.map(path -> {
                    FileContent ret = null;
                    try {
                        ret = new FileContent(path.getFileName().toString(), (int) Files.lines(path).count());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return ret;
                })
                .toList();
    }

    public void applyUpdate(String filename, String contents) throws IOException {
        FileWriter fileWriter = new FileWriter(filename, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        System.out.println(contents);

        bufferedWriter.write(contents);
        bufferedWriter.newLine();
        bufferedWriter.flush();
        bufferedWriter.close();
    }
}
