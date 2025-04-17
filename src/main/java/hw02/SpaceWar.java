import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;

public class SpaceWar {
    static private final int SIZE_BOARD = 5;

    public static void main(String[] args) {
        char[][] board = new char[SIZE_BOARD][SIZE_BOARD];
        for (char[] row : board) {
            Arrays.fill(row, '-');
        }

        Random random = new Random();
        int targetRow = random.nextInt(SIZE_BOARD) + 1;
        int targetCol = random.nextInt(SIZE_BOARD) + 1;

        // System.out.printf("%d %d\n", targetRow, targetCol);

        targetRow -= 1;
        targetCol -= 1;

        System.out.println("All Set. Get ready to rumble!");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            int row = getValidInput(scanner, "Enter row (1-5): ") - 1;
            int col = getValidInput(scanner, "Enter column (1-5): ") - 1;

            if (board[row][col] == '*') {
                System.out.println("You've already shot here. Try again.");
                continue;
            }

            if (row == targetRow && col == targetCol) {
                board[row][col] = 'x';
                printBoard(board);
                System.out.println("You have won!");
                break;
            } else {
                board[row][col] = '*';
                printBoard(board);
            }
        }

        scanner.close();
    }

    public static int getValidInput(Scanner scanner, String prompt) {
        int num;
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                num = scanner.nextInt();
                if (num >= 1 && num <= 5) {
                    break;
                }
            } else {
                scanner.next();
            }
            System.out.println("Invalid input. Please enter a number from 1 to 5.");
        }
        return num;
    }

    public static void printBoard(char[][] board) {
        System.out.print("   |");
        for (int i = 1; i <= board.length; i++) {
            System.out.printf(" %d |", i);
        }
        System.out.println();

        for (int i = 0; i < board.length; i++) {
            System.out.printf(" %d |", i + 1);
            for (int j = 0; j < board[i].length; j++) {
                System.out.printf(" %c |", board[i][j]);
            }
            System.out.println();
        }
    }
}
