package by.trubetski.services.impl;

import by.trubetski.managers.AnimalManager;
import by.trubetski.managers.PlantManager;
import by.trubetski.models.Animal;
import by.trubetski.models.Ecosystem;
import by.trubetski.models.Plant;
import by.trubetski.services.EcosystemServices;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class EcosystemServicesImpl implements EcosystemServices {

    private Map<String, Ecosystem> ecosystems;
    private PlantManager plantManager;
    private AnimalManager animalManager;

    public EcosystemServicesImpl() {
        this.ecosystems = new HashMap<>();
        this.plantManager = new PlantManager();
        this.animalManager = new AnimalManager();
    }


    public void addEcosystem(Ecosystem ecosystem) {
        ecosystems.put(ecosystem.getName(), ecosystem);
    }

    public Ecosystem getEcosystem(String name) {
        return ecosystems.get(name);
    }

    public void loadEcosystemFromFile(String ecosystemName, String plantsFile, String animalsFile) throws IOException {
        Ecosystem ecosystem = new Ecosystem();
        loadPlantsFromFile(ecosystem, plantsFile);
        loadAnimalsFromFile(ecosystem, animalsFile);
        ecosystems.put(ecosystemName, ecosystem);
    }

    public void saveEcosystemToFile(String ecosystemName, String plantsFile, String animalsFile) throws IOException {
        Ecosystem ecosystem = ecosystems.get(ecosystemName);
        if (ecosystem != null) {
            savePlantsToFile(ecosystem, plantsFile);
            saveAnimalsToFile(ecosystem, animalsFile);
        }
    }

    public void loadPlantsFromFile(Ecosystem ecosystem, String plantsFile) throws IOException {
        try (BufferedReader plantReader = new BufferedReader(new FileReader(plantsFile))) {
            String line;

            while ((line = plantReader.readLine()) != null) {

                String[] plantData = line.split(",");

                Plant plant = new Plant(
                        plantData[0],
                        Integer.parseInt(plantData[1]),
                        Integer.parseInt(plantData[2]),
                        Integer.parseInt(plantData[3]),
                        Integer.parseInt(plantData[4])
                );

                plantManager.addPlant(ecosystem, plant);
            }
        }
    }

    public void loadAnimalsFromFile(Ecosystem ecosystem, String animalsFile) throws IOException {
        try (BufferedReader animalReader = new BufferedReader(new FileReader(animalsFile))) {
            String line;

            while ((line = animalReader.readLine()) != null) {

                String[] animalData = line.split(",");

                Animal animal = new Animal(
                        animalData[0],
                        animalData[1],
                        animalData[2],
                        Integer.parseInt(animalData[3]),
                        Integer.parseInt(animalData[4]),
                        Integer.parseInt(animalData[5])
                );
                animalManager.addAnimal(ecosystem, animal);
            }
        }
    }

    private void savePlantsToFile(Ecosystem ecosystem, String plantsFile) throws IOException {
        try (BufferedWriter plantWriter = new BufferedWriter(new FileWriter(plantsFile))) {
            for (Plant plant : ecosystem.getPlants()) {
                plantWriter.write(plant.getName() +
                        "," + plant.getAge() +
                        "," + plant.getWeight() +
                        "," + plant.getTimeLife() +
                        "," + plant.getCount()
                );
                plantWriter.newLine();
            }
        }
    }

    private void saveAnimalsToFile(Ecosystem ecosystem, String animalsFile) throws IOException {
        try (BufferedWriter animalWriter = new BufferedWriter(new FileWriter(animalsFile))) {
            for (Animal animal : ecosystem.getAnimals()) {
                animalWriter.write(animal.getName() +
                        "," + animal.getGender() +
                        "," + animal.getDietType() +
                        "," + animal.getAge() +
                        "," + animal.getOffspringCount() +
                        "," + animal.getDailyFoodRequirement());
                animalWriter.newLine();
            }
        }
    }

    public void listEcosystems() {
        System.out.println("Available ecosystems:");
        ecosystems.keySet().forEach(System.out::println);
    }
}
