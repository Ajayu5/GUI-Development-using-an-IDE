import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class IntegerVerificationApp extends JFrame {
    private JTextField textField;
    private JLabel resultLabel;

    public IntegerVerificationApp() {
        setTitle("Integer Verification");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        // Create components
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));

        JLabel inputLabel = new JLabel("Enter an integer:");
        textField = new JTextField();
        JButton verifyButton = new JButton("Verify");
        JButton cancelButton = new JButton("Cancel");
        resultLabel = new JLabel();

        // Add components to the panel
        panel.add(inputLabel);
        panel.add(textField);
        panel.add(resultLabel);

        // Add panel to the frame
        add(panel, BorderLayout.CENTER);

        // Add action listeners to the buttons
        verifyButton.addActionListener(new VerifyButtonListener());
        cancelButton.addActionListener(new CancelButtonListener());

        // Create a panel for the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(verifyButton);
        buttonPanel.add(cancelButton);

        // Add the button panel to the frame
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private class VerifyButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String input = textField.getText();

            try {
                int value = Integer.parseInt(input);
                resultLabel.setText("Valid integer entered: " + value);
            } catch (NumberFormatException ex) {
                resultLabel.setText("Invalid integer entered.");
            }
        }
    }

    private class CancelButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            dispose(); // Close the window
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            IntegerVerificationApp app = new IntegerVerificationApp();
            app.setVisible(true);
        });
    }
}