package hw12.dao;

import hw12.model.Family;
import hw12.model.Humans.Human;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CollectionFamilyDao implements FamilyDAO<Family> {
    private static final String FILE_PATH = "src/main/resources/hw11/families.file";
    private final List<Family> families;

    public CollectionFamilyDao() {
        families = loadData();
    }

    @Override
    public List<Family> getAllFamilies() {
        return new ArrayList<>(families);
    }

    @Override
    public void setFamilies(List<Family> families) {
        this.families.clear();
        this.families.addAll(families);
    }

    @Override
    public Family getFamilyByIndex(int index) {
        if (index >= 0 && index < families.size()) {
            return families.get(index);
        }
        return null;
    }

    @Override
    public boolean deleteFamily(int index) {
        if (index >= 0 && index < families.size()) {
            families.remove(index);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteFamily(Family family) {
        return families.remove(family);
    }

    @Override
    public void saveFamily(Family family) {
        int index = families.indexOf(family);
        if (index >= 0) {
            families.set(index, family);
        } else {
            families.add(family);
        }
    }

    public void saveData(List<Family> families) {
        File file = new File(FILE_PATH);
        try {
            file.getParentFile().mkdirs();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (Family family : families) {
                    writer.write(serializeFamily(family));
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.err.println("Помилка збереження даних: " + e.getMessage());
        }
    }

    public List<Family> loadData() {
        List<Family> families = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                families.add(deserializeFamily(line));
            }
        } catch (IOException e) {
            System.err.println("Помилка завантаження даних: " + e.getMessage());
        }

        return families;
    }

    private String serializeFamily(Family family) {
        StringBuilder serialized = new StringBuilder();

        serialized.append(serializeHuman(family.getFather())).append("|");
        serialized.append(serializeHuman(family.getMother())).append("|");

        serialized.append(family.getChildren().size()).append("|");
        for (Human child : family.getChildren()) {
            serialized.append(serializeHuman(child)).append(";");
        }

        return serialized.toString();
    }

    private Family deserializeFamily(String data) {
        String[] parts = data.split("\\|");

        Human father = deserializeHuman(parts[0]);
        Human mother = deserializeHuman(parts[1]);

        Family family = new Family(mother, father);

        if (parts.length > 3 && !parts[3].isEmpty()) {
            String[] childrenData = parts[3].split(";");
            for (String childData : childrenData) {
                family.addChild(deserializeHuman(childData));
            }
        }

        return family;
    }

    private String serializeHuman(Human human) {
        return human.getName() + "," +
                human.getSurname() + "," +
                human.getBirthYear() + "," +
                human.getIq();
    }

    private Human deserializeHuman(String data) {
        String[] parts = data.split(",");
        String name = parts[0];
        String surname = parts[1];
        int birthYear = Integer.parseInt(parts[2]);
        int iq = Integer.parseInt(parts[3]);

        String birthDateStr = String.format("01/01/%d", birthYear);

        return new Human(name, surname, birthDateStr, iq);
    }
}
