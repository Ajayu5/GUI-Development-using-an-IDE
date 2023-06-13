import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorProgram extends JFrame {
    private JTextField numField1;
    private JTextField numField2;
    private JLabel resultLabel;

    public CalculatorProgram() {
        setTitle("Simple Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        // Create the input fields
        numField1 = new JTextField(10);
        numField2 = new JTextField(10);

        // Create the buttons
        JButton addButton = new JButton("Add");
        JButton subtractButton = new JButton("Subtract");
        JButton multiplyButton = new JButton("Multiply");
        JButton divideButton = new JButton("Divide");

        // Create the result label
        resultLabel = new JLabel("Result: ");

        // Create the panel and add components
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);

        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(new JLabel("Number 1:"), constraints);

        constraints.gridx = 1;
        panel.add(numField1, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(new JLabel("Number 2:"), constraints);

        constraints.gridx = 1;
        panel.add(numField2, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(addButton, constraints);

        constraints.gridx = 1;
        panel.add(subtractButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(multiplyButton, constraints);

        constraints.gridx = 1;
        panel.add(divideButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        panel.add(resultLabel, constraints);

        // Add the panel to the frame
        add(panel);

        // Register action listeners for the buttons
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculateResult(Operation.ADD);
            }
        });

        subtractButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculateResult(Operation.SUBTRACT);
            }
        });

        multiplyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculateResult(Operation.MULTIPLY);
            }
        });

        divideButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculateResult(Operation.DIVIDE);
            }
        });
    }

    private void calculateResult(Operation operation) {
        try {
            double num1 = Double.parseDouble(numField1.getText());
            double num2 = Double.parseDouble(numField2.getText());
            double result = 0;

            switch (operation) {
                case ADD:
                    result = num1 + num2;
                    break;
                case SUBTRACT:
                    result = num1 - num2;
                    break;
                case MULTIPLY:
                    result = num1 * num2;
                    break;
                case DIVIDE:
                    result = num1 / num2;
                    break;
            }

            resultLabel.setText("Result: " + result);
        } catch (NumberFormatException ex) {
            resultLabel.setText("Invalid input");
        } catch (ArithmeticException ex) {
            resultLabel.setText("Division by zero");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CalculatorProgram().setVisible(true);
            }
        });
    }

    private enum Operation {
        ADD, SUBTRACT, MULTIPLY, DIVIDE
    }
}