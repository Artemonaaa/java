package hw11.console;

import hw11.controller.FamilyController;

import java.util.Scanner;

public class FamilyConsoleApp {
    private static final Scanner scanner = new Scanner(System.in);
    private static final FamilyController controller = new FamilyController();

    public void run() {
        boolean running = true;
        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> controller.fillWithTestData();
                case "2" -> controller.displayAllFamilies();
                case "3" -> controller.displayFamiliesBiggerThan(scanner);
                case "4" -> controller.displayFamiliesLessThan(scanner);
                case "5" -> controller.countFamiliesWithMemberNumber(scanner);
                case "6" -> controller.createNewFamily(scanner);
                case "7" -> controller.deleteFamilyByIndex(scanner);
                case "8" -> controller.editFamily(scanner);
                case "9" -> controller.deleteAllChildrenOlderThan(scanner);
                case "0", "exit" -> {
                    running = false;
                    System.out.println("Завершення програми.");
                }
                default -> System.out.println("Невірна команда. Спробуйте ще раз.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("""
                
                ╔════════ МЕНЮ ════════╗
                1. Заповнити тестовими даними
                2. Відобразити весь список сімей
                3. Відобразити список сімей, де кількість людей більша за задану
                4. Відобразити список сімей, де кількість людей менша за задану
                5. Підрахувати кількість сімей, де кількість членів дорівнює
                6. Створити нову родину
                7. Видалити сім'ю за індексом
                8. Редагувати сім'ю за індексом
                9. Видалити всіх дітей старше віку
                10. Показати сім’ї за кастомним фільтром
                Введіть 'exit' для виходу з програми
                ╚══════════════════════╝
                Введіть команду:""");
    }
}

