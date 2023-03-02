package com.datvutech.texteditor.model.impl;

import com.datvutech.texteditor.model.FileModel;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FileModelImpl implements FileModel {

    private String name;
    private String fileName;
    private float sizeKb;
    private String encoding;
    private boolean persistent;
    private boolean saved;
    private String textData;

    public FileModelImpl(String name, String fileName, String encoding,
            float sizeKb, String textData, boolean persistent, boolean saved) {
        this.name = name;
        this.fileName = fileName;
        this.encoding = encoding;
        this.sizeKb = sizeKb;
        this.textData = textData;
        this.persistent = persistent;
        this.saved = saved;
    }

    @Override
    public void prepareBeforeSaving(String fileName, String textData) {
        this.fileName = fileName;
        this.textData = textData;
    }

    @Override
    public void transformToPersistent() {
        persistent = true;
        saved = true;
    }

    @Override
    public void transformToUnsaved() {
        saved = false;
    }

    @Override
    public void transformToBlank() {
        name = "";
        fileName = "";
        sizeKb = 0F;
        encoding = "undefined";
        textData = "";
        persistent = false;
        saved = false;
    }

    @Override
    public void transformState(FileModel file) {
        saved = file.isSaved();
        persistent = file.isPersistent();
        name = file.getName();
        fileName = file.getFileName();
        textData = file.getTextData();
        encoding = file.getEncoding();
        sizeKb = file.getSizeKb();
    }

    @Override
    public void setTextData(String text) {
        textData = text;
    }

}
