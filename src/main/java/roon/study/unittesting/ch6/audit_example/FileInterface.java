package roon.study.unittesting.ch6.audit_example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public interface FileInterface {
    default void mkdir(Path directoryPath) {
        File directory = new File(String.valueOf(directoryPath));
        if (!directory.isDirectory()) {
            directory.mkdir();
        }
    }

    default Stream<Path> getAllFilePaths(Path directoryPath) throws IOException {
        return Files.list(directoryPath);
    }

    default void writeToFile(Path filePath, String contents) throws IOException {
        File firstFile = new File(String.valueOf(filePath));
        firstFile.createNewFile();
        writeFile(firstFile.getAbsolutePath(), contents);
    }

    private void writeFile(String filePath, String contents) throws IOException {
        FileWriter fileWriter = new FileWriter(filePath, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        System.out.println(contents);

        bufferedWriter.write(contents);
        bufferedWriter.newLine();
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    default int getLineCount(Path filePath) throws IOException {
        return (int) Files.lines(filePath).count();
    }

}
