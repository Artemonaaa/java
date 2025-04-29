package hw04;

public class Main {
    public static void main(String[] args) {
        Pet doge = new Pet("Doge", "Rock", 5, 75, new String[]{"eat", "drink", "sleep"});
        Pet funnyDoge = new Pet("FunnyDoge", "Misty", 3, 45, new String[]{"jump", "sleep", "play"});

        Human mother = new Human("Jane", "Karleone", 1977);
        Human father = new Human("Vito", "Karleone", 1975);

        Family family = new Family(mother, father);
        family.setPet(doge);

        Human child = new Human("Michael", "Karleone", 2000, 90, new String[][]{{"Monday", "Football"}, {"Tuesday", "Swimming"}});
        family.addChild(child);

        child.greetPet();
        child.describePet();
        doge.eat();
        doge.respond();
        doge.foul();

        System.out.println(child);
        System.out.println(doge);
        System.out.println(family);

        Human mother2 = new Human("Anna", "Smith", 1980);
        Human father2 = new Human("John", "Smith", 1978);

        Family family2 = new Family(mother2, father2);
        family2.setPet(funnyDoge);

        Human child2 = new Human("Emma", "Smith", 2005, 95, new String[][]{{"Wednesday", "Piano"}, {"Friday", "Dance"}});
        family2.addChild(child2);

        System.out.println(family2);
    }
}
