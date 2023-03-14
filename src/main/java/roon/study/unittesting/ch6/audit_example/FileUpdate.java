package roon.study.unittesting.ch6.audit_example;

import lombok.Getter;

@Getter
public class FileUpdate {
    private String filename;
    private String contents;

    public FileUpdate(String filename, String contents) {
        this.filename = filename;
        this.contents = contents;
    }
}
