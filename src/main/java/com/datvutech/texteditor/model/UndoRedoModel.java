package com.datvutech.texteditor.model;

public interface UndoRedoModel {
    String redo();

    String undo();

    void addUndoText();

    void setTextSnapshot(String text);
}
