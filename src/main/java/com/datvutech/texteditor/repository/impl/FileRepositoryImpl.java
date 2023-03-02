package com.datvutech.texteditor.repository.impl;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.datvutech.texteditor.model.impl.FileModelImpl;
import com.datvutech.texteditor.repository.FileRepository;
import com.datvutech.texteditor.util.FileUtils;

public class FileRepositoryImpl implements FileRepository {
    @Override
    public FileModelImpl findByFileName(String fileName) {
        FileModelImpl fileResp = new FileModelImpl();
        Path path = Paths.get(fileName);
        if (Files.exists(path)) {
            fileName = path.toFile().getAbsolutePath();
            String name = path.toFile().getName();
            String textData = FileUtils.getTextData(fileName);
            String encoding = FileUtils.getEncoding(fileName);
            float sizeKb = FileUtils.getFileSizeKb(fileName);
            FileModelImpl tmpFile = new FileModelImpl(name, fileName, encoding, sizeKb, textData, true, true);
            fileResp.transformState(tmpFile);
        }
        return fileResp;
    }

    @Override
    public FileModelImpl saveFile(FileModelImpl fileReq) {
        FileModelImpl fileResp = fileReq;
        boolean persitent = FileUtils
                .saveNewOrOverrideTextFile(fileReq.getFileName(), fileReq.getTextData());
        if (persitent) {
            fileResp.transformToPersistent();
        }
        return fileResp;
    }

}
