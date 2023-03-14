package roon.study.unittesting.ch6.audit_example;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//@SpringBootTest
class AuditManagerTest {
//    @Autowired
//    AuditManager auditManager;

    @Test
    public void givenDirName_whenAddRecord_thenDirDeleted() throws IOException {
        String directoryName = "audit_results";
        AuditManager sut = new AuditManager(1);
        FileInterface fileInterface = new FileInterfaceImpl();

        Persister persister = new Persister(new AuditProperties(1, directoryName), fileInterface);

        var fileContents = Arrays.asList(
                new FileContent("audit_1.txt", 3),
                new FileContent("audit_2.txt", 3)
        );

        sut.addRecord(fileContents, "visitor1", LocalDateTime.now());

        deleteDirectory(directoryName);
    }

    private void deleteDirectory(String directoryName) {
        File directory = new File("./" + directoryName);
        File[] files = directory.listFiles();
        List<String> deletedFilePaths = new ArrayList<>();

        for (var file : files) {
            deletedFilePaths.add(file.getAbsolutePath());
            if (file.isDirectory()) deleteDirectory(directoryName);
            else file.delete();
        }
        boolean isDeleted = directory.delete();

        System.out.println("a directory and a file are deleted after create.");
        System.out.println(deletedFilePaths);
    }

    @Test
    public void A_new_file_is_created_when_the_current_file_overflows() throws IOException {
        Path directoryPath = Path.of("./audit_results");
        var fileContents = Arrays.asList(
                new FileContent("audit_1.txt", 3),
                new FileContent("audit_2.txt", 3)
        );

        final int maxLinesPerFile = 3;
        Path lastFilePath = Path.of("./audit_results/audit_2.txt");

        AuditManager sut = new AuditManager(maxLinesPerFile);
        LocalDateTime now = LocalDateTime.now();
        FileUpdate fileUpdateCommand = sut.addRecord(fileContents, "roon", now);

        assertEquals(fileUpdateCommand.getFilename(), "/audit_3.txt");
        assertTrue(fileUpdateCommand.getContents().contains(now.toString()));

    }

}