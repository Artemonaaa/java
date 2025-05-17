package hw09.models.Pets;

import hw09.enums.Species;

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

