package com.datvutech.texteditor.model;

public interface FileModelAccess {
    String getName();

    String getFileName();

    float getSizeKb();

    String getEncoding();

    boolean isPersistent();

    boolean isSaved();

    String getTextData();
}
