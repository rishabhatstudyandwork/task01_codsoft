import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGuessingGameTask1 {
    private final JTextField guessField;
    private final JLabel messageLabel;
    private final JLabel attemptsLabel;
    private final JLabel roundLabel;
    private final JLabel winsLabel;
    private final JButton guessButton;
    private final Random random;
    private int numberToGuess;
    private int attempts;
    private final int maxAttempts;
    private int totalRounds;
    private int totalWins;

    public NumberGuessingGameTask1() {
        random = new Random();
        maxAttempts = 10;
        totalRounds = 0;
        totalWins = 0;

        JFrame frame = new JFrame("Number Guessing Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(6, 1));

        roundLabel = new JLabel("Round: 1", SwingConstants.CENTER);
        winsLabel = new JLabel("Wins: 0", SwingConstants.CENTER);
        messageLabel = new JLabel("Guess a number between 1 and 100", SwingConstants.CENTER);
        attemptsLabel = new JLabel("Attempts left: " + maxAttempts, SwingConstants.CENTER);

        guessField = new JTextField();
        guessButton = new JButton("Guess");
        JButton newGameButton = new JButton("New Game");

        guessButton.addActionListener(new GuessHandler());
        newGameButton.addActionListener(e -> startNewRound());

        frame.add(roundLabel);
        frame.add(winsLabel);
        frame.add(messageLabel);
        frame.add(attemptsLabel);
        frame.add(guessField);
        frame.add(guessButton);
        frame.add(newGameButton);

        frame.setVisible(true);

        startNewRound();
    }

    private void startNewRound() {
        numberToGuess = random.nextInt(100) + 1;
        attempts = 0;
        totalRounds++;
        roundLabel.setText("Round: " + totalRounds);
        winsLabel.setText("Wins: " + totalWins);
        attemptsLabel.setText("Attempts left: " + maxAttempts);
        messageLabel.setText("Guess a number between 1 and 100");
        guessField.setText("");
        guessField.setEnabled(true);
        guessButton.setEnabled(true);
    }

    private class GuessHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                int guess = Integer.parseInt(guessField.getText());
                attempts++;
                if (guess == numberToGuess) {
                    messageLabel.setText("Congratulations! You guessed it in " + attempts + " attempts.");
                    totalWins++;
                    winsLabel.setText("Wins: " + totalWins);
                    guessField.setEnabled(false);
                    guessButton.setEnabled(false);
                } else if (guess < numberToGuess) {
                    messageLabel.setText("Too low! Try again.");
                } else {
                    messageLabel.setText("Too high! Try again.");
                }
                attemptsLabel.setText("Attempts left: " + (maxAttempts - attempts));

                if (attempts >= maxAttempts && guess != numberToGuess) {
                    messageLabel.setText("Game Over! The number was: " + numberToGuess);
                    guessField.setEnabled(false);
                    guessButton.setEnabled(false);
                }
            } catch (NumberFormatException ex) {
                messageLabel.setText("Please enter a valid number!");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(NumberGuessingGameTask1::new);
    }
}