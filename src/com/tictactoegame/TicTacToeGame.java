package com.tictactoegame;

public class TicTacToeGame {
	public static char[][] board;
	public char computer, player;

	/**
	 * Constructor
	 */
	public TicTacToeGame() {
		board = new char[3][3];
		createBoard();
	}

	/**
	 * UC1
	 */
	public static void createBoard() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				board[i][j] = ' ';
			}
		}

	}

	public static void main(String[] args) {
		TicTacToeGame game = new TicTacToeGame();
		createBoard();
	}

}
