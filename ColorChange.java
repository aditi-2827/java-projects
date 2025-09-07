package colorchange;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

class CChange extends JFrame implements ActionListener {

    // buttons
    JButton redBtn, greenBtn, blueBtn, yellowBtn, orangeBtn, pinkBtn, blackBtn, startBtn;
    JLabel instructionLabel, scoreLabel, timerLabel;
    Timer timer, bgTimer;
    int timeLeft = 5;
    int score = 0;
    String targetColor = "";
    Random rand = new Random();

    // constructor
    CChange() {
        super("Color Confusion Game");

        // labels
        instructionLabel = new JLabel("Press START to begin", SwingConstants.CENTER);
        instructionLabel.setBounds(50, 20, 300, 30);
        instructionLabel.setFont(new Font("Arial", Font.BOLD, 18));

        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setBounds(20, 320, 100, 30);

        timerLabel = new JLabel("Time: 5");
        timerLabel.setBounds(300, 320, 100, 30);

        // create buttons
        redBtn = new JButton("Red");
        greenBtn = new JButton("Green");
        blueBtn = new JButton("Blue");
        yellowBtn = new JButton("Yellow");
        orangeBtn = new JButton("Orange");
        pinkBtn = new JButton("Pink");
        blackBtn = new JButton("Black");
        startBtn = new JButton("START");

        // set positions
        redBtn.setBounds(30, 80, 100, 40);
        greenBtn.setBounds(150, 80, 100, 40);
        blueBtn.setBounds(270, 80, 100, 40);

        yellowBtn.setBounds(30, 140, 100, 40);
        orangeBtn.setBounds(150, 140, 100, 40);
        pinkBtn.setBounds(270, 140, 100, 40);

        blackBtn.setBounds(150, 200, 100, 40);
        startBtn.setBounds(150, 260, 100, 40);

        // add listeners
        redBtn.addActionListener(this);
        greenBtn.addActionListener(this);
        blueBtn.addActionListener(this);
        yellowBtn.addActionListener(this);
        orangeBtn.addActionListener(this);
        pinkBtn.addActionListener(this);
        blackBtn.addActionListener(this);
        startBtn.addActionListener(this);

        // add components
        add(instructionLabel);
        add(scoreLabel);
        add(timerLabel);
        add(redBtn);
        add(greenBtn);
        add(blueBtn);
        add(yellowBtn);
        add(orangeBtn);
        add(pinkBtn);
        add(blackBtn);
        add(startBtn);

        // frame setup
        setSize(420, 400);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // method to pick a new target color
    private void newRound() {
        String[] colors = {"Red", "Green", "Blue", "Yellow", "Orange", "Pink", "Black"};
        targetColor = colors[rand.nextInt(colors.length)];
        instructionLabel.setText("Click: " + targetColor);

        // reset time (gets harder)
        timeLeft = Math.max(2, 7 - score);
        timerLabel.setText("Time: " + timeLeft);

        // start timer
        if (timer != null) timer.stop();
        timer = new Timer(1000, e -> {
            timeLeft--;
            timerLabel.setText("Time: " + timeLeft);
            if (timeLeft <= 0) {
                gameOver("⏳ Time's up! Game Over.");
            }
        });
        timer.start();

        // background color distraction
        if (bgTimer != null) bgTimer.stop();
        bgTimer = new Timer(500, e -> {
            Color randomColor = new Color(
                rand.nextInt(256),
                rand.nextInt(256),
                rand.nextInt(256)
            );
            getContentPane().setBackground(randomColor);
        });
        bgTimer.start();
    }

    // handle button clicks
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startBtn) {
            score = 0;
            scoreLabel.setText("Score: 0");
            getContentPane().setBackground(Color.WHITE);
            newRound();
            return;
        }

        String clicked = "";
        if (e.getSource() == redBtn) clicked = "Red";
        else if (e.getSource() == greenBtn) clicked = "Green";
        else if (e.getSource() == blueBtn) clicked = "Blue";
        else if (e.getSource() == yellowBtn) clicked = "Yellow";
        else if (e.getSource() == orangeBtn) clicked = "Orange";
        else if (e.getSource() == pinkBtn) clicked = "Pink";
        else if (e.getSource() == blackBtn) clicked = "Black";

        if (!targetColor.equals("")) {
            if (clicked.equals(targetColor)) {
                score++;
                scoreLabel.setText("Score: " + score);
                newRound(); // next round
            } else {
                gameOver("❌ Wrong choice! Game Over.");
            }
        }
    }

    // end game
    private void gameOver(String message) {
        if (timer != null) timer.stop();
        if (bgTimer != null) bgTimer.stop();
        JOptionPane.showMessageDialog(this, message + "\nFinal Score: " + score);
        instructionLabel.setText("Click START to play again");
        targetColor = "";
        getContentPane().setBackground(Color.WHITE);
    }
}

// main class
public class ColorChange {
    public static void main(String[] args) {
        new CChange();
    }
}
