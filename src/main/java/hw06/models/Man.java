package hw06.models;

public final class Man extends Human {
    public Man(String name, String surname, int year, int iq, String[][] schedule) {
        super(name, surname, year, iq, schedule);
    }

    @Override
    public void greetPet() {
        if (getFamily() != null && getFamily().getPet() != null) {
            System.out.printf("Hello brawny %s!\n", getFamily().getPet().getNickname());
        }
    }

    public void repairCar() {
        System.out.println("Fixing the car, don't disturb me.");
    }
}
