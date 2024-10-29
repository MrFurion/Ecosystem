package by.trubetski.services;

import by.trubetski.models.Ecosystem;

import java.io.IOException;
import java.util.Scanner;

public interface EcosystemServices {
    Ecosystem loadEcosystemFromFile(String ecosystemName) throws IOException;

    void addEcosystem(Ecosystem ecosystem);

    void listEcosystems();

    void saveEcosystemToFile(Ecosystem ecosystem, String filePath) throws IOException;

    void delete(String ecosystemNamePath);
}
