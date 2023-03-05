package roon.study.unittesting.ch6.audit_example;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//@SpringBootTest
class AuditManagerTest {
//    @Autowired
//    AuditManager auditManager;

    @Test
    public void givenDirName_whenAddRecord_thenDirDeleted() throws IOException {
        String directoryName = "audit_results";
        AuditManager auditManager = new AuditManager(1, directoryName);
        auditManager.addRecord("visitor1", LocalDateTime.now());

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

}