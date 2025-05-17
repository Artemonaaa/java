package hw09.models.Humans;

import hw09.models.Family;
import hw09.models.Pets.Pet;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Objects;

public class Human {
    private String name;
    private String surname;
    private long birthDate;
    private int iq;
    private Map<String, String> schedule;
    private Family family;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Human() {}

    public Human(String name, String surname, int year) {
        this(name, surname, LocalDate.of(year, 1, 1)
                .format(FORMATTER), 0, null);
    }

    public Human(String name, String surname, int year, int iq, Map<String, String> schedule) {
        this(name, surname, LocalDate.of(year, 1, 1).format(FORMATTER), iq, schedule);
    }

    public Human(String name, String surname, String birthDateStr, int iq) {
        this(name, surname, birthDateStr, iq, null);
    }

    public Human(String name, String surname, String birthDateStr, int iq, Map<String, String> schedule) {
        this.name = name;
        this.surname = surname;
        this.iq = iq;
        this.schedule = schedule;
        this.birthDate = parseDate(birthDateStr);
    }

    private long parseDate(String dateStr) {
        LocalDate date = LocalDate.parse(dateStr, FORMATTER);
        return date.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public String describeAge() {
        LocalDate birth = Instant.ofEpochMilli(this.birthDate)
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        Period age = Period.between(birth, LocalDate.now());
        return String.format("Age: %d years, %d months, %d days", age.getYears(), age.getMonths(), age.getDays());
    }

    public void greetPet() {
        if (family != null && family.getPets() != null) {
            family.getPets().stream()
                    .map(Pet::getNickname)
                    .forEach(nickname -> System.out.printf("Hello, %s%n", nickname));
        }
    }

    public void describePet() {
        if (family != null && family.getPets() != null && !family.getPets().isEmpty()) {
            family.getPets()
                    .forEach(pet -> {
                        String slyness = pet.getTrickLevel() > 50 ? "very tricky" : "almost not tricky";
                        System.out.printf("I have a %s, they are %d years old, and they are %s%n",
                                pet.getSpecies(), pet.getAge(), slyness);
                    });
        }
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public long getBirthDate() {
        return birthDate;
    }

    public int getIq() {
        return iq;
    }

    public Map<String, String> getSchedule() {
        return schedule;
    }

    public Family getFamily() {
        return family;
    }

    public void setFamily(Family family) {
        this.family = family;
    }

    public void setSchedule(Map<String, String> schedule) {
        this.schedule = schedule;
    }

    public int getAge() {
        LocalDate birth = Instant.ofEpochMilli(this.birthDate)
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        return Period.between(birth, LocalDate.now()).getYears();
    }


    @Override
    public String toString() {
        String birthDateStr = Instant.ofEpochMilli(birthDate)
                .atZone(ZoneId.systemDefault())
                .toLocalDate()
                .format(FORMATTER);
        return String.format("Human{name='%s', surname='%s', birthDate='%s', iq=%d, schedule=%s}",
                name, surname, birthDateStr, iq, schedule);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Human human)) return false;
        return birthDate == human.birthDate &&
                iq == human.iq &&
                Objects.equals(name, human.name) &&
                Objects.equals(surname, human.surname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, birthDate, iq);
    }
}
