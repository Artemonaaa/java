package hw01;

import java.util.Random;
import java.util.Scanner;

public class Hello1 {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Random rand = new Random();
    int number = rand.nextInt(101);

    int[] guesses = new int[0];

    System.out.println("Enter your name: ");
    String name = scanner.nextLine();

    System.out.println("Let the game begin!");

    while (true) {
      System.out.println("Enter your number: ");

      while (!scanner.hasNextInt()) {
        System.out.println("That's not a valid number! Please enter a valid number: ");
        scanner.next();
      }

      int checkNumber = scanner.nextInt();

      int[] newGuesses = new int[guesses.length + 1];
      for (int i = 0; i < guesses.length; i++) {
        newGuesses[i] = guesses[i];
      }
      newGuesses[guesses.length] = checkNumber;
      guesses = newGuesses;

      if (checkNumber == number) {
        System.out.println("Congratulations, " + name + '!');
        break;
      } else if (checkNumber > number) {
        System.out.println("Your number is too big. Please, try again..");
      } else {
        System.out.println("Your number is too small. Please, try again..");
      }
    }

    for (int i = 0; i < guesses.length - 1; i++) {
      for (int j = i + 1; j < guesses.length; j++) {
        if (guesses[i] < guesses[j]) {
          int temp = guesses[i];
          guesses[i] = guesses[j];
          guesses[j] = temp;
        }
      }
    }

    System.out.print("Your numbers: ");
    for (int i = 0; i < guesses.length; i++) {
      System.out.print(guesses[i]);
      if (i < guesses.length - 1) {
        System.out.print(", ");
      }
    }
    System.out.println();
  }
}
