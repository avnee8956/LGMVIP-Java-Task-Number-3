import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToeGame extends JFrame {
    private JButton[][] board;
    private boolean xTurn;
    
    public TicTacToeGame() {
        setTitle("Tic-Tac-Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLayout(new GridLayout(3, 3));
        
        xTurn = true;
        board = new JButton[3][3];
        
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                JButton button = new JButton();
                button.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 60));
                button.addActionListener(new ButtonListener(row, col));
                add(button);
                board[row][col] = button;
            }
        }
    }
    
    private class ButtonListener implements ActionListener {
        private int row;
        private int col;
        
        public ButtonListener(int row, int col) {
            this.row = row;
            this.col = col;
        }
        
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton)e.getSource();
            
            if (button.getText().equals("")) {
                if (xTurn) {
                    button.setText("X");
                } else {
                    button.setText("O");
                }
                
                if (checkWin()) {
                    String winner = xTurn ? "X" : "O";
                    JOptionPane.showMessageDialog(null, "Player " + winner + " wins!");
                    resetBoard();
                } else if (checkDraw()) {
                    JOptionPane.showMessageDialog(null, "It's a draw!");
                    resetBoard();
                } else {
                    xTurn = !xTurn;
                }
            }
        }
        
        private boolean checkWin() {
            String symbol = xTurn ? "X" : "O";
            
            // Check rows
            for (int i = 0; i < 3; i++) {
                if (board[i][0].getText().equals(symbol) &&
                    board[i][1].getText().equals(symbol) &&
                    board[i][2].getText().equals(symbol)) {
                    return true;
                }
            }
            
            // Check columns
            for (int i = 0; i < 3; i++) {
                if (board[0][i].getText().equals(symbol) &&
                    board[1][i].getText().equals(symbol) &&
                    board[2][i].getText().equals(symbol)) {
                    return true;
                }
            }
            
            // Check diagonals
            if (board[0][0].getText().equals(symbol) &&
                board[1][1].getText().equals(symbol) &&
                board[2][2].getText().equals(symbol)) {
                return true;
            }
            
            if (board[0][2].getText().equals(symbol) &&
                board[1][1].getText().equals(symbol) &&
                board[2][0].getText().equals(symbol)) {
                return true;
            }
            
            return false;
        }
        
        private boolean checkDraw() {
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    if (board[row][col].getText().equals("")) {
                        return false;
                    }
                }
            }
            
            return true;
        }
        
        private void resetBoard() {
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    board[row][col].setText("");
                }
            }
            
            xTurn = true;
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TicTacToeGame().setVisible(true);
            }
        });
    }
}
