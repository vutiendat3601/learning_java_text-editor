package com.datvutech.texteditor.service.impl;

import com.datvutech.texteditor.model.impl.FileModelImpl;
import com.datvutech.texteditor.repository.FileRepository;
import com.datvutech.texteditor.repository.impl.FileRepositoryImpl;
import com.datvutech.texteditor.service.FileService;

public class FileServiceImpl implements FileService {

    private FileRepository fileRepo = new FileRepositoryImpl();

    @Override
    public FileModelImpl getFile(String fileName) {
        return fileRepo.findByFileName(fileName);
    }

    @Override
    public FileModelImpl saveFile(FileModelImpl file) {
        return fileRepo.saveFile(file);
    }
}
