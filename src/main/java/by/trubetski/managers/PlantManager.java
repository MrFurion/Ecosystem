package by.trubetski.managers;

import by.trubetski.dto.PlantDto;
import by.trubetski.mapper.PlantMapper;
import by.trubetski.models.Ecosystem;
import by.trubetski.models.Plant;
import by.trubetski.util.ValidationIntFildOnNull;
import by.trubetski.util.ValidationUtils;

import java.util.Scanner;

import static by.trubetski.constants.PlaintDtoConstants.AGE;
import static by.trubetski.constants.PlaintDtoConstants.CHARACTERS_OF_NAME;
import static by.trubetski.constants.PlaintDtoConstants.COUNT;
import static by.trubetski.constants.PlaintDtoConstants.TIME_LIFE;
import static by.trubetski.constants.PlaintDtoConstants.WEIGHT;
import static java.lang.System.out;

public class PlantManager {

    public static void addPlantsToEcosystem(Scanner scanner, Ecosystem ecosystem) {
        PlantMapper plantMapper = PlantMapper.INSTANCE;

        while (true) {
            out.println("Добавление нового растения:");

            out.print("Название растения должно иметь " + CHARACTERS_OF_NAME + ": ");
            String name = scanner.nextLine();

            Integer age = ValidationIntFildOnNull.getInputInt(scanner, "Возраст растения до " + AGE + ": ");
            if (age == null) continue;

            Integer weight = ValidationIntFildOnNull.getInputInt(scanner, "Вес растения (в кг до " + WEIGHT + "): ");
            if (weight == null) continue;

            Integer timeLife = ValidationIntFildOnNull.getInputInt(scanner, "Продолжительность жизни (до " + TIME_LIFE + "): ");
            if (timeLife == null) continue;

            Integer count = ValidationIntFildOnNull.getInputInt(scanner, "Количество до " + COUNT + ": ");
            if (count == null) continue;

            PlantDto plantDto = PlantDto.builder()
                    .name(name)
                    .age(age)
                    .weight(weight)
                    .timeLife(timeLife)
                    .count(count)
                    .build();

            try {
                ValidationUtils.validate(plantDto);

                Plant plant = plantMapper.toEntity(plantDto);
                ecosystem.getPlants().add(plant);
                out.println("Растение добавлено.");

                out.print("Хотите добавить ещё одно растение? (да/нет): ");
                if (!scanner.nextLine().equalsIgnoreCase("да")) {
                    break;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Ошибка валидации: " + e.getMessage());
                System.out.println("Пожалуйста, введите данные снова.");
            }
        }
    }

    public static void updatePlantInEcosystem(Scanner scanner, Ecosystem ecosystem) {
        out.print("Введите имя растения для обновления: ");
        String plantName = scanner.nextLine();

        Plant plant = ecosystem.getPlants().stream()
                .filter(p -> p.getName().equalsIgnoreCase(plantName))
                .findFirst()
                .orElse(null);

        if (plant == null) {
            out.println("Растение с таким именем не найдено.");
            return;
        }

        Integer newAge = ValidationIntFildOnNull.getInputInt(scanner, "Введите новый возраст для растения (текущий: " + plant.getAge() + "): ");
        if (newAge != null) plant.setAge(newAge);

        Integer newWeight = ValidationIntFildOnNull.getInputInt(scanner, "Введите новый вес для растения (текущий: " + plant.getWeight() + "): ");
        if (newWeight != null) plant.setWeight(newWeight);

        Integer newTimeLife = ValidationIntFildOnNull.getInputInt(scanner, "Введите новую продолжительность жизни для растения (текущая: " + plant.getTimeLife() + "): ");
        if (newTimeLife != null) plant.setTimeLife(newTimeLife);

        Integer newCount = ValidationIntFildOnNull.getInputInt(scanner, "Введите новое количество (текущее: " + plant.getCount() + "): ");
        if (newCount != null) plant.setCount(newCount);

        out.println("Растение успешно обновлено: " + plant);
    }
}
