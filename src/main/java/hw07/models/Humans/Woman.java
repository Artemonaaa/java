package hw07.models.Humans;

import hw07.models.Pets.Pet;

import java.util.Map;

public final class Woman extends Human {
    public Woman(String name, String surname, int year, int iq, Map<String, String> schedule) {
        super(name, surname, year, iq, schedule);
    }

    @Override
    public void greetPet() {
        if (getFamily() != null && getFamily().getPets() != null) {
            for (Pet pet : getFamily().getPets()) {
                System.out.printf("Hello brawny %s!\n", pet.getNickname());
            }
        }
    }

    public void makeup() {
        System.out.println("Applying some makeup.");
    }
}
