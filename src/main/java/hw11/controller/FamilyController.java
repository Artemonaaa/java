package hw11.controller;

import hw11.exceptions.FamilyOverflowException;
import hw11.model.Family;
import hw11.service.FamilyService;
import hw11.model.Humans.Human;

import java.time.DateTimeException;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.Scanner;

public class FamilyController {
    private final FamilyService familyService = new FamilyService();

    public void fillWithTestData() {
        familyService.createNewFamily(new Human("Doge1", "Doge1", 1980), new Human("Doge1", "Doge1", 1982));
        familyService.createNewFamily(new Human("Doge2", "Doge2", 1975), new Human("Doge2", "Doge2", 1978));
        familyService.createNewFamily(new Human("Doge3", "Doge3", 1990), new Human("Doge3", "Doge3", 1991));
        System.out.println("Тестові дані додано.");
    }

    public void saveData() {
        familyService.saveData();
    }

    public void loadData() {
        familyService.loadData();
        displayAllFamilies();
    }

    public void displayAllFamilies() {
        List<Family> families = familyService.getAllFamilies();
        if (families.isEmpty()) {
            System.out.println("Сімей немає.");
            return;
        }
        printFamilies(families);
    }

    public void displayFamiliesBiggerThan(Scanner scanner) {
        System.out.print("Введіть мінімальну кількість членів сім'ї: ");
        int count = Integer.parseInt(scanner.nextLine());
        List<Family> result = familyService.getAllFamilies().stream()
                .filter(family -> family.countFamily() > count)
                .collect(Collectors.toList());
        printFamilies(result);
    }

    public void displayFamiliesLessThan(Scanner scanner) {
        System.out.print("Введіть максимальну кількість членів сім'ї: ");
        int count = Integer.parseInt(scanner.nextLine());
        List<Family> result = familyService.getAllFamilies().stream()
                .filter(family -> family.countFamily() < count)
                .collect(Collectors.toList());
        printFamilies(result);
    }

    public void countFamiliesWithMemberNumber(Scanner scanner) {
        System.out.print("Введіть кількість членів сім'ї: ");
        int count = Integer.parseInt(scanner.nextLine());
        long number = familyService.getAllFamilies().stream()
                .filter(family -> family.countFamily() == count)
                .count();
        System.out.println("Кількість сімей з " + count + " членами: " + number);
    }

    public void createNewFamily(Scanner scanner) {
        System.out.println("Введіть інформацію про батька:");
        Human father = inputHuman(scanner);
        System.out.println("Введіть інформацію про матір:");
        Human mother = inputHuman(scanner);
        familyService.createNewFamily(father, mother);
        System.out.println("Нову родину створено.");
    }

    public Human inputHuman(Scanner scanner) {
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
            return inputHuman(scanner);
        } catch (DateTimeException e) {
            System.out.println("Некоректна дата. Будь ласка, введіть правильну дату.");
            return inputHuman(scanner);
        }
    }
    public void deleteFamilyByIndex(Scanner scanner) {
        System.out.print("Введіть індекс сім'ї для видалення: ");
        int index = Integer.parseInt(scanner.nextLine()) - 1;
        familyService.deleteFamilyByIndex(index);
        System.out.println("Сім'ю видалено.");
    }

    public void editFamily(Scanner scanner) {
        int index = inputFamilyIndex(scanner);
        if (index == -1) return;

        System.out.println("Редагування сім’ї:");
        System.out.println("1. Додати дитину (усиновлення)");
        System.out.println("2. Народити дитину");
        System.out.print("Ваш вибір: ");
        String choice = scanner.nextLine();

        switch (choice) {
            case "1" -> adoptChild(scanner, index);
            case "2" -> bornChild(scanner, index);
            default -> System.out.println("Невірний вибір. Будь ласка, оберіть 1 або 2.");
        }
    }

    private int inputFamilyIndex(Scanner scanner) {
        System.out.print("Введіть індекс сім'ї для редагування: ");
        try {
            int index = Integer.parseInt(scanner.nextLine()) - 1;
            if (familyService.getFamilyByIndex(index) == null) {
                System.out.println("Сім’я з таким індексом не знайдена.");
                return -1;
            }
            return index;
        } catch (NumberFormatException e) {
            System.out.println("Некоректний індекс. Спробуйте ще раз.");
            return -1;
        }
    }

    private void adoptChild(Scanner scanner, int index) {
        try {
            Human child = inputHuman(scanner);
            familyService.adoptChild(index, child);
            System.out.println("Дитину додано до сім'ї.");
        } catch (Exception e) {
            System.out.println("Помилка при усиновленні: " + e.getMessage());
        }
    }

    private void bornChild(Scanner scanner, int index) {
        try {
            System.out.print("Ім'я сина: ");
            String boyName = scanner.nextLine();
            System.out.print("Ім'я доньки: ");
            String girlName = scanner.nextLine();
            familyService.bornChild(index, boyName, girlName);
            System.out.println("Дитина народжена.");
        } catch (FamilyOverflowException e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    public void deleteAllChildrenOlderThan(Scanner scanner) {
        System.out.print("Введіть вік: ");
        int age = Integer.parseInt(scanner.nextLine());
        familyService.deleteAllChildrenOlderThan(age);
        System.out.println("Діти старше " + age + " років видалені.");
    }

    public void displayFamiliesWithCustomFilter(Scanner scanner) {
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

    public void printFamilies(List<Family> families) {
        if (families.isEmpty()) {
            System.out.println("Сімей, що відповідають критеріям, не знайдено.");
            return;
        }
        IntStream.range(0, families.size())
                .forEach(i -> System.out.println((i + 1) + ". " + families.get(i).prettyFormat()));
    }
}
