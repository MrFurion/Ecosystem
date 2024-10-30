package by.trubetski.controllers;

import by.trubetski.models.Ecosystem;
import by.trubetski.services.EcosystemServices;
import by.trubetski.services.impl.EcosystemServicesImpl;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.IOException;

import static java.lang.System.out;

@AllArgsConstructor
@NoArgsConstructor
public class EcosystemController {
    private EcosystemServices ecosystemServices = new EcosystemServicesImpl();


    public Ecosystem findEcosystemByName(String nameEcosystem) {
        try {
            Ecosystem ecosystem = ecosystemServices.loadEcosystemFromFile(nameEcosystem);
            out.println("Экосистема '" + nameEcosystem + "' загружена из файлов.");
            return ecosystem;
        } catch (IOException e) {
            out.println("Ошибка при загрузке экосистемы: " + e.getMessage());
            return null;
        }
    }

    public void findAllEcosystem() {
        ecosystemServices.listEcosystems();
    }

    public void creatNewEcosystem(Ecosystem ecosystem, String filePath) {
        try {
            ecosystemServices.saveEcosystemToFile(ecosystem, filePath);
            out.println("Экосистема успешно сохранена в файл: " + filePath);
        } catch (IOException e) {
            out.println("Ошибка при сохранении экосистемы: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public void updateEcosystem(String ecosystemName, Ecosystem ecosystem) {
        try {
            ecosystemServices.saveEcosystemToFile(ecosystem, ecosystemName);
        } catch (IOException e) {
            out.println("Ошибка при загрузке экосистемы: " + e.getMessage());
        }
    }

    public void deleteEcosystemByName(String ecosystemNamePath) {
        ecosystemServices.delete(ecosystemNamePath);
    }
}
