import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Text;
public class TicTacToe {
    int boardwidth = 600;
    int boardheight= 650; //50px for text panel on the top

    JFrame frame = new JFrame("_Tic-Tac-Toe_");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();

    JButton[][] board = new JButton[3][3];
    String player1 = "X";
    String player2 = "O";
    String currentPlayer = player1;

    boolean gameOver = false;
    int turns = 0;

    TicTacToe() {
        frame.setVisible(true);
        frame.setSize(boardheight, boardwidth);
        frame.setLocationRelativeTo(null);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textLabel.setBackground(Color.gray);
        textLabel.setForeground(Color.black);
        textLabel.setFont(new Font("Arial", Font.BOLD, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Tic-Tac-Toe");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(3, 3));
        boardPanel.setBackground(Color.gray);
        frame.add(boardPanel);

        for (int r = 0; r < 3; r++){
            for (int c= 0; c< 3; c++){
                JButton tile = new JButton();
                board[r][c] = tile;
                boardPanel.add(tile);

                tile.setBackground(Color.black);
                tile.setForeground(Color.gray);
                tile.setFont(new Font("Arial",Font.BOLD,120));
                tile.setFocusable(false);
               // tile.setText(currentPlayer);

               tile.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    if (gameOver) return;
                    JButton tile = (JButton) e.getSource();
                    if (tile.getText()== ""){
                        tile.setText(currentPlayer);
                        turns++;
                        checkWinner();
                        if (!gameOver){
                            currentPlayer = currentPlayer == player1 ? player2 : player1;
                            textLabel.setText(currentPlayer + " 's turn to play");
                        }

                        
                    }
                    

                }
               });
            }
        }
       }
       void checkWinner(){
        //horizontal win
        for (int r=0 ; r<3 ; r++ ){
        if(board[r][0].getText() == "")continue;

        if (board[r][0].getText() == board[r][1].getText() &&
            board[r][1].getText() == board[r][2].getText()){
            for (int i =0; i<3;i++){
                setWinner(board[r][i]);
            }
                gameOver = true;
                return;

            }
               }
               //vertical win
               for (int c = 0; c<3 ; c++){
                if (board[0][c].getText()== "")continue;

                if (board[0][c].getText()== board[1][c].getText()&&
                    board[1][c].getText() == board[2][c].getText()){
                        for (int i=0; i<3; i++){
                            setWinner(board[i][c]);
                        }

                        gameOver = true;
                        return;
                    }
                
               }
               //diagonal win
               if (board[0][0].getText()== board[1][1].getText()&&
                    board[1][1].getText() == board[2][2].getText()&&
                    board[0][0].getText()!= ""){
                    for (int i=0; i<3; i++){
                        setWinner(board[i][i]);

                }
                gameOver = true;
                return;
            }

            //Antidiagonal win
            if (board[0][2].getText()== board[1][1].getText()&&
                    board[1][1].getText() == board[2][0].getText()&&
                    board[0][2].getText()!= ""){
                        setWinner(board[0][2]);
                        setWinner(board[1][1]);
                        setWinner(board[2][0]);
                        gameOver = true;
                        return;
                   
                    }
                    //tie
                    if (turns == 9){
                        for( int r = 0; r < 3; r++){
                            for(int c=0 ; c<3; c++){
                                setTie(board[r][c]);
                            }
                        }
                        gameOver = true;
                    }
               
}
    void setWinner(JButton tile){
        tile.setForeground(Color.blue);
        tile.setBackground(Color.darkGray);
        textLabel.setText(currentPlayer +  " won");
        
    }
    void setTie(JButton tile){
        tile.setForeground(Color.orange);
        tile.setBackground(Color.gray);
        textLabel.setText(" tie, try again");
    }
    public static void main(String[]args){
        TicTacToe game = new TicTacToe();
    
    // Create a reset button and add it to the frame
    JButton resetButton = new JButton("fail again");
    resetButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            game.resetGame();
        }
    });
    game.frame.add(resetButton, BorderLayout.SOUTH);
    }

  // Method to reset the game
  void resetGame() {
    currentPlayer = player1;
    gameOver = false;
    turns = 0;
    textLabel.setText("Tic-Tac-Toe");

    // Reset buttons
    for (int r = 0; r < 3; r++) {
        for (int c = 0; c < 3; c++) {
            board[r][c].setText("");
            board[r][c].setForeground(Color.darkGray);
            board[r][c].setBackground(Color.black);
        }
    }
}
    }