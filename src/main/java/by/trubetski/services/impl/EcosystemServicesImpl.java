package by.trubetski.services.impl;

import by.trubetski.models.Animal;
import by.trubetski.models.Ecosystem;
import by.trubetski.models.Plant;
import by.trubetski.repositories.EcosystemRepository;
import by.trubetski.repositories.bdimpl.BD;
import by.trubetski.services.EcosystemServices;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EcosystemServicesImpl implements EcosystemServices {

    private Map<String, Ecosystem> ecosystems;

    private EcosystemRepository ecosystemRepository;

    public EcosystemServicesImpl() {
        this.ecosystems = new HashMap<>();
        this.ecosystemRepository = new BD();
    }

    public void addEcosystem(Ecosystem ecosystem) {
        ecosystems.put(ecosystem.getName(), ecosystem);
    }

    public Ecosystem loadEcosystemFromFile(String ecosystemName) throws IOException {

        String directoryPath = "allecosystems";
        File directory = new File(directoryPath);

        if (!directory.exists()) {
            directory.mkdirs();
        }

        String filePath = directoryPath + File.separator + ecosystemName;

        File file = ecosystemRepository.foundEcosystemByName(filePath);

        if (!file.exists()) {
            throw new FileNotFoundException("Файл экосистемы с именем '" + ecosystemName + "' не найден.");
        }

        Ecosystem ecosystem = new Ecosystem(ecosystemName, 0, 0, 0, 0);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("### Ecosystem")) {
                    ecosystem.setName(reader.readLine().split(": ")[1]);
                    ecosystem.setTemperature(Integer.parseInt(reader.readLine().split(": ")[1]));
                    ecosystem.setHumidity(Integer.parseInt(reader.readLine().split(": ")[1]));
                    ecosystem.setQuantityWater(Integer.parseInt(reader.readLine().split(": ")[1]));
                    ecosystem.setQuantityEarth(Integer.parseInt(reader.readLine().split(": ")[1]));
                } else if (line.startsWith("### Plants")) {
                    while ((line = reader.readLine()) != null) {
                        if (line.startsWith("### Animals")) {
                            break;
                        }
                        String[] plantData = line.split(",");
                        Plant plant = new Plant(plantData[0], Integer.parseInt(plantData[1]), Integer.parseInt(plantData[2]),
                                Integer.parseInt(plantData[3]), Integer.parseInt(plantData[4]));
                        ecosystem.getPlants().add(plant);
                    }

                    while ((line = reader.readLine()) != null && !line.trim().isEmpty()) {
                        String[] animalData = line.split(",");
                        if (animalData.length == 6) {
                            Animal animal = new Animal(animalData[0], animalData[1], animalData[2],
                                    Integer.parseInt(animalData[3]), Integer.parseInt(animalData[4]), Integer.parseInt(animalData[5]));
                            ecosystem.getAnimals().add(animal);
                        } else {
                            System.out.println("Ошибка: Неправильный формат данных для животного в строке: " + line);
                        }
                    }
                }
            }
        }

        ecosystems.put(ecosystemName, ecosystem);
        System.out.println("Ваша экосистема ------> " + ecosystem);
        return ecosystem;
    }

    public void listEcosystems() {

        File directory = ecosystemRepository.foundAllEcosystems();

        if (!directory.exists() || !directory.isDirectory()) {
            System.out.println("Папка с экосистемами не найдена.");
            return;
        }

        System.out.println("Доступные экосистемы:");
        File[] files = directory.listFiles();

        if (files == null) {
            System.out.println("Не удалось прочитать содержимое директории.");
            return;
        }

        if (files.length > 0) {
            for (File file : files) {
                System.out.println(file.getName());
            }
        } else {
            System.out.println("В директории нет файлов.");
        }
    }

    public void saveEcosystemToFile(Ecosystem ecosystem, String nameFile) throws IOException {
        String directoryPath = "allecosystems";
        File directory = new File(directoryPath);

        if (!directory.exists()) {
            directory.mkdirs();
        }

        String filePath = directoryPath + File.separator + nameFile;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            ecosystemRepository.saveEcosystem(writer, ecosystem, filePath);
            ecosystemRepository.savePlant(writer, ecosystem, filePath);
            ecosystemRepository.saveAnimal(writer, ecosystem, filePath);
        }
    }

    @Override
    public void delete(String ecosystemNamePath) {
        ecosystemRepository.deleteEcosystem(ecosystemNamePath);
    }
}
