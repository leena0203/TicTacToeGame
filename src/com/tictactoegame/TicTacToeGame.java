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
					// countEntry++;

				} else {
					System.out.println("Already filled index");
					markEntry(input, symbol, chance);
				}
			}
		}
		}
		else {
			int entryRow = getWin(computer);
			int entryColumn = getWin1(computer);
			if(entryRow != 0) {
				if (entryColumn != 0) {
				makeMove(entryRow,entryColumn,computer, board);
			}
			countEntry++;
			checkGameStatus(input,computer,Chance.CompPlayer);
			}
		}
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
	 * 
	 * @param args
	 */
	public static int getWin(char symbol) {
		int entryRow = 0;
		int entryColumn = 0;
		char[][] copyBoard = board;
		for(int iteration = 0; iteration < 3 ; iteration++) {
			if(copyBoard[entryRow][entryColumn] == ' ') {
				makeMove(iteration,entryColumn, symbol, copyBoard);
				if(checkForWin(copyBoard,symbol)) {
					entryRow = iteration;
					break;
				}
			}
		}
		return entryRow; 
	}
	
	/**
	 * UC8
	 * @param symbol
	 * @return
	 */
	public static int getWin1(char symbol) {
		int entryRow = 0;
		int entryColumn = 0;
		char[][] copyBoard = board;
		for(int i = 0; i < 3 ; i++) {
			if(copyBoard[entryRow][entryColumn] == ' ') {
				makeMove(entryRow,i, symbol, copyBoard);
				if(checkForWin(copyBoard,symbol)) {
					entryColumn = i;
					break;
				}
			}
		}
		return entryColumn; 
	}
	
		
	public static void main(String[] args) {
		TicTacToeGame game = new TicTacToeGame();
		TicTacToeGame.createBoard();
		TicTacToeGame.inputMark(input);
		TicTacToeGame.tossToCheck();

	}
}
