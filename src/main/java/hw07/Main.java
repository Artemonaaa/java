package hw07;

import hw07.enums.DayOfWeek;
import hw07.models.Family;
import hw07.models.Humans.Man;
import hw07.models.Humans.Woman;
import hw07.models.Pets.*;

import java.util.Map;
import java.util.Set;

public final class Main {
    public static void main(String[] args) {
        Pet fish = new Fish("Nemo", 1, 10, Set.of("swim", "bubble"));
        fish.eat();
        fish.respond();
        System.out.println(fish);

        Pet cat = new DomesticCat("Murka", 3, 60, Set.of("scratch", "sleep"));
        cat.eat();
        cat.respond();
        if (cat instanceof Foulable) ((Foulable) cat).foul();
        System.out.println(cat);

        Pet dog = new Dog("Sharik", 5, 80, Set.of("bark", "guard"));
        dog.eat();
        dog.respond();
        if (dog instanceof Foulable) ((Foulable) dog).foul();
        System.out.println(dog);

        Pet robocat = new RoboCat("RoboMyrka", 2, 90, Set.of("charge", "scan"));
        robocat.eat();
        robocat.respond();
        if (robocat instanceof Foulable) {
            ((Foulable) robocat).foul();
        } else {
            System.out.println("RoboCat can't foul.");
        }
        System.out.println(robocat);

        Man man = new Man("Ivan", "Ivanov", 1985, 90, Map.of(
                DayOfWeek.MONDAY.name(), "Work",
                DayOfWeek.TUESDAY.name(), "Fix car"
        ));

        Woman woman = new Woman("Oksana", "Ivanova", 1988, 95, Map.of(
                DayOfWeek.MONDAY.name(), "Shopping",
                DayOfWeek.TUESDAY.name(), "Makeup"
        ));

        Family family = new Family(woman, man);
        family.setPet(dog);

        System.out.println("Woman greets pet:");
        woman.greetPet();
        woman.makeup();

        System.out.println("Man greets pet:");
        man.greetPet();
        man.repairCar();

        woman.describePet();
        man.describePet();

        System.out.println("Family:");
        System.out.println(family);
    }
}
