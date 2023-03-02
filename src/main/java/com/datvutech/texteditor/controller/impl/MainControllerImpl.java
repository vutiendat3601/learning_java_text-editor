package com.datvutech.texteditor.controller.impl;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

import com.datvutech.texteditor.controller.MainController;
import com.datvutech.texteditor.model.UndoRedoModel;
import com.datvutech.texteditor.model.FileModel;
import com.datvutech.texteditor.model.impl.FileModelImpl;
import com.datvutech.texteditor.model.impl.UndoRedoModelImpl;
import com.datvutech.texteditor.service.FileService;
import com.datvutech.texteditor.service.impl.FileServiceImpl;
import com.datvutech.texteditor.util.Stopwatch;
import com.datvutech.texteditor.util.TextUtils;
import com.datvutech.texteditor.view.MainView;
import com.datvutech.texteditor.view.MainViewImpl;

public class MainControllerImpl implements MainController {
    private FileModel currentFile;
    private UndoRedoModel bufferedTextData;
    private MainView mainView;
    private Stopwatch stopwatch = new Stopwatch();
    private FileService fileService = new FileServiceImpl();

    public MainControllerImpl(FileModel currentFile) {
        this.currentFile = currentFile;
        bufferedTextData = new UndoRedoModelImpl();
        mainView = new MainViewImpl(this, currentFile);
    }
    /* ### Workspace ### */

    @Override
    public void changedCurrentFileData() {
        String textData = mainView.getWorkspaceTextData();
        bufferedTextData.setTextSnapshot(textData);
        currentFile.transformToUnsaved();
        mainView.reloadSavedStateStatusbar();
    }

    @Override
    public void makeBufferedTextData() {
        stopwatch.stopAndResetNow();
        long numOfSeconds = stopwatch.getSeconds();
        if (numOfSeconds > 0.5) {
            bufferedTextData.addUndoText();
        }
    }

    @Override
    public void changedCaretPossition() {
        mainView.reloadCaretPossitionStatusbar();
    }
    /* #################################### */

    /* ### Menu File ### */
    @Override
    public void newFile() {
        if (!currentFile.isSaved()) {
            boolean continuous = saveFileFirst("create new file");
            if (!continuous) {
                return;
            }
        }
        currentFile.transformToBlank();
        mainView.reloadWorkspace();
    }

    @Override
    public void openFile() {
        if (!currentFile.isSaved()) {
            boolean continuous = saveFileFirst("open file");
            if (!continuous) {
                return;
            }
        }
        String fileName = mainView.showOpenFileDialog("Open file ...");
        if (!TextUtils.isNullOrBlank(fileName)) {
            FileModelImpl fileResp = fileService.getFile(fileName);
            if (fileResp.isPersistent()) {
                currentFile.transformState(fileResp);
                mainView.reloadWorkspace();
            } else {
                mainView.showErrorDialog("File can't open", "Can't open the file");
            }
        }
    }

    @Override
    public boolean saveFile() {
        if (currentFile.isSaved()) {
            return true;
        }
        boolean result = false;
        String textData = mainView.getWorkspaceTextData();
        String fileName = currentFile.getFileName();
        FileModelImpl fileReq = new FileModelImpl();

        // Check if current file has been persisted before (had fileName)
        boolean persistent = currentFile.isPersistent();
        if (persistent) {
            fileReq.prepareBeforeSaving(fileName, textData);
            FileModelImpl fileResp = fileService.saveFile(fileReq);
            result = fileResp.isPersistent();
            currentFile.transformState(fileResp);
            mainView.setTitle(currentFile.getFileName() + ", saved - Text Editor");
        } else {
            result = saveFileAs();
        }
        return result;
    }

    @Override
    public boolean saveFileAs() {
        boolean result = false;

        // User choose location to saved
        String textData = mainView.getWorkspaceTextData();
        String fileName = mainView.showSaveFileDialog("Save as ...");
        if (!TextUtils.isNullOrBlank(fileName)) {
            // Location is ok
            FileModelImpl fileReq = new FileModelImpl();
            fileReq.prepareBeforeSaving(fileName, textData);
            FileModelImpl fileResp = fileService.saveFile(fileReq);
            if (fileResp.isPersistent()) {
                result = true;
            } else {
                mainView.showErrorDialog("File not saved", "File can't be saved, please try again");
            }
        }
        return result;
    }

    @Override
    public void exit() {
        System.exit(0);
    }

    /* #################################### */

    /* ### Menu Edit ### */
    @Override
    public void undoWorkspace() {
        String text = bufferedTextData.undo();
        if (text != null) {
            currentFile.setTextData(text);
            mainView.reloadWorkspace();
        }
    }

    @Override
    public void redoWorkspace() {
        String text = bufferedTextData.redo();
        if (text != null) {
            currentFile.setTextData(text);
            mainView.reloadWorkspace();
        }
    }

    @Override
    public void cutText() {
        // TODO Auto-generated method stub
    }

    @Override
    public void copyText() {
        String text = mainView.getWorkspaceSelectedText();
        if (!text.equals("")) {
            System.out.println(text);
            Clipboard osClipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            osClipboard.setContents(new StringSelection(text), null);
        }
    }

    @Override
    public void pasteText() {
        mainView.pasteWorkspaceText();
    }

    @Override
    public void findText() {
        // TODO Auto-generated method stub
    }

    @Override
    public void replaceText() {
        // TODO Auto-generated method stub
    }
    /* #################################### */

    @Override
    public void showAbout() {
        // TODO Auto-generated method stub
    }

    /* Begin: Helper */
    private boolean saveFileFirst(String rootAction) {
        boolean accepted = mainView.showConfirmDialog("File not saved",
                "Current file is not saved, do you want to saved it now?");
        if (accepted) {
            boolean saved = saveFile();
            if (saved) {
                return true;
            } else {
                return mainView.showConfirmDialog("File not saved",
                        "Do you want to skip saving and " + rootAction + " anyway?");
            }
        }
        return true;
    }
    /* End: Helper */
}
