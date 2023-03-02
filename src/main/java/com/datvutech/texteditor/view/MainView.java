package com.datvutech.texteditor.view;

public interface MainView {
    void setTitle(String title);

    /* Begin: Dialog */
    String showSaveFileDialog(String title);

    String showOpenFileDialog(String title);

    void showErrorDialog(String title, String message);

    boolean showConfirmDialog(String title, String message);
    /* End: Dialog */

    /* Begin: Workspace */
    String getWorkspaceTextData();

    String getWorkspaceSelectedText();

    void pasteWorkspaceText();

    void reloadWorkspace();
    /* End: Workspace */

    /* Begin: Statusbar */
    void reloadStatusbar();

    void reloadCaretPossitionStatusbar();

    void reloadSavedStateStatusbar();

    void reloadLocationStatusbar();

    void reloadCharEncodingStatusbar();
    /* End: Statusbar */

}
