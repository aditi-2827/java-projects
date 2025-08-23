import javax.swing.*;
import java.awt.*;
import java.io.*;

public class teFormatting {
    private final texteditor ui;

    public teFormatting(texteditor ui, JButton openBtn, JButton saveBtn){
        this.ui = ui;

        // ---- File Open/Save (menu + toolbar) ----
        ui.fileItemOpen.addActionListener(e -> openFile());
        ui.fileItemSave.addActionListener(e -> saveFile());
        if (openBtn != null) openBtn.addActionListener(e -> openFile());
        if (saveBtn != null) saveBtn.addActionListener(e -> saveFile());

        // ---- Colors ----
        ui.textColorItem.addActionListener(e -> changeTextColor());
        ui.bgColorItem.addActionListener(e -> changeBackgroundColor());

        // ---- Font family chooser ----
        ui.fontItem.addActionListener(e -> changeFontFamily());
    }

    // ---------- File ----------
    private void openFile(){
        JFileChooser chooser = new JFileChooser();
        int option = chooser.showOpenDialog(ui);
        if(option == JFileChooser.APPROVE_OPTION){
            try (BufferedReader br = new BufferedReader(new FileReader(chooser.getSelectedFile()))) {
                ui.textarea.read(br, null);
            } catch(Exception ex){
                JOptionPane.showMessageDialog(ui, "Error Opening File");
            }
        }
    }

    private void saveFile(){
        JFileChooser chooser = new JFileChooser();
        int option = chooser.showSaveDialog(ui);
        if(option == JFileChooser.APPROVE_OPTION){
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(chooser.getSelectedFile()))) {
                ui.textarea.write(bw);
            } catch(Exception ex){
                JOptionPane.showMessageDialog(ui, "Error Saving File");
            }
        }
    }

    // ---------- Colors ----------
    private void changeTextColor(){
        Color c = JColorChooser.showDialog(ui, "Choose Text Color", ui.textarea.getForeground());
        if(c != null) ui.textarea.setForeground(c);
    }

    private void changeBackgroundColor(){
        Color c = JColorChooser.showDialog(ui, "Choose Background Color", ui.textarea.getBackground());
        if(c != null) ui.textarea.setBackground(c);
    }

    // ---------- Font family ----------
    private void changeFontFamily(){
        String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment()
                .getAvailableFontFamilyNames();

        String current = ui.textarea.getFont().getFamily();
        String selected = (String) JOptionPane.showInputDialog(
                ui, "Choose Font Family", "Font Family",
                JOptionPane.PLAIN_MESSAGE, null, fonts, current);

        if(selected != null){
            // keep size & style from texteditor state
            ui.currentFontFamily = selected;
            ui.textarea.setFont(new Font(ui.currentFontFamily, ui.currentFontStyle, ui.currentFontSize));
        }
    }
}
