package by.trubetski.managers;

import by.trubetski.controllers.EcosystemController;
import by.trubetski.dto.EcosystemDto;
import by.trubetski.mapper.EcosystemMapper;
import by.trubetski.models.Ecosystem;
import by.trubetski.services.EcosystemServices;
import by.trubetski.services.impl.EcosystemServicesImpl;
import by.trubetski.util.ValidationIntFildOnNull;
import by.trubetski.util.ValidationUtils;

import java.util.ArrayList;
import java.util.Scanner;

import static by.trubetski.constants.EcosystemDtoConstants.CHARACTERS_NAME;
import static by.trubetski.constants.EcosystemDtoConstants.C_MAX;
import static by.trubetski.constants.EcosystemDtoConstants.C_MIN;
import static by.trubetski.constants.EcosystemDtoConstants.HUMIDITY_MAX;
import static by.trubetski.constants.EcosystemDtoConstants.HUMIDITY_MIN;
import static by.trubetski.constants.EcosystemDtoConstants.QUANTITY_EARTH_MAX;
import static by.trubetski.constants.EcosystemDtoConstants.QUANTITY_EARTH_MIN;
import static by.trubetski.constants.EcosystemDtoConstants.QUANTITY_WATER_MAX;
import static by.trubetski.constants.EcosystemDtoConstants.QUANTITY_WATER_MIN;
import static java.lang.System.in;
import static java.lang.System.out;

public class InputManager {
    private static final EcosystemServices ecosystemServices = new EcosystemServicesImpl();
    private static final EcosystemController ecosystemController = new EcosystemController();

