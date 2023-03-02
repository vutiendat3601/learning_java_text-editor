package com.datvutech.texteditor.service;

import com.datvutech.texteditor.model.impl.FileModelImpl;

public interface FileService {
    public FileModelImpl getFile(String fileName);

    public FileModelImpl saveFile(FileModelImpl file);
}
