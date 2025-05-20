package hw10.models.console;

import hw10.models.Family;
import hw10.models.FamilyService;
import hw10.models.Humans.Human;
import hw10.models.exceptions.FamilyOverflowException;

import java.time.DateTimeException;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FamilyConsoleApp {
    private final FamilyService familyService;
    private final Scanner scanner;

    public FamilyConsoleApp() {
        this.familyService = new FamilyService();
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        while (true) {
            printMenu();
            String command = scanner.nextLine().trim();
            try {
                switch (command) {
                    case "1" -> fillWithTestData();
                    case "2" -> displayAllFamilies();
                    case "3" -> displayFamiliesBiggerThan();
                    case "4" -> displayFamiliesLessThan();
                    case "5" -> countFamiliesWithMemberNumber();
                    case "6" -> createNewFamily();
                    case "7" -> deleteFamilyByIndex();
                    case "8" -> editFamily();
                    case "9" -> deleteAllChildrenOlderThan();
                    case "10" -> displayFamiliesWithCustomFilter();
                    case "exit" -> {
                        System.out.println("Вихід з програми.");
                        return;
                    }
                    default -> System.out.println("Невідома команда. Спробуйте ще раз.");
                }
            } catch (FamilyOverflowException e) {
                System.out.println("Помилка: " + e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Помилка: некоректний формат числа.");
            } catch (Exception e) {
                System.out.println("Сталася помилка: " + e.getMessage());
            }
        }
    }

    private void printMenu() {
        System.out.println("""
                Меню:
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
                Ваш вибір: """);
    }

    private void fillWithTestData() {
        familyService.createNewFamily(new Human("Іван", "Іванович", 1980), new Human("Марія", "Іванова", 1982));
        familyService.createNewFamily(new Human("Петро", "Петренко", 1975), new Human("Олена", "Петренко", 1978));
        familyService.createNewFamily(new Human("Олег", "Сидоренко", 1990), new Human("Ірина", "Сидоренко", 1991));
        System.out.println("Тестові дані додано.");
    }

    private void displayAllFamilies() {
        List<Family> families = familyService.getAllFamilies();
        if (families.isEmpty()) {
            System.out.println("Сімей немає.");
            return;
        }
        printFamilies(families);
    }

    private void displayFamiliesBiggerThan() {
        System.out.print("Введіть мінімальну кількість членів сім'ї: ");
        int count = Integer.parseInt(scanner.nextLine());
        List<Family> result = familyService.getAllFamilies().stream()
                .filter(family -> family.countFamily() > count)
                .collect(Collectors.toList());
        printFamilies(result);
    }

    private void displayFamiliesLessThan() {
        System.out.print("Введіть максимальну кількість членів сім'ї: ");
        int count = Integer.parseInt(scanner.nextLine());
        List<Family> result = familyService.getAllFamilies().stream()
                .filter(family -> family.countFamily() < count)
                .collect(Collectors.toList());
        printFamilies(result);
    }

    private void countFamiliesWithMemberNumber() {
        System.out.print("Введіть кількість членів сім'ї: ");
        int count = Integer.parseInt(scanner.nextLine());
        long number = familyService.getAllFamilies().stream()
                .filter(family -> family.countFamily() == count)
                .count();
        System.out.println("Кількість сімей з " + count + " членами: " + number);
    }

    private void createNewFamily() {
        System.out.println("Введіть інформацію про батька:");
        Human father = inputHuman();
        System.out.println("Введіть інформацію про матір:");
        Human mother = inputHuman();
        familyService.createNewFamily(father, mother);
        System.out.println("Нову родину створено.");
    }

    private Human inputHuman() {
        try {
            System.out.print("Ім'я: ");
            String name = scanner.nextLine();
            System.out.print("Прізвище: ");
            String surname = scanner.nextLine();

            System.out.print("Рік народження: ");
            int year = Integer.parseInt(scanner.nextLine());

            System.out.print("Місяць народження (1-12): ");
            int month = Integer.parseInt(scanner.nextLine());

            System.out.print("День народження (1-31): ");
            int day = Integer.parseInt(scanner.nextLine());

            System.out.print("IQ: ");
            int iq = Integer.parseInt(scanner.nextLine());

            String birthDateStr = String.format("%02d/%02d/%04d", day, month, year);

            return new Human(name, surname, birthDateStr, iq);
        } catch (NumberFormatException e) {
            System.out.println("Некоректний формат введення числового значення. Спробуйте ще раз.");
            return inputHuman();
        } catch (DateTimeException e) {
            System.out.println("Некоректна дата. Будь ласка, введіть правильну дату.");
            return inputHuman();
        }
    }
    private void deleteFamilyByIndex() {
        System.out.print("Введіть індекс сім'ї для видалення: ");
        int index = Integer.parseInt(scanner.nextLine()) - 1;
        familyService.deleteFamilyByIndex(index);
        System.out.println("Сім'ю видалено.");
    }

    private void editFamily() {
        System.out.print("Введіть індекс сім'ї для редагування: ");
        int index = Integer.parseInt(scanner.nextLine()) - 1;

        Family family = familyService.getFamilyByIndex(index);
        if (family == null) {
            System.out.println("Сім’я з таким індексом не знайдена.");
            return;
        }

        System.out.println("Редагування сім’ї:");
        System.out.println("1. Додати дитину (усиновлення)");
        System.out.println("2. Народити дитину");
        System.out.print("Ваш вибір: ");
        String choice = scanner.nextLine();

        try {
            switch (choice) {
                case "1" -> {
                    Human child = inputHuman();
                    familyService.adoptChild(index, child);
                    System.out.println("Дитину додано до сім'ї.");
                }
                case "2" -> {
                    System.out.print("Ім'я сина: ");
                    String boyName = scanner.nextLine();
                    System.out.print("Ім'я доньки: ");
                    String girlName = scanner.nextLine();
                    familyService.bornChild(index, boyName, girlName);
                    System.out.println("Дитина народжена.");
                }
                default -> System.out.println("Невірний вибір. Будь ласка, оберіть 1 або 2.");
            }
        } catch (FamilyOverflowException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    private void deleteAllChildrenOlderThan() {
        System.out.print("Введіть вік: ");
        int age = Integer.parseInt(scanner.nextLine());
        familyService.deleteAllChildrenOlderThan(age);
        System.out.println("Діти старше " + age + " років видалені.");
    }

    private void displayFamiliesWithCustomFilter() {
        System.out.println("Оберіть фільтр:");
        System.out.println("1. За мінімальною кількістю членів сім'ї");
        System.out.println("2. За роком народження батька");
        System.out.println("3. За роком народження матері");
        System.out.print("Ваш вибір: ");
        String choice = scanner.nextLine();

        Predicate<Family> filter;

        switch (choice) {
            case "1" -> {
                System.out.print("Введіть мінімальну кількість членів сім'ї: ");
                int minCount = Integer.parseInt(scanner.nextLine());
                filter = family -> family.countFamily() >= minCount;
            }
            case "2" -> {
                System.out.print("Введіть мінімальний рік народження батька: ");
                int minYear = Integer.parseInt(scanner.nextLine());
                filter = family -> family.getFather() != null && family.getFather().getAge() >= minYear;
            }
            case "3" -> {
                System.out.print("Введіть мінімальний рік народження матері: ");
                int minYear = Integer.parseInt(scanner.nextLine());
                filter = family -> family.getMother() != null && family.getMother().getAge() >= minYear;
            }
            default -> {
                System.out.println("Невірний вибір фільтра.");
                return;
            }
        }

        List<Family> filtered = familyService.getAllFamilies().stream()
                .filter(filter)
                .collect(Collectors.toList());

        printFamilies(filtered);
    }

    private void printFamilies(List<Family> families) {
        if (families.isEmpty()) {
            System.out.println("Сімей, що відповідають критеріям, не знайдено.");
            return;
        }
        IntStream.range(0, families.size())
                .forEach(i -> System.out.println((i + 1) + ". " + families.get(i).prettyFormat()));
    }
}
