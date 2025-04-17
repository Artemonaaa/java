package hw03;

import java.util.Scanner;

public class TaskPlanner {
    public static void main(String[] args) {
        String[][] scedule = {
                {"Sunday", "do home work"},
                {"Monday", "don't do home work"},
                {"Tuesday", "go to work"},
                {"Wednesday", "don't go to work"},
                {"Thursday", "sleep"},
                {"Friday", "more sleep"},
                {"Saturday", "ok"}
        };

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Please, input the day of the week:");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("exit")) {
                System.out.println("Goodbye!");
                break;
            }

            boolean found  = false;
            for (int i = 0; i < 7; i++) {
                if (scedule[i][0].toLowerCase().equals(input)) {
                    System.out.printf("Your tasks for %s: %s%n", scedule[i][0], scedule[i][1]);
                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println("Sorry, I don't understand you, please try again.");
            }
        }
    }
}
