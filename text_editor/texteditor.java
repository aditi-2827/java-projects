import javax.swing.*;
import java.awt.*;

public class texteditor extends JFrame {
    JTextArea textarea;
    JLabel statusBar;

    // Menus
    JMenuItem fileItemNew, fileItemOpen, fileItemSave, fileItemExit;
    JMenuItem cutItem, copyItem, pasteItem;
    JMenuItem fontItem, textColorItem, bgColorItem;
    JMenu fontSizeMenu, fontStyleMenu;

    // current font state
    int currentFontSize = 16;           // default size
    String currentFontFamily = "Arial"; // default family
    int currentFontStyle = Font.PLAIN;  // default style

    public texteditor(){
        super("Text Editor - Enhanced");

        // ====== MENU BAR ======
        JMenuBar menuBar = new JMenuBar();

        // File Menu
        JMenu fileMenu = new JMenu("File");
        fileItemNew  = new JMenuItem("New");
        fileItemOpen = new JMenuItem("Open");
        fileItemSave = new JMenuItem("Save");
        fileItemExit = new JMenuItem("Exit");
        fileMenu.add(fileItemNew);
        fileMenu.add(fileItemOpen);
        fileMenu.add(fileItemSave);
        fileMenu.addSeparator();
        fileMenu.add(fileItemExit);

        // Edit Menu
        JMenu editMenu = new JMenu("Edit");
        cutItem   = new JMenuItem("Cut");
        copyItem  = new JMenuItem("Copy");
        pasteItem = new JMenuItem("Paste");
        editMenu.add(cutItem);
        editMenu.add(copyItem);
        editMenu.add(pasteItem);

        // Format Menu
        JMenu formatMenu = new JMenu("Format");
        fontItem      = new JMenuItem("Font Family...");
        textColorItem = new JMenuItem("Text Color");
        bgColorItem   = new JMenuItem("Background Color");

        // Font Size submenu
        fontSizeMenu = new JMenu("Font Size");
        int[] sizes = {12, 14, 16, 18, 20, 24};
        for (int size : sizes) {
            JMenuItem sizeItem = new JMenuItem(String.valueOf(size));
            // set font size -> call updateFont()
            sizeItem.addActionListener(e -> {
                currentFontSize = size;
                updateFont();
            });
            fontSizeMenu.add(sizeItem);
        }

        // Font Style submenu
        fontStyleMenu = new JMenu("Font Style");
        JMenuItem plainItem  = new JMenuItem("Plain");
        JMenuItem boldItem   = new JMenuItem("Bold");
        JMenuItem italicItem = new JMenuItem("Italic");

        plainItem.addActionListener(e -> { currentFontStyle = Font.PLAIN;  updateFont(); });
        boldItem.addActionListener(e  -> { currentFontStyle = Font.BOLD;   updateFont(); });
        italicItem.addActionListener(e-> { currentFontStyle = Font.ITALIC; updateFont(); });

        fontStyleMenu.add(plainItem);
        fontStyleMenu.add(boldItem);
        fontStyleMenu.add(italicItem);

        formatMenu.add(fontItem);
        formatMenu.add(fontSizeMenu);
        formatMenu.add(fontStyleMenu);
        formatMenu.addSeparator();
        formatMenu.add(textColorItem);
        formatMenu.add(bgColorItem);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(formatMenu);
        setJMenuBar(menuBar);

        // ====== TOOLBAR ======
        JToolBar toolBar = new JToolBar();
        JButton newBtn   = new JButton("New");
        JButton openBtn  = new JButton("Open");
        JButton saveBtn  = new JButton("Save");
        JButton cutBtn   = new JButton("Cut");
        JButton copyBtn  = new JButton("Copy");
        JButton pasteBtn = new JButton("Paste");

        toolBar.add(newBtn);
        toolBar.add(openBtn);
        toolBar.add(saveBtn);
        toolBar.addSeparator();
        toolBar.add(cutBtn);
        toolBar.add(copyBtn);
        toolBar.add(pasteBtn);
        add(toolBar, BorderLayout.NORTH);

        // ====== TEXT AREA ======
        textarea = new JTextArea();
        textarea.setFont(new Font(currentFontFamily, currentFontStyle, currentFontSize));
        JScrollPane scroll = new JScrollPane(textarea);
        add(scroll, BorderLayout.CENTER);

        // ====== STATUS BAR ======
        statusBar = new JLabel("Line: 1, Column: 1   |   Words: 0");
        statusBar.setBorder(BorderFactory.createEtchedBorder());
        add(statusBar, BorderLayout.SOUTH);

        // ====== BASIC ACTIONS WIRED HERE ======
        // File (quick ones)
        fileItemNew.addActionListener(e -> textarea.setText(""));
        fileItemExit.addActionListener(e -> System.exit(0));

        // Toolbar shortcuts for edit ops
        cutItem.addActionListener(e -> textarea.cut());
        copyItem.addActionListener(e -> textarea.copy());
        pasteItem.addActionListener(e -> textarea.paste());
        cutBtn.addActionListener(e -> textarea.cut());
        copyBtn.addActionListener(e -> textarea.copy());
        pasteBtn.addActionListener(e -> textarea.paste());

        // Let the formatting/IO module wire the rest (open/save, colors, font family)
        new teFormatting(this, openBtn, saveBtn);

        setSize(1200, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // ---- Helper to reapply the current font state ----
    private void updateFont() {
        textarea.setFont(new Font(currentFontFamily, currentFontStyle, currentFontSize));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(texteditor::new);
    }
}
