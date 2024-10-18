package by.trubetski.managers;

import by.trubetski.models.Ecosystem;
import by.trubetski.models.Plant;

public class PlantManager {
    public void addPlant(Ecosystem ecosystem, Plant plant) {
        ecosystem.getPlants().add(plant);
    }
}
