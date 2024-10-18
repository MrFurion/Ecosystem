package by.trubetski.managers;

import by.trubetski.models.Animal;
import by.trubetski.models.Ecosystem;

public class AnimalManager {
    public void addAnimal(Ecosystem ecosystem, Animal animal) {
        ecosystem.getAnimals().add(animal);
    }
}
