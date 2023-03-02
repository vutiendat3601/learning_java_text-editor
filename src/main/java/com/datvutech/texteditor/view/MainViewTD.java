/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.datvutech.texteditor.view;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author vutie
 */
public class MainViewTD extends javax.swing.JFrame {

    /**
     * Creates new form MainView
     */
    public MainViewTD() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        wspace = new javax.swing.JPanel();
        wspace_Scroll = new javax.swing.JScrollPane();
        wspace_EditArea = new javax.swing.JTextArea();
        tlbar = new javax.swing.JToolBar();
        tlbar_New = new javax.swing.JButton();
        sttbar = new javax.swing.JPanel();
        sttbar_LnCol = new javax.swing.JLabel();
        sttbar_CharEncoding = new javax.swing.JLabel();
        sttbar_Saved = new javax.swing.JLabel();
        sttbar_Location = new javax.swing.JLabel();
        mnbar = new javax.swing.JMenuBar();
        mnbar_File = new javax.swing.JMenu();
        mnbar_File_New = new javax.swing.JMenuItem();
        mnbar_File_Separator1 = new javax.swing.JPopupMenu.Separator();
        mnbar_File_Open = new javax.swing.JMenuItem();
        mnbar_File_Separator2 = new javax.swing.JPopupMenu.Separator();
        mnbar_File_Save = new javax.swing.JMenuItem();
        mnbar_File_SaveAs = new javax.swing.JMenuItem();
        mnbar_File_Separator3 = new javax.swing.JPopupMenu.Separator();
        mnbar_File_Exit = new javax.swing.JMenuItem();
        mnbar_Edit = new javax.swing.JMenu();
        mnbar_Edit_Undo = new javax.swing.JMenuItem();
        mnbar_Edit_Redo = new javax.swing.JMenuItem();
        mnbar_Edit_Separator1 = new javax.swing.JPopupMenu.Separator();
        mnbar_Edit_Cut = new javax.swing.JMenuItem();
        mnbar_Edit_Copy = new javax.swing.JMenuItem();
        mnbar_Edit_Paste = new javax.swing.JMenuItem();
        mnbar_Edit_Separator2 = new javax.swing.JPopupMenu.Separator();
        mnbar_Edit_Find = new javax.swing.JMenuItem();
        mnbar_Edit_Replace = new javax.swing.JMenuItem();
        mnbar_Help = new javax.swing.JMenu();
        mnbar_Help_About = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        wspace.setLayout(new java.awt.BorderLayout());