    public static void input() {
        Scanner scanner = new Scanner(in);
        String choice;

        while (true) {
            out.println("\n--- Меню экосистем ---");
            out.println("1. Создать новую экосистему");
            out.println("2. Загрузить экосистему из файла");
            out.println("3. Показать все экосистемы");
            out.println("4. Обновить экосистему");
            out.println("5. Удалить экосистему");
            out.println("6. Выйти");
            out.print("Введите ваш выбор: ");
            choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    createNewEcosystem(scanner);
                    break;
                case "2":
                    loadEcosystem(scanner);
                    break;
                case "3":
                    ecosystemController.findAllEcosystem();
                    break;
                case "4":
                    updateEcosystem(scanner);
                    break;
                case "5":
                    deleteEcosystem(scanner);
                    break;
                case "6":
                    out.println("Выход...");
                    return;
                default:
                    out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    private static void createNewEcosystem(Scanner scanner) {
        EcosystemDto ecosystemDto;
        while (true) {
            out.print("Введите название новой экосистемы размером " + CHARACTERS_NAME + ": ");
            String ecosystemName = scanner.nextLine().trim();
            if (ecosystemName.isEmpty()) {
                out.println("Ошибка: название экосистемы не может быть пустым. Попробуйте снова.");
                continue;
            }

            Integer temperature = ValidationIntFildOnNull.getInputInt(scanner,
                    "Введите температуру (в градусах Цельсия) от " + C_MIN + " до " + C_MAX + ": ");
            if (temperature == null) continue;

            Integer humidity = ValidationIntFildOnNull.getInputInt(scanner,
                    "Введите влажность (в процентах) от " + HUMIDITY_MIN + " до " + HUMIDITY_MAX + ": ");
            if (humidity == null) continue;

            Integer quantityWater = ValidationIntFildOnNull.getInputInt(scanner,
                    "Введите количество воды (в литрах) от " + QUANTITY_WATER_MIN + " до " + QUANTITY_WATER_MAX + ": ");
            if (quantityWater == null) continue;

            Integer quantityEarth = ValidationIntFildOnNull.getInputInt(scanner,
                    "Введите количество земли (в кубических метрах) от " + QUANTITY_EARTH_MIN + " до " + QUANTITY_EARTH_MAX + ": ");
            if (quantityEarth == null) continue;

            ecosystemDto = EcosystemDto.builder()
                    .name(ecosystemName)
                    .temperature(temperature)
                    .humidity(humidity)
                    .quantityWater(quantityWater)
                    .quantityEarth(quantityEarth)
                    .animals(new ArrayList<>())
                    .plants(new ArrayList<>())
                    .build();

            try {
                ValidationUtils.validate(ecosystemDto);
                break;
            } catch (IllegalArgumentException e) {
                out.println("Ошибка валидации: " + e.getMessage());
                out.println("Пожалуйста, введите данные снова.");
            }
        }

        Ecosystem ecosystem = EcosystemMapper.INSTANCE.toEntity(ecosystemDto);

        out.println("Экосистема '" + ecosystem.getName() + "' создана с параметрами: температура = " + ecosystem.getTemperature() +
                "°C, влажность = " + ecosystem.getHumidity() + "%, количество воды = "
                + ecosystem.getQuantityWater() + " л, количество земли = " + ecosystem.getQuantityEarth() + " м³.");

        String option;
        while (true) {
            option = displayAddMenu(scanner);

            if (option.equals("1")) {
                PlantManager.addPlantsToEcosystem(scanner, ecosystem);
            } else if (option.equals("2")) {
                AnimalManager.addAnimalsToEcosystem(scanner, ecosystem);
            } else if (option.equals("3")) {
                ecosystemServices.addEcosystem(ecosystem);
                out.println("Экосистема '" + ecosystem.getName() + "' успешно создана!");

                out.print("Введите путь для сохранения экосистемы: ");
                String filePath = scanner.nextLine();

                ecosystemController.creatNewEcosystem(ecosystem, filePath);
                break;
            } else {
                out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    private static void loadEcosystem(Scanner scanner) {
        out.print("Введите название экосистемы: ");
        String ecosystemName = scanner.nextLine();
        ecosystemController.findEcosystemByName(ecosystemName);
    }

    private static String displayAddMenu(Scanner scanner) {
        out.println("Хотите добавить растения или животных?");
        out.println("1. Добавить растения");
        out.println("2. Добавить животных");
        out.println("3. Завершить добавление");
        out.print("Введите ваш выбор: ");
        return scanner.nextLine();
    }

    private static void updateEcosystem(Scanner scanner) {
        out.print("Введите название экосистемы для обновления: ");
        String ecosystemName = scanner.nextLine();
        Ecosystem ecosystem = ecosystemController.findEcosystemByName(ecosystemName);

        if (ecosystem == null) {
            out.println("Экосистема с таким именем не найдена.");
            return;
        }

        Integer temperature = ValidationIntFildOnNull.getInputInt(scanner, "Введите новую температуру (текущая: "
                + ecosystem.getTemperature() + "): ");
        if (temperature == null) return;
        ecosystem.setTemperature(temperature);

        Integer humidity = ValidationIntFildOnNull.getInputInt(scanner, "Введите новую влажность (текущая: "
                + ecosystem.getHumidity() + "): ");
        if (humidity == null) return;
        ecosystem.setHumidity(humidity);

        Integer quantityWater = ValidationIntFildOnNull.getInputInt(scanner, "Введите новое количество воды (текущая: "
                + ecosystem.getQuantityWater() + " л): ");
        if (quantityWater == null) return;
        ecosystem.setQuantityWater(quantityWater);

        Integer quantityEarth = ValidationIntFildOnNull.getInputInt(scanner, "Введите новое количество земли (текущая: "
                + ecosystem.getQuantityEarth() + " м³): ");
        if (quantityEarth == null) return;
        ecosystem.setQuantityEarth(quantityEarth);

        ecosystemController.updateEcosystem(ecosystemName, ecosystem);
        out.println("Экосистема '" + ecosystemName + "' успешно обновлена!");

        String option;
        while (true) {
            out.println("\n--- Редактирование экосистемы ---");
            out.println("1. Добавить животное");
            out.println("2. Добавить растение");
            out.println("3. Изменить параметры животного");
            out.println("4. Изменить параметры растения");
            out.println("5. Сохранить и выйти");
            out.print("Выберите опцию: ");
            option = scanner.nextLine();

            switch (option) {
                case "1":
                    AnimalManager.addAnimalsToEcosystem(scanner, ecosystem);
                    break;

                case "2":
                    PlantManager.addPlantsToEcosystem(scanner, ecosystem);
                    break;

                case "3":
                    AnimalManager.updateAnimalInEcosystem(scanner, ecosystem);
                    break;

                case "4":
                    PlantManager.updatePlantInEcosystem(scanner, ecosystem);
                    break;

                case "5":
                    ecosystemController.updateEcosystem(ecosystemName, ecosystem);
                    out.println("Экосистема '" + ecosystemName + "' успешно обновлена!");
                    return;
                default:
                    out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }

    private static void deleteEcosystem(Scanner scanner) {
        out.print("Введите название экосистемы для удаления: ");
        String ecosystemName = scanner.nextLine();

        ecosystemController.deleteEcosystemByName(ecosystemName);
    }
}
