package com.tictactoegame;

import java.util.Scanner;

public class TicTacToeGame {
	static Scanner input = new Scanner(System.in);
	static Scanner inputcolumn = new Scanner(System.in);
	public static char[][] board;
	public static char computer;
	public static char player;

	/**
	 * Constructor
	 */
	public TicTacToeGame() {
		board = new char[3][3];
		createBoard();
		System.out.println("Welcome to Tic Tac Toe");
	}

	/**
	 * UC1
	 */
	public static void createBoard() {
		for (int i = 1; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				board[i][j] = ' ';
			}
		}

	}

	/**
	 * UC2
	 * 
	 * @param input
	 */
	public static void inputMark(Scanner input) {
		System.out.println("Enter your input mark X or O: ");
		player = input.next().charAt(0);
		if (player == 'X' || player == 'O') {
			switch (player) {
			case 'X':
				computer = 'O';
				break;
			case 'O':
				computer = 'X';
				break;
			default:
				System.out.println("Ivalid input");
				break;
			}
			System.out.println("Your mark is: " + player);
			System.out.println("Computer mark is: " + computer);

		}

	}

	/**
	 * UC3
	 */

	public static void printBorad(char[][] board) {
		System.out.println("-------------");
		for (int i = 0; i < 3; i++) {
			System.out.print("| ");
			for (int j = 0; j < 3; j++) {
				System.out.print(board[i][j] + " | ");
			}
			System.out.println();
			System.out.println("-------------");
		}
	}

	/**
	 * UC4
	 * 
	 * @param input
	 * @param inputcolumn
	 * @param board
	 * @return
	 */

	public static boolean markEntry(Scanner input, Scanner inputcolumn, char[][] board) {
		while (true) {
			int entryRow = 0;
			int entryColumn = 0;
			System.out.println("Enter the index for row and coulmn:");
			entryRow = input.nextInt();
			entryColumn = inputcolumn.nextInt();
			if ((entryRow >= 0) && (entryRow < 3)) {
				if ((entryColumn >= 0) && (entryColumn < 3)) {
					board[entryRow][entryColumn] = '-';
					{
						board[entryRow][entryColumn] = player;
						printBorad(board);
						return true;
					}
				}
			} else {
				System.out.println("Already filled index");
				printBorad(board);
				return false;
			}
		}
	}

	public static void main(String[] args) {
		TicTacToeGame game = new TicTacToeGame();
		TicTacToeGame.createBoard();
		TicTacToeGame.inputMark(input);
		markEntry(input, inputcolumn, board);

	}

}
