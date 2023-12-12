package Java.ProjektSpiele;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;



public class VierGewinnt extends JFrame implements ActionListener {

    public static void FunktionVierGewinnt() {
        VierGewinnt game = new VierGewinnt();
        game.setVisible(true);
    }

    private static final long serialVersionUID = 1L;
    private JButton[][] buttons;
    private JPanel gamePanel, startPanel, endPanel;
    private JLabel messageLabel;
    private JButton startButton, resetButton;
    private int[][] board;
    private boolean player1Turn;
    private boolean gameOver;

    public VierGewinnt() {
        super("4 Gewinnt");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(700, 600);
        setLocationRelativeTo(null);
        setVisible(true);

        gamePanel = new JPanel(new GridLayout(6, 7));
        buttons = new JButton[6][7];
        board = new int[6][7];
        player1Turn = true;
        gameOver = false;

        for (int row = 0; row < 6; row++) {
            for (int col = 0; col < 7; col++) {
                buttons[row][col] = new JButton();
                buttons[row][col].setBackground(Color.WHITE);
                buttons[row][col].setBorder(BorderFactory.createLineBorder(Color.BLACK));
                buttons[row][col].addActionListener(this);
                gamePanel.add(buttons[row][col]);
            }
        }

        startPanel = new JPanel(new BorderLayout());
        startButton = new JButton("Start");
        startButton.addActionListener(this);
        startPanel.add(startButton, BorderLayout.CENTER);

        endPanel = new JPanel(new BorderLayout());
        messageLabel = new JLabel();
        resetButton = new JButton("Zurück zum Startmenü");
        resetButton.addActionListener(this);
        endPanel.add(messageLabel, BorderLayout.CENTER);
        endPanel.add(resetButton, BorderLayout.SOUTH);

        getContentPane().add(startPanel);

        validate();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            getContentPane().remove(startPanel);
            getContentPane().add(gamePanel);
            validate();
        } else if (e.getSource() == resetButton) {
            getContentPane().remove(endPanel);
            variablenreset();               //funmktion zum zurücksetzen der variablen
            Startseite.main(null);     //aufrufen der main funktion, eigentlich falsch. ich kann mir nur keine andere möglichkeit vorstellen =P
            validate();
        } else if (!gameOver) {
            for (int row = 5; row >= 0; row--) {
                for (int col = 0; col < 7; col++) {
                    if (e.getSource() == buttons[row][col]) {
                        if (board[row][col] == 0) {
                            int player = player1Turn ? 1 : 2;
                            buttons[row][col].setBackground(player == 1 ? Color.RED : Color.YELLOW);
                            board[row][col] = player;
                            if (checkWin(row, col)) {
                                gameOver = true;
                                messageLabel.setText("Spieler " + player + " hat gewonnen!");
                                getContentPane().remove(gamePanel);
                                getContentPane().add(endPanel);
                                validate();
                            } else if (checkTie()) {
                                gameOver = true;
                                messageLabel.setText("Unentschieden!");
                                getContentPane().remove(gamePanel);
                                getContentPane().add(endPanel);
                                validate();
                            } else {
                                player1Turn = !player1Turn;
                            }
                        }
                        break;
                    }
                }
            }
        }
    }

    public void reset() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                board[i][j] = 0;
            }
        }
        player1Turn = true;
        gameOver = false;
        repaint();
    }
    
    
    public boolean checkTie() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (board[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
    

    public boolean checkWin(int row, int col) {
        int player = board[row][col];
        int count = 0;
        for (int i = col - 3; i <= col + 3; i++) {
            if (i >= 0 && i < 7 && board[row][i] == player) {
                count++;
                if (count == 4) {
                    return true;
                }
            } else {
                count = 0;
            }
        }
        count = 0;
        for (int i = row - 3; i <= row + 3; i++) {
            if (i >= 0 && i < 6 && board[i][col] == player) {
                count++;
                if (count == 4) {
                    return true;
                }
            } else {
                count = 0;
            }
        }
        count = 0;
        for (int i = -3; i <= 3; i++) {
            int r = row + i;
            int c = col + i;
            if (r >= 0 && r < 6 && c >= 0 && c < 7 && board[r][c] == player) {
                count++;
                if (count == 4) {
                    return true;
                }
            } else {
                count = 0;
            }
        }
        count = 0;
        for (int i = -3; i <= 3; i++) {
            int r = row - i;
            int c = col + i;
            if (r >= 0 && r < 6 && c >= 0 && c < 7 && board[r][c] == player) {
                count++;
                if (count == 4) {
                    return true;
                }
            } else {
                count = 0;
            }
        }
        return false;
    }
    //funktion zum zurücksetzen der variablen
    public void variablenreset()
    {
        buttons = null;
        gamePanel = null;
        startPanel = null;
        endPanel = null;
        messageLabel = null;
        startButton = null; 
        resetButton = null;
        board = null;
        player1Turn = false;
        gameOver = false;
    }
}
