package da;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Container pane;
	private String currentPayer; 
	private CustomButtons[][] board;
	private boolean win = false;
	int player1Score=0, player2Scoare=0;
	private String player1Name=null, player2Name=null;
	
	public GUI() {
		super();
		pane = getContentPane();
		pane.setLayout(new GridLayout(3,3));
		setBounds(600, 100, 600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		board = new CustomButtons[3][3];
		initBoard();
		getNames();
		setVisible(true);
		setTitle("Tic-Tac-Toe");
		setResizable(false);
		currentPayer = "X";
		game();
		
	}

	public void getNames() {
		board[0][0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (player1Name == null && player2Name == null) {
					player1Name = JOptionPane.showInputDialog(null, "What is your name?", "Player 1", JOptionPane.INFORMATION_MESSAGE);
					player2Name = JOptionPane.showInputDialog(null, "What is your name?", "Player 2", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
	}
	
	public void initBoard() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				board[i][j] = new CustomButtons(i, j);
				board[i][j].setBackground(Color.black);
				board[i][j].setForeground(Color.white);
				board[i][j].setOpaque(true);
				board[i][j].setText("");
			}
		}
		currentPayer = "X";
	}

	
	private void game() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				CustomButtons button = new CustomButtons(i,j);
				button.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 100));
				button.setBackground(Color.black);
				button.setForeground(Color.white);
				button.setOpaque(true);
				board[i][j] = button;
				CustomButtons e1 = new CustomButtons(i, j);
				button.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						int response;
						if (player1Name == null && player2Name == null) {
							player1Name = JOptionPane.showInputDialog(null, "What is your name?", "Player 1", JOptionPane.INFORMATION_MESSAGE);
							player2Name = JOptionPane.showInputDialog(null, "What is your name?", "Player 2", JOptionPane.INFORMATION_MESSAGE);
						}
						if (!isVisited(e1.getCoordX(), e1.getCoordY()) && !win) {
							button.setText(currentPayer);
							checkWin(e1.getCoordX(), e1.getCoordY());
							if (win && currentPayer.equals("X")) {
								response = JOptionPane.showConfirmDialog(null, player1Name+" wins!\nScore \n"+player1Name+" : "
										+(++player1Score)+" points\n"+player2Name+" : "+player2Scoare+" points\nPlay again?", "Results",JOptionPane.YES_NO_OPTION);
								if (response == JOptionPane.YES_OPTION)
									restart();
								else
									System.exit(0);
							} else if (win && currentPayer.equals("0")) {
								response = JOptionPane.showConfirmDialog(null, "Player 2 wins!\nScore \nPlayer 1 : "
							+player1Score+" points\n\tPlayer 2 : "+(++player2Scoare)+" points", "Results\nPlay again?",JOptionPane.YES_NO_OPTION);
								if (response == JOptionPane.YES_OPTION)
									restart();
								else
									System.exit(0);
							} else if (isFull()) {
								response = JOptionPane.showConfirmDialog(null, "Draw!\nScore \nPlayer 1 : "
										+player1Score+" points\n\tPlayer 2 : "+player2Scoare+" points\nPlay again?", "Results",JOptionPane.YES_NO_OPTION);
								if (response == JOptionPane.YES_OPTION)
									restart();
								else
									System.exit(0);
							} else
								toggleCurrentPlayer();
						}
						
					}
				});
				pane.add(button);
			}
		}
	}
	
	public void restart() {
		currentPayer = "X";
		win = false;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				board[i][j].setText("");
			}
		}
	}
	
	public boolean isFull() {
		int index=0;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (!board[i][j].getText().equals(""))
					index++;
			}
		}
		if (index==9)
			return true;
		else
			return false;
	}
	
	public void toggleCurrentPlayer() {
		if (currentPayer.equals("X"))
			currentPayer = "0";
		else
			currentPayer = "X";
	}
	
	public void checkWin(int x, int y) {
		int row=0, col=0, diag=0;
		for (int i = 0; i < board.length; i++) {
			if (board[i][y].getText().equals(currentPayer))
				col++;
		}
		for (int j = 0; j < board.length; j++) {
			if (board[x][j].getText().equals(currentPayer))
				row++;
		}
		if (x==y) {
			for (int i = 0; i < board.length; i++) {
				if (board[i][i].getText().equals(currentPayer))
					diag++;
			}
		}
		if (x+y==2) {
			diag = 0;
			for (int i = 0; i < board.length; i++) {
				if (board[i][2-i].getText().equals(currentPayer))
					diag++;
			}
		}
		if (row == 3 || col == 3 || diag == 3)
			win = true;
	}
	
	public boolean isVisited(int x, int y) {
		if (board[x][y].getText().equals(""))
			return false;
		else
			return true;
	}
	
}
