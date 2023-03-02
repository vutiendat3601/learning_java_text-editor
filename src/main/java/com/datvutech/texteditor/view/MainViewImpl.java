package com.datvutech.texteditor.view;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.InputEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;

import com.datvutech.texteditor.controller.MainController;
import com.datvutech.texteditor.model.FileModelAccess;
import com.datvutech.texteditor.util.TextUtils;
import com.listener.DocumentAdapter;

/**
 * This is main view implementation of Text Editor app
 * 
 * @author Dat Vu
 * @date 2023-03-02
 */
public class MainViewImpl extends JFrame implements MainView {
	private FileModelAccess currentFile;
	private MainController mainController;

	public MainViewImpl(MainController mainController, FileModelAccess currentFile) {
		this.mainController = mainController;
		this.currentFile = currentFile;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initComponents();
		setVisible(true);
	}

	/* ### Statusbar ### */
	@Override
	public void reloadCaretPossitionStatusbar() {
		Point possion = getCurrentLineAndColumn();
		sttbar_LnCol.setText("Ln " + (int) possion.getX() +
				", Col " + (int) possion.getY());
	}

	@Override
	public void reloadSavedStateStatusbar() {
		sttbar_Saved.setText("Saved: " + (currentFile.isSaved() ? "YES" : "NO"));
	}

	@Override
	public void reloadLocationStatusbar() {
		boolean undefinedLocation = TextUtils.isNullOrBlank(currentFile.getFileName());
		sttbar_Location.setText("Location: " + (undefinedLocation ? " undefined" : currentFile.getFileName()));
	}

	@Override
	public void reloadCharEncodingStatusbar() {
		boolean undefinedEncoding = TextUtils.isNullOrBlank(currentFile.getEncoding());
		sttbar_CharEncoding.setText(undefinedEncoding ? "undefined" : currentFile.getEncoding());
	}

	@Override
	public void reloadStatusbar() {
		reloadSavedStateStatusbar();
		reloadLocationStatusbar();
		reloadCharEncodingStatusbar();
		reloadCaretPossitionStatusbar();
	}
	/* #################################### */

	/* ### Dialog ### */
	public void showErrorDialog(String title, String message) {
		JOptionPane.showMessageDialog(this, message, title, JOptionPane.ERROR_MESSAGE);
	}

	@Override
	public boolean showConfirmDialog(String title, String message) {
		int result = JOptionPane.showConfirmDialog(MainViewImpl.this,
				message, title, JOptionPane.YES_NO_OPTION);
		return (result == JOptionPane.YES_OPTION);
	}

