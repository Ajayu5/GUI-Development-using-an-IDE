import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GameIntroApp {
    private JFrame mainFrame;
    private JFrame instructionsFrame;
    private JFrame gameFrame;
    private JTextField guessTextField;
    private JLabel resultLabel;
    private int targetNumber;

    public GameIntroApp() {
        prepareGUI();
    }

    private void prepareGUI() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        mainFrame = new JFrame("Game Introduction");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(300, 200);
        mainFrame.setLayout(new BorderLayout());
        mainFrame.setLocationRelativeTo(null);

        ImageIcon originalImageIcon = new ImageIcon("C:\\Users\\pocah\\Downloads\\Question mark photo.jpg"); 
        Image originalImage = originalImageIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(mainFrame.getWidth(), mainFrame.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);

        JLabel backgroundLabel = new JLabel(scaledImageIcon);
        backgroundLabel.setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        buttonPanel.setOpaque(false);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton instructionsButton = new JButton("Instructions");
        JButton playButton = new JButton("Play");
        JButton quitButton = new JButton("Quit");

        instructionsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showInstructions();
            }
        });

        playButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });

        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        buttonPanel.add(instructionsButton);
        buttonPanel.add(playButton);
        buttonPanel.add(quitButton);

        backgroundLabel.add(buttonPanel, BorderLayout.CENTER);
        mainFrame.setContentPane(backgroundLabel);
        mainFrame.setVisible(true);
    }

    private void showInstructions() {
        instructionsFrame = new JFrame("Instructions");
        instructionsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        instructionsFrame.setSize(300, 200);
        instructionsFrame.setLayout(new BorderLayout());
        instructionsFrame.setLocationRelativeTo(mainFrame);

        JTextArea instructionsTextArea = new JTextArea();
        instructionsTextArea.setText("These are the instructions.");
        instructionsTextArea.setEditable(false);
        instructionsTextArea.setFont(new Font("Arial", Font.PLAIN, 14));
        instructionsTextArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                instructionsFrame.dispose();
            }
        });

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setOpaque(false);
        contentPanel.add(instructionsTextArea, BorderLayout.CENTER);
        contentPanel.add(backButton, BorderLayout.SOUTH);

        instructionsFrame.setContentPane(contentPanel);
        instructionsFrame.setVisible(true);
    }

    private void startGame() {
        gameFrame = new JFrame("Guessing Game");
        gameFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        gameFrame.setSize(300, 200);
        gameFrame.setLayout(new BorderLayout());
        gameFrame.setLocationRelativeTo(mainFrame);

        ImageIcon originalImageIcon = new ImageIcon("C:\\Users\\pocah\\Downloads\\Question mark photo.jpg"); 
        Image originalImage = originalImageIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(gameFrame.getWidth(), gameFrame.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);

        JLabel backgroundLabel = new JLabel(scaledImageIcon);
        backgroundLabel.setLayout(new BorderLayout());

        JPanel gamePanel = new JPanel(new BorderLayout());
        gamePanel.setOpaque(false);
        gamePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel guessLabel = new JLabel("Enter your guess:");
        guessTextField = new JTextField(10);
        guessTextField.setFont(new Font("Arial", Font.PLAIN, 14));
        JButton checkButton = new JButton("Check");
        checkButton.setFont(new Font("Arial", Font.PLAIN, 14));
        resultLabel = new JLabel();
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);

        checkButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });

        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.setOpaque(false);
        inputPanel.add(guessLabel);
        inputPanel.add(guessTextField);
        inputPanel.add(checkButton);

        gamePanel.add(inputPanel, BorderLayout.CENTER);
        gamePanel.add(resultLabel, BorderLayout.SOUTH);

        backgroundLabel.add(gamePanel, BorderLayout.CENTER);
        gameFrame.setContentPane(backgroundLabel);
        gameFrame.setVisible(true);

        // Generate a random number between 1 and 100 as the target
        Random random = new Random();
        targetNumber = random.nextInt(100) + 1;
    }

    private void checkGuess() {
        int guess = Integer.parseInt(guessTextField.getText());
        if (guess < targetNumber) {
            resultLabel.setText("Too low");
        } else if (guess > targetNumber) {
            resultLabel.setText("Too high");
        } else {
            resultLabel.setText("You got it!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                GameIntroApp app = new GameIntroApp();
            }
        });
    }
}