package com.datvutech.texteditor.controller;

public interface MainController {
    /* ### Workspace ### */
    void changedCurrentFileData();

    void changedCaretPossition();

    void makeBufferedTextData();

    /* #################################### */

    /* ### Menu File ### */
    void newFile();

    void openFile();

    boolean saveFile();

    boolean saveFileAs();

    void exit();
    /* #################################### */

    /* ### Menu Edit ### */
    void undoWorkspace();

    void redoWorkspace();

    void cutText();

    void copyText();

    void pasteText();

    void findText();

    void replaceText();
    /* #################################### */

    /* ### Menu Help ### */
    void showAbout();
}