	@Override
	public String showOpenFileDialog(String title) {
		String fileName = "";
		fileChooser.setDialogTitle(title);
		int result = fileChooser.showOpenDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
			fileName = fileChooser.getSelectedFile().getAbsolutePath();
		}
		return fileName;
	}

	@Override
	public String showSaveFileDialog(String title) {
		String fileName = "";
		fileChooser.setDialogTitle(title);
		int result = fileChooser.showSaveDialog(this);
		if (result == JFileChooser.APPROVE_OPTION) {
			fileName = fileChooser.getSelectedFile().getAbsolutePath();
		}
		return fileName;
	}
	/* #################################### */

	/* ### Workspace ### */
	@Override
	public String getWorkspaceTextData() {
		return wspace_EditArea.getText();
	}

	@Override
	public String getWorkspaceSelectedText() {
		String text = wspace_EditArea.getSelectedText();
		return TextUtils.isNullOrBlank(text) ? "" : text;
	}

	@Override
	public void pasteWorkspaceText() {
		wspace_EditArea.paste();
	}

	@Override
	public void reloadWorkspace() {
		String textData = currentFile.getTextData();
		wspace_EditArea.setText(textData);
		reloadStatusbar();
	}

	/* Helper */
	private Point getCurrentLineAndColumn() {
		int line = 0;
		int startOffsetLine = 0;
		int caretPos = 0;
		int column = 0;
		try {
			caretPos = wspace_EditArea.getCaretPosition();
			line = wspace_EditArea.getLineOfOffset(caretPos);
			startOffsetLine = wspace_EditArea.getLineStartOffset(line);
			column = caretPos - startOffsetLine;
		} catch (BadLocationException ex) {
			ex.printStackTrace();
		}
		return new Point(line + 1, column + 1);
	}
	/* #################################### */

	/* ### Initialize components ### */

	private void initActions() {
		/* *** Workspace actions *** */

		// Change file data
		// wspace_EditArea.addKeyListener(new KeyAdapter() {
		// // Order press -> type -> relsease
		// @Override
		// public void keyReleased(KeyEvent e) {
		// mainController.changedCurrentFileData();
		// }

		// });

		wspace_EditArea.getDocument().addDocumentListener(new DocumentAdapter() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				mainController.makeBufferedTextData();
				mainController.changedCurrentFileData();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				mainController.makeBufferedTextData();
				mainController.changedCurrentFileData();
			}
		});

		// Locate text cursor position
		// wspace_EditArea.addCaretListener(new CaretListener() {

		// @Override
		// public void caretUpdate(CaretEvent e) {
		// // (e) -> mainController.changedCaretPossition();
		// // mainController.changedCaretPossition();
		// mainController.makeBufferedTextData();
		// }

		// });

		/* ************************************ */

		/* *** Menu File actions *** */

		// New file ...
		mnbar_File_New.addActionListener((e) -> mainController.newFile());
		tlbar_New.addActionListener((e) -> mainController.newFile());

		// Open file ...
		mnbar_File_Open.addActionListener((e) -> mainController.openFile());
		tlbar_Open.addActionListener((e) -> mainController.openFile());

		// Save file ...
		mnbar_File_Save.addActionListener((e) -> mainController.saveFile());

		// Save file as ...
		mnbar_File_SaveAs.addActionListener((e) -> mainController.saveFileAs());
		/* ************************************ */

		/* *** Menu Edit actions *** */
		mnbar_Edit_Copy.addActionListener((e) -> mainController.copyText());
		mnbar_Edit_Paste.addActionListener((e) -> mainController.pasteText());
		mnbar_Edit_Undo.addActionListener((e) -> mainController.undoWorkspace());
		mnbar_Edit_Redo.addActionListener((e) -> mainController.redoWorkspace());
	}

	private void initComponents() {
		fileChooser = new JFileChooser();
		wspace = new JPanel();
		wspace_Scroll = new JScrollPane();
		wspace_EditArea = new JTextArea();
		sttbar = new JPanel();
		sttbar_LnCol = new JLabel();
		sttbar_CharEncoding = new JLabel();
		sttbar_Saved = new javax.swing.JLabel();
		sttbar_Location = new javax.swing.JLabel();
		tlbar = new JToolBar();
		tlbar_New = new JButton();
		tlbar_Open = new JButton();
		mnbar = new JMenuBar();
		mnbar_File = new JMenu();
		mnbar_File_New = new JMenuItem();
		mnbar_File_Separator1 = new JPopupMenu.Separator();
		mnbar_File_Open = new JMenuItem();
		mnbar_File_Separator2 = new JPopupMenu.Separator();
		mnbar_File_Save = new JMenuItem();
		mnbar_File_SaveAs = new JMenuItem();
		mnbar_File_Separator3 = new JPopupMenu.Separator();
		mnbar_File_Exit = new JMenuItem();
		mnbar_Edit = new JMenu();
		mnbar_Edit_Undo = new JMenuItem();
		mnbar_Edit_Redo = new JMenuItem();
		mnbar_Edit_Separator1 = new JPopupMenu.Separator();
		mnbar_Edit_Cut = new JMenuItem();
		mnbar_Edit_Copy = new JMenuItem();
		mnbar_Edit_Paste = new JMenuItem();
		mnbar_Edit_Separator2 = new JPopupMenu.Separator();
		mnbar_Edit_Find = new JMenuItem();
		mnbar_Edit_Replace = new JMenuItem();
		mnbar_Help = new JMenu();
		mnbar_Help_About = new JMenuItem();

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		wspace.setLayout(new BorderLayout());

		wspace_EditArea.setColumns(20);
		wspace_EditArea.setFont(new Font("Arial", Font.PLAIN, 16));
		wspace_EditArea.setRows(5);
		wspace_EditArea.setText("");

		wspace_Scroll.setViewportView(wspace_EditArea);

		wspace.add(wspace_Scroll, BorderLayout.CENTER);

		sttbar.setFont(new Font("Segoe UI", 1, 14));
		sttbar.setMinimumSize(new Dimension(100, 32));
		sttbar.setLayout(new FlowLayout(FlowLayout.LEFT));

		sttbar_LnCol.setFont(new Font("Segoe UI", 0, 13));
		sttbar_LnCol.setText("Ln 1, Col 1");
		sttbar.add(sttbar_LnCol);

		sttbar_CharEncoding.setFont(new Font("Segoe UI", 0, 13));
		sttbar_CharEncoding.setText("UTF8");
		sttbar_CharEncoding.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		sttbar_CharEncoding.setHorizontalTextPosition(SwingConstants.LEFT);
		sttbar.add(sttbar_CharEncoding);

		sttbar_Saved.setFont(new Font("Segoe UI", 0, 13));
		sttbar_Saved.setText("Saved: NO");
		sttbar.add(sttbar_Saved);

		sttbar_Location.setFont(new Font("Segoe UI", 0, 13));
		sttbar_Location.setText("Location: undefined");
		sttbar.add(sttbar_Location);

		wspace.add(sttbar, BorderLayout.SOUTH);

		getContentPane().add(wspace, BorderLayout.CENTER);

		tlbar.setRollover(true);

		tlbar_New.setText("New");
		tlbar_New.setToolTipText("Create new file");
		tlbar_New.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
		tlbar_New.setFocusable(false);
		tlbar_New.setHorizontalTextPosition(SwingConstants.CENTER);
		tlbar_New.setVerticalTextPosition(SwingConstants.BOTTOM);
		tlbar.add(tlbar_New);

		tlbar_Open.setText("Open");
		tlbar_Open.setToolTipText("Open a file");
		tlbar_Open.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
		tlbar_Open.setFocusable(false);
		tlbar_Open.setHorizontalTextPosition(SwingConstants.CENTER);
		tlbar_Open.setVerticalTextPosition(SwingConstants.BOTTOM);
		tlbar.add(tlbar_Open);

		getContentPane().add(tlbar, BorderLayout.NORTH);

		mnbar_File.setText("File");
		mnbar_File.setFont(new Font("Segoe UI", 0, 13));

		mnbar_File_New.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
				InputEvent.CTRL_DOWN_MASK));
		mnbar_File_New.setFont(new Font("Segoe UI", 0, 13));
		mnbar_File_New.setText("New File...");
		mnbar_File.add(mnbar_File_New);
		mnbar_File.add(mnbar_File_Separator1);

		mnbar_File_Open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
				InputEvent.CTRL_DOWN_MASK));
		mnbar_File_Open.setFont(new Font("Segoe UI", 0, 13));
		mnbar_File_Open.setText("Open File...");
		mnbar_File.add(mnbar_File_Open);
		mnbar_File.add(mnbar_File_Separator2);

		mnbar_File_Save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				InputEvent.CTRL_DOWN_MASK));
		mnbar_File_Save.setFont(new Font("Segoe UI", 0, 13));
		mnbar_File_Save.setText("Save");
		mnbar_File.add(mnbar_File_Save);

		mnbar_File_SaveAs.setFont(new Font("Segoe UI", 0, 13));
		mnbar_File_SaveAs.setText("Save As...");
		mnbar_File.add(mnbar_File_SaveAs);
		mnbar_File.add(mnbar_File_Separator3);

		mnbar_File_Exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
				InputEvent.ALT_DOWN_MASK));
		mnbar_File_Exit.setFont(new Font("Segoe UI", 0, 13));
		mnbar_File_Exit.setText("Exit");
		mnbar_File.add(mnbar_File_Exit);

		mnbar.add(mnbar_File);

		mnbar_Edit.setText("Edit");
		mnbar_Edit.setFont(new Font("Segoe UI", 0, 13));

		mnbar_Edit_Undo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,
				InputEvent.CTRL_DOWN_MASK));
		mnbar_Edit_Undo.setFont(new Font("Segoe UI", 0, 13));
		mnbar_Edit_Undo.setText("Undo");
		mnbar_Edit.add(mnbar_Edit_Undo);

		mnbar_Edit_Redo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y,
				InputEvent.CTRL_DOWN_MASK));
		mnbar_Edit_Redo.setFont(new Font("Segoe UI", 0, 13));
		mnbar_Edit_Redo.setText("Redo");
		mnbar_Edit.add(mnbar_Edit_Redo);
		mnbar_Edit.add(mnbar_Edit_Separator1);

		mnbar_Edit_Cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
				InputEvent.CTRL_DOWN_MASK));
		mnbar_Edit_Cut.setFont(new Font("Segoe UI", 0, 13));
		mnbar_Edit_Cut.setText("Cut");
		mnbar_Edit.add(mnbar_Edit_Cut);

		mnbar_Edit_Copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,
				InputEvent.CTRL_DOWN_MASK));
		mnbar_Edit_Copy.setFont(new Font("Segoe UI", 0, 13));
		mnbar_Edit_Copy.setText("Copy");
		mnbar_Edit.add(mnbar_Edit_Copy);

		mnbar_Edit_Paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,
				InputEvent.CTRL_DOWN_MASK));
		mnbar_Edit_Paste.setFont(new Font("Segoe UI", 0, 13));
		mnbar_Edit_Paste.setText("Paste");
		mnbar_Edit.add(mnbar_Edit_Paste);
		mnbar_Edit.add(mnbar_Edit_Separator2);

		mnbar_Edit_Find.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,
				InputEvent.CTRL_DOWN_MASK));
		mnbar_Edit_Find.setFont(new Font("Segoe UI", 0, 13));
		mnbar_Edit_Find.setText("Find");
		mnbar_Edit.add(mnbar_Edit_Find);

		mnbar_Edit_Replace.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,
				InputEvent.CTRL_DOWN_MASK));
		mnbar_Edit_Replace.setFont(new Font("Segoe UI", 0, 13));
		mnbar_Edit_Replace.setText("Replace");
		mnbar_Edit.add(mnbar_Edit_Replace);

		mnbar.add(mnbar_Edit);

		mnbar_Help.setText("Help");
		mnbar_Help.setFont(new Font("Segoe UI", 0, 13));

		mnbar_Help_About.setFont(new Font("Segoe UI", 0, 13));
		mnbar_Help_About.setText("About");
		mnbar_Help.add(mnbar_Help_About);

		mnbar.add(mnbar_Help);

		setJMenuBar(mnbar);

		pack();
		initActions();
	}

	private JFileChooser fileChooser;
	private JMenuBar mnbar;
	private JMenu mnbar_Edit;
	private JMenuItem mnbar_Edit_Copy;
	private JMenuItem mnbar_Edit_Cut;
	private JMenuItem mnbar_Edit_Find;
	private JMenuItem mnbar_Edit_Paste;
	private JMenuItem mnbar_Edit_Redo;
	private JMenuItem mnbar_Edit_Replace;
	private JPopupMenu.Separator mnbar_Edit_Separator1;
	private JPopupMenu.Separator mnbar_Edit_Separator2;
	private JMenuItem mnbar_Edit_Undo;
	private JMenu mnbar_File;
	private JMenuItem mnbar_File_Exit;
	private JMenuItem mnbar_File_New;
	private JMenuItem mnbar_File_Open;
	private JMenuItem mnbar_File_Save;
	private JMenuItem mnbar_File_SaveAs;
	private JPopupMenu.Separator mnbar_File_Separator1;
	private JPopupMenu.Separator mnbar_File_Separator2;
	private JPopupMenu.Separator mnbar_File_Separator3;
	private JMenu mnbar_Help;
	private JMenuItem mnbar_Help_About;
	private JPanel sttbar;
	private JLabel sttbar_CharEncoding;
	private JLabel sttbar_LnCol;
	private JLabel sttbar_Location;
	private JLabel sttbar_Saved;
	private JToolBar tlbar;
	private JButton tlbar_New;
	private JButton tlbar_Open;
	private JPanel wspace;
	private JTextArea wspace_EditArea;
	private JScrollPane wspace_Scroll;
}
