import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

// Main class extending JFrame and implementing ActionListener for handling button events
class count1 extends JFrame implements ActionListener {

    JTextArea textArea;  // Declaring a text area for user input
    JButton wordButton, charButton, bgColorButton, textColorButton; // Declaring buttons

    // Constructor: This runs automatically when we create an object of count1
    count1(){
        super("word counter"); // Setting the title of the JFrame window

        // Creating a text area and setting its position and size
        textArea = new JTextArea();
        textArea.setBounds(50,50,350,200);

        // Creating buttons and setting their positions and sizes
        wordButton = new JButton("word count");
        wordButton.setBounds(50, 270, 120, 30);

        charButton = new JButton("count character");
        charButton.setBounds(270,270,120,30);

        bgColorButton = new JButton("bg Color");
        bgColorButton.setBounds(50, 320, 120, 30);

        textColorButton = new JButton("text color");
        textColorButton.setBounds(270,320,120,30);

        // Adding components to the frame
        add(textArea);
        add(wordButton);
        add(charButton);
        add(bgColorButton);
        add(textColorButton);

        addActionListeners(); // Method call to attach action listeners to buttons

        // Frame settings
        setSize(500,500); // Setting size of the frame
        setLayout(null); // Using null layout for manual positioning
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close the window when user clicks X
        setVisible(true); // Making the frame visible
    }

    // Adding action listeners to all buttons so they can respond to clicks
    public void addActionListeners(){
        wordButton.addActionListener(this);
        charButton.addActionListener(this);
        bgColorButton.addActionListener(this);
        textColorButton.addActionListener(this);
    }

    // Handling button click events (event handling method)
    public void actionPerformed (ActionEvent e){
        String text = textArea.getText(); // Getting the text from the text area

        if (e.getSource() == wordButton) {
            // Splitting text into words (using whitespace as separator)
            String[] words = text.split("\\s+");
            showMessage("Total words: " + words.length);

        } else if (e.getSource() == charButton) {
            // Counting characters (including spaces)
            showMessage("Total Characters with space: " + text.length());

        } else if (e.getSource() == bgColorButton) {
            // Opening a color chooser for selecting background color
            Color bgColor = JColorChooser.showDialog(this, "Choose Background Color", Color.WHITE);
            if(bgColor != null) textArea.setBackground(bgColor);

        } else if (e.getSource() == textColorButton) {
            // Opening a color chooser for selecting text color
            Color textColor = JColorChooser.showDialog(this, "Choose Text Color", Color.BLACK);
            if(textColor != null) textArea.setForeground(textColor);
        }
    }

    // Utility method to display message dialogs
    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}

// Main class with main method (entry point of program)
public class wordCount{
    public static void main(String[] args) {
        new count1(); // Creating an object of count1, which launches the GUI
    }
}

