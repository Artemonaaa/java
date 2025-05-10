package hw07.models.Humans;

import java.util.Map;

public final class Woman extends Human {
    public Woman(String name, String surname, int year, int iq, Map<String, String> schedule) {
        super(name, surname, year, iq, schedule);
    }

    @Override
    public void greetPet() {
        if (getFamily() != null && getFamily().getPet() != null) {
            System.out.printf("Hi cute %s!\n", getFamily().getPet().getNickname());
        }
    }

    public void makeup() {
        System.out.println("Applying some makeup.");
    }
}
