package byteUprise;

import java.util.Scanner;

public class task1_TicTacToe {
        private char[][] board;
        private char currentPlayer;
        private int movesCount;

        public task1_TicTacToe() {
            board = new char[3][3];
            currentPlayer = 'X';
            movesCount = 0;
            initializeBoard();
        }

        // Initialize the board with empty spaces
        private void initializeBoard() {
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    board[i][j] = ' ';
                }
            }
        }

        // Print the board
        private void printBoard() {
            System.out.println("Current board:");
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    System.out.print(board[i][j]);
                    if (j < 2) System.out.print("|");
                }
                System.out.println();
                if (i < 2) System.out.println("-----");
            }
        }

        // Check if the move is valid
        private boolean isMoveValid(int row, int col) {
            return row >= 0 && row < 3 && col >= 0 && col < 3 && board[row][col] == ' ';
        }

        // Make a move
        private boolean makeMove(int row, int col) {
            if (isMoveValid(row, col)) {
                board[row][col] = currentPlayer;
                movesCount++;
                return true;
            }
            return false;
        }

        // Check for a win
        private boolean checkWin() {
            // Check rows
            for (int i = 0; i < 3; i++) {
                if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
                    return true;
                }
            }

            // Check columns
            for (int i = 0; i < 3; i++) {
                if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer) {
                    return true;
                }
            }

            // Check diagonals
            if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) {
                return true;
            }
            if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) {
                return true;
            }

            return false;
        }

        // Check for a draw
        private boolean checkDraw() {
            return movesCount == 9;
        }

        // Switch player
        private void switchPlayer() {
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }

        // Main game loop
        public void playGame() {
            Scanner scanner = new Scanner(System.in);
            boolean gameEnd = false;

            while (!gameEnd) {
                printBoard();
                System.out.println("Player " + currentPlayer + ", enter your move (row and column): ");
                int row = scanner.nextInt();
                int col = scanner.nextInt();

                if (makeMove(row, col)) {
                    if (checkWin()) {
                        printBoard();
                        System.out.println("Player " + currentPlayer + " wins!");
                        gameEnd = true;
                    } else if (checkDraw()) {
                        printBoard();
                        System.out.println("The game is a draw!");
                        gameEnd = true;
                    } else {
                        switchPlayer();
                    }
                } else {
                    System.out.println("This move is not valid. Try again.");
                }
            }

            scanner.close();
        }

        public static void main(String[] args) {
            task1_TicTacToe game = new task1_TicTacToe();
            game.playGame();
        }

}
