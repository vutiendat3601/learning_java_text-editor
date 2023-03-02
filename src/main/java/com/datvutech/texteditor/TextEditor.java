package com.datvutech.texteditor;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.datvutech.texteditor.controller.MainController;
import com.datvutech.texteditor.controller.impl.MainControllerImpl;
import com.datvutech.texteditor.model.impl.FileModelImpl;

public class TextEditor {

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        FileModelImpl currentFile = new FileModelImpl();
        MainController mainController = new MainControllerImpl(currentFile);
    }
}
