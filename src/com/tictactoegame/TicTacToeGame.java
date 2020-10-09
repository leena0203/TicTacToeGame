package com.tictactoegame;

import java.util.Scanner;

public class TicTacToeGame {
	static Scanner input = new Scanner(System.in);
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
		for (int i = 0; i < 3; i++) {
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

	public static void printBorad() {
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

	public static void main(String[] args) {
		TicTacToeGame game = new TicTacToeGame();
		TicTacToeGame.createBoard();
		TicTacToeGame.inputMark(input);
		TicTacToeGame.printBorad();
	}

}