        wspace_EditArea.setColumns(20);
        wspace_EditArea.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        wspace_EditArea.setRows(5);
        wspace_EditArea.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                wspace_EditAreaCaretUpdate(evt);
            }
        });
        wspace_EditArea.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                wspace_EditAreaMouseEntered(evt);
            }
        });
        wspace_EditArea.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
                wspace_EditAreaCaretPositionChanged(evt);
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
            }
        });
        wspace_Scroll.setViewportView(wspace_EditArea);

        wspace.add(wspace_Scroll, java.awt.BorderLayout.CENTER);

        getContentPane().add(wspace, java.awt.BorderLayout.CENTER);

        tlbar.setRollover(true);

        tlbar_New.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        tlbar_New.setText("New");
        tlbar_New.setToolTipText("Create new file");
        tlbar_New.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        tlbar_New.setFocusable(false);
        tlbar_New.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        tlbar_New.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        tlbar.add(tlbar_New);

        getContentPane().add(tlbar, java.awt.BorderLayout.NORTH);

        sttbar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        sttbar.setMinimumSize(new java.awt.Dimension(100, 32));
        sttbar.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        sttbar_LnCol.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        sttbar_LnCol.setText("Ln 1, Col 1");
        sttbar.add(sttbar_LnCol);

        sttbar_CharEncoding.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        sttbar_CharEncoding.setText("UTF-8");
        sttbar_CharEncoding.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        sttbar_CharEncoding.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        sttbar.add(sttbar_CharEncoding);

        sttbar_Saved.setText("Saved: YES");
        sttbar.add(sttbar_Saved);

        sttbar_Location.setText("Location: ");
        sttbar.add(sttbar_Location);

        getContentPane().add(sttbar, java.awt.BorderLayout.SOUTH);

        mnbar_File.setText("File");
        mnbar_File.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N

        mnbar_File_New.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mnbar_File_New.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        mnbar_File_New.setText("New File...");
        mnbar_File.add(mnbar_File_New);
        mnbar_File.add(mnbar_File_Separator1);

        mnbar_File_Open.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mnbar_File_Open.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        mnbar_File_Open.setText("Open File...");
        mnbar_File.add(mnbar_File_Open);
        mnbar_File.add(mnbar_File_Separator2);

        mnbar_File_Save.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mnbar_File_Save.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        mnbar_File_Save.setText("Save");
        mnbar_File.add(mnbar_File_Save);

        mnbar_File_SaveAs.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        mnbar_File_SaveAs.setText("Save As...");
        mnbar_File.add(mnbar_File_SaveAs);
        mnbar_File.add(mnbar_File_Separator3);

        mnbar_File_Exit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.ALT_DOWN_MASK));
        mnbar_File_Exit.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        mnbar_File_Exit.setText("Exit");
        mnbar_File.add(mnbar_File_Exit);

        mnbar.add(mnbar_File);

        mnbar_Edit.setText("Edit");
        mnbar_Edit.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N

        mnbar_Edit_Undo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mnbar_Edit_Undo.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        mnbar_Edit_Undo.setText("Undo");
        mnbar_Edit.add(mnbar_Edit_Undo);

        mnbar_Edit_Redo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mnbar_Edit_Redo.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        mnbar_Edit_Redo.setText("Redo");
        mnbar_Edit.add(mnbar_Edit_Redo);
        mnbar_Edit.add(mnbar_Edit_Separator1);

        mnbar_Edit_Cut.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mnbar_Edit_Cut.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        mnbar_Edit_Cut.setText("Cut");
        mnbar_Edit.add(mnbar_Edit_Cut);

        mnbar_Edit_Copy.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mnbar_Edit_Copy.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        mnbar_Edit_Copy.setText("Copy");
        mnbar_Edit.add(mnbar_Edit_Copy);

        mnbar_Edit_Paste.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mnbar_Edit_Paste.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        mnbar_Edit_Paste.setText("Paste");
        mnbar_Edit.add(mnbar_Edit_Paste);
        mnbar_Edit.add(mnbar_Edit_Separator2);

        mnbar_Edit_Find.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mnbar_Edit_Find.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        mnbar_Edit_Find.setText("Find");
        mnbar_Edit.add(mnbar_Edit_Find);

        mnbar_Edit_Replace.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mnbar_Edit_Replace.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        mnbar_Edit_Replace.setText("Replace");
        mnbar_Edit.add(mnbar_Edit_Replace);

        mnbar.add(mnbar_Edit);

        mnbar_Help.setText("Help");
        mnbar_Help.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N

        mnbar_Help_About.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        mnbar_Help_About.setText("About");
        mnbar_Help.add(mnbar_Help_About);

        mnbar.add(mnbar_Help);

        setJMenuBar(mnbar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void wspace_EditAreaCaretPositionChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_wspace_EditAreaCaretPositionChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_wspace_EditAreaCaretPositionChanged

    private void wspace_EditAreaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_wspace_EditAreaMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_wspace_EditAreaMouseEntered

    private void wspace_EditAreaCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_wspace_EditAreaCaretUpdate
        // TODO add your handling code here:
    }//GEN-LAST:event_wspace_EditAreaCaretUpdate

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainViewTD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainViewTD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainViewTD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainViewTD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    new MainViewTD().setVisible(true);
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(MainViewTD.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar mnbar;
    private javax.swing.JMenu mnbar_Edit;
    private javax.swing.JMenuItem mnbar_Edit_Copy;
    private javax.swing.JMenuItem mnbar_Edit_Cut;
    private javax.swing.JMenuItem mnbar_Edit_Find;
    private javax.swing.JMenuItem mnbar_Edit_Paste;
    private javax.swing.JMenuItem mnbar_Edit_Redo;
    private javax.swing.JMenuItem mnbar_Edit_Replace;
    private javax.swing.JPopupMenu.Separator mnbar_Edit_Separator1;
    private javax.swing.JPopupMenu.Separator mnbar_Edit_Separator2;
    private javax.swing.JMenuItem mnbar_Edit_Undo;
    private javax.swing.JMenu mnbar_File;
    private javax.swing.JMenuItem mnbar_File_Exit;
    private javax.swing.JMenuItem mnbar_File_New;
    private javax.swing.JMenuItem mnbar_File_Open;
    private javax.swing.JMenuItem mnbar_File_Save;
    private javax.swing.JMenuItem mnbar_File_SaveAs;
    private javax.swing.JPopupMenu.Separator mnbar_File_Separator1;
    private javax.swing.JPopupMenu.Separator mnbar_File_Separator2;
    private javax.swing.JPopupMenu.Separator mnbar_File_Separator3;
    private javax.swing.JMenu mnbar_Help;
    private javax.swing.JMenuItem mnbar_Help_About;
    private javax.swing.JPanel sttbar;
    private javax.swing.JLabel sttbar_CharEncoding;
    private javax.swing.JLabel sttbar_LnCol;
    private javax.swing.JLabel sttbar_Location;
    private javax.swing.JLabel sttbar_Saved;
    private javax.swing.JToolBar tlbar;
    private javax.swing.JButton tlbar_New;
    private javax.swing.JPanel wspace;
    private javax.swing.JTextArea wspace_EditArea;
    private javax.swing.JScrollPane wspace_Scroll;
    // End of variables declaration//GEN-END:variables
}