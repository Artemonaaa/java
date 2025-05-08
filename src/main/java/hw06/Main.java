package hw06;

import hw06.enums.DayOfWeek;
import hw06.models.*;

public final class Main {
    public static void main(String[] args) {
        Pet fish = new Fish("Nemo", 1, 10, new String[]{"swim", "bubble"});
        fish.eat();
        fish.respond();
        System.out.println(fish);

        Pet cat = new DomesticCat("Murka", 3, 60, new String[]{"scratch", "sleep"});
        cat.eat();
        cat.respond();
        if (cat instanceof Foulable) ((Foulable) cat).foul();
        System.out.println(cat);

        Pet dog = new Dog("Sharik", 5, 80, new String[]{"bark", "guard"});
        dog.eat();
        dog.respond();
        if (dog instanceof Foulable) ((Foulable) dog).foul();
        System.out.println(dog);

        Pet robocat = new RoboCat("RoboMyrka", 2, 90, new String[]{"charge", "scan"});
        robocat.eat();
        robocat.respond();
        if (robocat instanceof Foulable) {
            ((Foulable) robocat).foul();
        } else {
            System.out.println("RoboCat cant foul.");
        }
        System.out.println(robocat);

        String[][] manSchedule = {{DayOfWeek.MONDAY.name(), "Work"}, {DayOfWeek.TUESDAY.name(), "Fix car"}};
        Man man = new Man("Ivan", "Ivanov", 1985, 90, manSchedule);

        String[][] womanSchedule = {{DayOfWeek.MONDAY.name(), "Shopping"}, {DayOfWeek.TUESDAY.name(), "Makeup"}};
        Woman woman = new Woman("Oksana", "Ivanova", 1988, 95, womanSchedule);


        Family family = new Family(woman, man);
        family.setPet(dog);

        System.out.println("Woman greet pet:");
        woman.greetPet();
        woman.makeup();

        System.out.println("Man greet pet:");
        man.greetPet();
        man.repairCar();

        woman.describePet();
        man.describePet();

        System.out.println("Family:");
        System.out.println(family);
    }
}
