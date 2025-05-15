package hw08.models.Pets;

import hw08.enums.Species;

import java.util.Set;

public class Dog extends Pet implements Foulable {
    public Dog(String nickname, int age, int trickLevel, Set<String> habits) {
        super(nickname, age, trickLevel, habits);
        this.species = Species.DOG;
    }

    @Override
    public void respond() {
        System.out.printf("Dogeeeeeee! I'm %s, your loyal dog!\n", getNickname());
    }

    @Override
    public void foul() {
        System.out.println("I'm very sorry xiopang(doge).");
    }
}
