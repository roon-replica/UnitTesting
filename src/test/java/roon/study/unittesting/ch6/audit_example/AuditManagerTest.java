package roon.study.unittesting.ch6.audit_example;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;

//@SpringBootTest
class AuditManagerTest {
//    @Autowired
//    AuditManager auditManager;

    @Test
    public void givenDirName_whenAddRecord_thenDirDeleted() throws IOException {
        String directoryName = "audit_results";
        FileInterface fileInterface = new FileInterfaceImpl();
        AuditManager auditManager = new AuditManager(1, directoryName, fileInterface);
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

    @Test
    public void A_new_file_is_created_when_the_current_file_overflows() throws IOException {
        Path directoryPath = Path.of("./audit_results");
        var fileSystemMock = mock(FileInterface.class);
        when(fileSystemMock.getAllFilePaths(directoryPath)).thenReturn(
                Stream.of(
                        Path.of("./audit_results/audit_1.txt"),
                        Path.of("./audit_results/audit_2.txt")
                )
        );

        final int maxLinesPerFile = 3;
        Path lastFilePath = Path.of("./audit_results/audit_2.txt");
        when(fileSystemMock.getLineCount(lastFilePath)).thenReturn(maxLinesPerFile);

        AuditManager sut = new AuditManager(maxLinesPerFile, "audit_results", fileSystemMock);
        String visitorName = "roon";
        LocalDateTime now = LocalDateTime.now();
        sut.addRecord(visitorName, now);

        Path nextFilePath = Path.of("./audit_results/audit_3.txt");
        verify(fileSystemMock).writeToFile(nextFilePath, visitorName + " " + now);
    }

}