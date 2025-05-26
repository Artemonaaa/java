package hw12.model.Humans;

import hw12.model.Pets.Pet;

import java.util.Map;

public final class Woman extends Human {
    public Woman(String name, String surname, int year, int iq, Map<String, String> schedule) {
        super(name, surname, year, iq, schedule);
    }

    @Override
    public void greetPet() {
        if (getFamily() != null && getFamily().getPets() != null) {
            getFamily().getPets().stream()
                    .map(Pet::getNickname)
                    .forEach(nickname -> System.out.printf("Hello brawny %s!%n", nickname));
        }
    }

    public void makeup() {
        System.out.println("Applying some makeup.");
    }
}
