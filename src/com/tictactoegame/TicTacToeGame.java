package com.tictactoegame;

import java.util.Scanner;

public class TicTacToeGame {
	static Scanner input = new Scanner(System.in);
	public static char[][] board;
	public static char computer;
	public static char player;
	public static int countEntry = 0;

	enum Chance {
		User, CompPlayer;
	}

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
	 */

	public static void markEntry(Scanner input, char symbol, Chance chance) {
		if (chance == Chance.User) {
			int entryRow = 0;
			int entryColumn = 0;
			System.out.println("Enter the index for row and coulmn:");
			entryRow = input.nextInt();
			entryColumn = input.nextInt();
			if ((entryRow >= 0) && (entryRow < 3)) {
				if ((entryColumn >= 0) && (entryColumn < 3)) {
					if (isBoardIndexFree(entryRow, entryColumn)) {
						makeMove(entryRow, entryColumn, symbol, board);
						printBorad(board);
						checkGameStatus(input, symbol, chance);
						countEntry++;

					} else {
						System.out.println("Already filled index");
						markEntry(input, symbol, chance);
					}
				}
			}
		} else {
			if(! getWin(symbol)) {
				if(! getWin(player)) {
					if(! playEdges(symbol)) {
						//if(! playCenter(symbol)) {
						//playSides(symbol);
					}
				}
			}
			checkGameStatus(input, computer, Chance.CompPlayer);
		}
	}

	/**
	 * UC11
	 * @param symbol
	 * @return
	 */

	private static boolean playCenter(char symbol) {
		// TODO Auto-generated method stub
		boolean flag = false;
		if (board[1][1] == ' ') {
			flag = true;
		}
		return false;
	}

	/**
	 * UC5
	 * 
	 * @param entryRow
	 * @param entryColumn
	 * @return
	 */
	public static boolean isBoardIndexFree(int entryRow, int entryColumn) {
		boolean isFree = false;
		if (board[entryRow][entryColumn] == ' ') {
			isFree = true;
		}
		return isFree;
	}

	/**
	 * UC5
	 * 
	 * @param entryRow
	 * @param entryColumn
	 * @param symbol
	 * @param board2
	 */

	private static void makeMove(int entryRow, int entryColumn, char symbol, char[][] board) {
		board[entryRow][entryColumn] = symbol;
		countEntry++;
	}

	public static int markEntryComputer() {
		int mark = (int) Math.floor(Math.random() * 10) % 9 + 1;
		/*
		 * if (board[mark][mark] == ' ') { board[mark][mark] = computer; countEntry++;
		 * printBorad(board);
		 */
		return mark;
	}

	/***
	 * UC6
	 * 
	 * @return
	 */

	public static void tossToCheck() {

		int random = (int) Math.floor((Math.random() * 2)) + 1;
		if (random == 1) {
			System.out.println("Player Start");
			markEntry(input, player, Chance.User);
		} else {
			System.out.println("Computer Starts");
			markEntry(input, computer, Chance.CompPlayer);
		}
	}

	/**
	 * UC7
	 * 
	 * @param board
	 * @param symbol
	 * @return
	 */

	public static boolean checkForWin(char[][] board, char symbol) {
		boolean flag = false;
		if (board[0][0] == board[0][1] && board[0][1] == board[0][2] && board[0][1] == symbol
				|| board[0][0] == board[1][0] && board[1][0] == board[2][0] && board[1][0] == symbol
				|| board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[1][1] == symbol
				|| board[0][1] == board[1][1] && board[1][1] == board[2][1] && board[1][1] == symbol
				|| board[0][2] == board[1][2] && board[1][2] == board[2][2] && board[1][2] == symbol
				|| board[0][2] == board[1][2] && board[1][1] == board[2][0] && board[1][1] == symbol
				|| board[1][0] == board[1][1] && board[1][1] == board[1][2] && board[1][1] == symbol
				|| board[2][0] == board[2][1] && board[2][1] == board[2][2] && board[2][1] == symbol) {
			flag = true;
		}
		return flag;
	}

	public static boolean checkTie() {
		boolean checkTie = false;
		if (countEntry == 9) {
			checkTie = true;
		}
		return checkTie;
	}

	private static void checkGameStatus(Scanner input2, char symbol, Chance chance) {
		if (checkForWin(board, symbol)) {
			if (symbol == player) {
				System.out.println(" You WON!");
			} else {
				System.out.println(" Computer WON!");
			}
		} else if (checkTie()) {
			System.out.println("Game came to a tie");
		} else {
			if (chance == Chance.User) {
				markEntry(input, computer, Chance.CompPlayer);
			} else {
				markEntry(input, player, Chance.User);
			}
		}
	}

	/**
	 * UC8
	 * 
	 * @param args
	 */
	public static boolean getWin(char symbol) {
		boolean flag = false;
		int entryRow = 0;
		int entryColumn = 0;
		char[][] copyBoard = board;
		for (int iteration = 0; iteration < 3; iteration++) {
			for (int i = 0; i < 3; i++) {
				if (copyBoard[entryRow][entryColumn] == ' ') {
					makeMove(iteration, i, symbol, copyBoard);
					if (checkForWin(copyBoard, symbol)) {
						entryRow = iteration;
						entryColumn = i;
						makeMove(iteration, i, symbol, board);
						countEntry++;
						flag = true;
						break;
					}
				}
			}
		}
		return flag;
	}
	/**
	 * UC10
	 * @param symbol
	 * @return
	 */
	public static boolean playEdges(char symbol) {
		boolean flag = false;
		int entryRow = 0;
		int entryColumn = 0;
		for (int iteration = 0; iteration < 3; iteration = iteration+2) {
			for (int i = 0; i < 3;i = i+2) {	
				if (board[iteration][i] == ' ' ) {
					flag = true;
					makeMove(iteration, i, symbol, board);
					countEntry++;
					break;
				}
			}
		}
		return flag;
	}
	/**
	 * UC11
	 * @param symbol
	 */

		public static void playSides(char symbol) {
			int entryRow = 0;
			int entryColumn = 0;
			for (int i = 0; i < 3; i = i+2) {	
				if (board[i][1] == ' ' ) {
					makeMove(i, 1, symbol, board);
					countEntry++;
					break;
				}
				else if(board[1][0] == ' ') {
					makeMove(1, 0, symbol, board);
					countEntry++;
				}
				else {
					makeMove(1, 2, symbol, board);
				}
			}
	
		}
	public static void resetGame(Scanner input) {
		createBoard();
		computer = ' ';
		player = ' ';
		countEntry = 0;
		inputMark(input);
		printBorad(board);
		tossToCheck();
	}
	public static void main(String[] args) {
		TicTacToeGame game = new TicTacToeGame();
		TicTacToeGame.createBoard();
		TicTacToeGame.inputMark(input);
		TicTacToeGame.tossToCheck();
		resetGame(input);

	}
}
