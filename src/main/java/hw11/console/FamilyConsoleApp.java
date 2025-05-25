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
                case "0" -> controller.fillWithTestData();
                case "1" -> controller.loadData();
                case "2" -> controller.saveData();
                case "3" -> controller.displayAllFamilies();
                case "4" -> controller.displayFamiliesBiggerThan(scanner);
                case "5" -> controller.displayFamiliesLessThan(scanner);
                case "6" -> controller.countFamiliesWithMemberNumber(scanner);
                case "7" -> controller.createNewFamily(scanner);
                case "8" -> controller.deleteFamilyByIndex(scanner);
                case "9" -> controller.editFamily(scanner);
                case "10" -> controller.deleteAllChildrenOlderThan(scanner);
                case "-1", "exit" -> {
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
                0. Заповнити тестовими даними
                1. Завантажити дані
                2. Зберегти дані
                3. Відобразити весь список сімей
                4. Відобразити список сімей, де кількість людей більша за задану
                5. Відобразити список сімей, де кількість людей менша за задану
                6. Підрахувати кількість сімей, де кількість членів дорівнює
                7. Створити нову родину
                8. Видалити сім'ю за індексом
                9. Редагувати сім'ю за індексом
                10. Видалити всіх дітей старше віку
                11. Показати сім’ї за кастомним фільтром
                Введіть 'exit' для виходу з програми
                ╚══════════════════════╝
                Введіть команду:""");
    }
}

