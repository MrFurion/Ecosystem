package by.trubetski.services;

import by.trubetski.models.Ecosystem;

import java.io.IOException;

public interface EcosystemServices {
    void loadEcosystemFromFile(String ecosystemName, String plantsFile, String animalsFile) throws IOException;
    Ecosystem getEcosystem(String name);
    void addEcosystem(Ecosystem ecosystem);
    void saveEcosystemToFile(String ecosystemName, String plantsFile, String animalsFile) throws IOException;
    void listEcosystems();
}
