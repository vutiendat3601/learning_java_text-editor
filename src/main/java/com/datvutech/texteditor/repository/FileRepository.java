package com.datvutech.texteditor.repository;

import com.datvutech.texteditor.model.impl.FileModelImpl;

public interface FileRepository {
    FileModelImpl findByFileName(String fileName);

    FileModelImpl saveFile(FileModelImpl file);
}
