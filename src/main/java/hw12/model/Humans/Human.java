package hw12.model.Humans;

import hw12.model.Family;
import hw12.model.Pets.Pet;

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
        this(name, surname, LocalDate.of(year, 1, 1).format(FORMATTER), 0, null);
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

    private LocalDate getBirthDateAsLocalDate() {
        return Instant.ofEpochMilli(this.birthDate)
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }

    public String describeAge() {
        Period age = calculateAge();
        return String.format("Age: %d years, %d months, %d days", age.getYears(), age.getMonths(), age.getDays());
    }

    private Period calculateAge() {
        return Period.between(getBirthDateAsLocalDate(), LocalDate.now());
    }

    public int getBirthYear() {
        return Instant.ofEpochMilli(this.birthDate)
                .atZone(ZoneId.systemDefault())
                .toLocalDate()
                .getYear();
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
        return calculateAge().getYears();
    }

    public String prettyFormat() {
        return String.format("{name='%s', surname='%s', birthDate='%s', iq=%d, schedule=%s}",
                name, surname, getBirthDateAsLocalDate().format(FORMATTER), iq, schedule);
    }

    @Override
    public String toString() {
        return String.format("Human{name='%s', surname='%s', birthDate='%s', iq=%d, schedule=%s}",
                name, surname, getBirthDateAsLocalDate().format(FORMATTER), iq, schedule);
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
