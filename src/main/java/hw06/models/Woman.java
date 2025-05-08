package hw06.models;

public final class Woman extends Human {
    public Woman(String name, String surname, int year, int iq, String[][] schedule) {
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
