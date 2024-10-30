package by.trubetski.repositories.bdimpl;

import by.trubetski.models.Animal;
import by.trubetski.models.Ecosystem;
import by.trubetski.models.Plant;
import by.trubetski.repositories.EcosystemRepository;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

public class BD implements EcosystemRepository {

    public static final String ALLECOSYSTEMS = "I:/IT/Ecosystem/allecosystems"; // Укажите путь к папке, где хранятся файлы экосистем

    @Override
    public void saveEcosystem(BufferedWriter writer, Ecosystem ecosystem, String filePath) {

        try {
            writer.write("### Ecosystem\n");
            writer.write("Имя: " + ecosystem.getName() + "\n");
            writer.write("Температура: " + ecosystem.getTemperature() + "\n");
            writer.write("Влажность: " + ecosystem.getHumidity() + "\n");
            writer.write("Количество воды: " + ecosystem.getQuantityWater() + "\n");
            writer.write("Количество земли: " + ecosystem.getQuantityEarth() + "\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void savePlant(BufferedWriter writer, Ecosystem ecosystem, String filePath) {
        try {
            writer.write("### Plants\n");
            for (Plant plant : ecosystem.getPlants()) {
                writer.write(plant.getName() + "," + plant.getAge() + "," + plant.getWeight() + "," +
                        plant.getTimeLife() + "," + plant.getCount());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveAnimal(BufferedWriter writer, Ecosystem ecosystem, String filePath) {
        try {
            writer.write("### Animals\n");
            for (Animal animal : ecosystem.getAnimals()) {
                writer.write(animal.getName() + "," + animal.getGender() + "," + animal.getDietType() + "," +
                        animal.getAge() + "," + animal.getOffspringCount() + "," + animal.getDailyFoodRequirement());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public File foundEcosystemByName(String filePath) {
        return new File(filePath);
    }

    @Override
    public File foundAllEcosystems() {
        return new File(ALLECOSYSTEMS);
    }

    @Override
    public void deleteEcosystem(String ecosystemNamePath) {
        String directoryPath = ALLECOSYSTEMS;
        String filePath = directoryPath + File.separator + ecosystemNamePath;

        File file = new File(filePath);
        if (file.exists()) {
            if (file.delete()) {
                System.out.println("Экосистема '" + ecosystemNamePath + "' успешно удалена.");
            } else {
                System.out.println("Не удалось удалить экосистему '" + ecosystemNamePath + "'.");
            }
        } else {
            System.out.println("Экосистема с таким именем не найдена.");
        }
    }
}
