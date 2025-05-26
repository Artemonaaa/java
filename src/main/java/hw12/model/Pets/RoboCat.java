package hw12.model.Pets;

import hw12.enums.Species;

import java.util.Set;

public class RoboCat extends Pet {
    public RoboCat(String nickname, int age, int trickLevel, Set<String> habits) {
        super(nickname, age, trickLevel, habits);
        this.species = Species.ROBOCAT;
    }

    @Override
    public void respond() {
        System.out.printf("0x1001000f... I'm RoboCat %s\n", getNickname());
    }
}

