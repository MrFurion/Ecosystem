package by.trubetski.repositories;

import by.trubetski.models.Ecosystem;

import java.io.BufferedWriter;
import java.io.File;

public interface EcosystemRepository {
    void saveEcosystem(BufferedWriter writer, Ecosystem ecosystem, String filePath);
    void savePlant(BufferedWriter writer, Ecosystem ecosystem, String filePath);
    void saveAnimal(BufferedWriter writer, Ecosystem ecosystem, String filePath);
    File foundEcosystemByName(String filePath );
    File foundAllEcosystems();
    void deleteEcosystem(String filePath);
}
