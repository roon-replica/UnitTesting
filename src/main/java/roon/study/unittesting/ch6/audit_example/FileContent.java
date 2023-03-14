package roon.study.unittesting.ch6.audit_example;

import lombok.Getter;

@Getter
public class FileContent {
    private String filename;
    private int fileLineCount;

    public FileContent(String filename, int fileLineCount) {
        this.filename = filename;
        this.fileLineCount = fileLineCount;
    }
}
