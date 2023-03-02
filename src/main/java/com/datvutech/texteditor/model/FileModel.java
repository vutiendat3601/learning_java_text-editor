package com.datvutech.texteditor.model;

public interface FileModel extends FileModelAccess {
    void transformToUnsaved();

    void transformToBlank();

    void setTextData(String text);

    void transformState(FileModel file);

    void transformToPersistent();

    void prepareBeforeSaving(String fileName, String textData);
}
