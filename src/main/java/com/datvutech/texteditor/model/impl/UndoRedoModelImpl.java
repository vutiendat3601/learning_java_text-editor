package com.datvutech.texteditor.model.impl;

import java.util.Stack;

import com.datvutech.texteditor.model.UndoRedoModel;

public class UndoRedoModelImpl implements UndoRedoModel {
    private Stack<String> undoTexts = new Stack<>();
    private Stack<String> redoTexts = new Stack<>();
    private String textSnapshot;

    @Override
    public String redo() {
        if (undoTexts.empty() || !undoTexts.peek().equals(textSnapshot)) {
            undoTexts.push(textSnapshot);
        }
        textSnapshot = redoTexts.empty() ? null : redoTexts.pop();
        return textSnapshot;
    }

    @Override
    public String undo() {
        if (redoTexts.empty() || !redoTexts.peek().equals(textSnapshot)) {
            redoTexts.push(textSnapshot);
        }
        textSnapshot = undoTexts.empty() ? null : undoTexts.pop();
        return textSnapshot;
    }

    @Override
    public void addUndoText() {
        if (textSnapshot == null) {
            return;
        }
        if (undoTexts.empty() || !undoTexts.peek().equals(textSnapshot)) {
            undoTexts.push(textSnapshot);
        }
    }

    @Override
    public void setTextSnapshot(String text) {
        this.textSnapshot = text;
    }
}