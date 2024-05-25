package byteUprise;

import java.util.Scanner;

public class task4_SudokuSolver {
        private static final int SIZE = 9;
        private int[][] grid;

        public task4_SudokuSolver(int[][] grid) {
            this.grid = grid;
        }

        public boolean solveSudoku() {
            int row = -1;
            int col = -1;
            boolean isEmpty = true;

            int num;
            for(num = 0; num < 9; ++num) {
                for(int j = 0; j < 9; ++j) {
                    if (this.grid[num][j] == 0) {
                        row = num;
                        col = j;
                        isEmpty = false;
                        break;
                    }
                }

                if (!isEmpty) {
                    break;
                }
            }

            if (isEmpty) {
                return true;
            } else {
                for(num = 1; num <= 9; ++num) {
                    if (this.isSafe(row, col, num)) {
                        this.grid[row][col] = num;
                        if (this.solveSudoku()) {
                            return true;
                        }

                        this.grid[row][col] = 0;
                    }
                }

                return false;
            }
        }

        private boolean isSafe(int row, int col, int num) {
            int startRow;
            for(startRow = 0; startRow < 9; ++startRow) {
                if (this.grid[row][startRow] == num) {
                    return false;
                }
            }

            for(startRow = 0; startRow < 9; ++startRow) {
                if (this.grid[startRow][col] == num) {
                    return false;
                }
            }

            startRow = row - row % 3;
            int startCol = col - col % 3;

            for(int i = 0; i < 3; ++i) {
                for(int j = 0; j < 3; ++j) {
                    if (this.grid[i + startRow][j + startCol] == num) {
                        return false;
                    }
                }
            }

            return true;
        }

        public void displaySudoku() {
            for(int i = 0; i < 9; ++i) {
                if (i % 3 == 0 && i != 0) {
                    System.out.println("---------------------");
                }

                for(int j = 0; j < 9; ++j) {
                    if (j % 3 == 0 && j != 0) {
                        System.out.print("| ");
                    }

                    System.out.print(this.grid[i][j] + " ");
                }

                System.out.println();
            }

        }

        public static void main(String[] args) {
            int[][] puzzle = new int[9][9];
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the Sudoku puzzle (use 0 to represent empty cells):");

            for (int i = 0; i < 9; ++i) {
                System.out.printf("Enter row %d: ", i + 1);

                for (int j = 0; j < 9; ++j) {
                    puzzle[i][j] = scanner.nextInt();
                }
            }

            task4_SudokuSolver solver = new task4_SudokuSolver(puzzle);
            if (solver.solveSudoku()) {
                System.out.println("Sudoku puzzle solved:");
                solver.displaySudoku();
            } else {
                System.out.println("No solution exists for the Sudoku puzzle.");
            }
        }
}
