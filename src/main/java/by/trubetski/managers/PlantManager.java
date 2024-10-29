package by.trubetski.managers;

import by.trubetski.dto.PlantDto;
import by.trubetski.mapper.PlantMapper;
import by.trubetski.models.Ecosystem;
import by.trubetski.models.Plant;

import java.util.Scanner;

import static java.lang.System.out;

public class PlantManager {

    public static void addPlantsToEcosystem(Scanner scanner, Ecosystem ecosystem) {
        PlantMapper plantMapper = PlantMapper.INSTANCE;

        while (true) {
            out.println("Добавление нового растения:");
            out.print("Название растения: ");
            String name = scanner.nextLine();
            out.print("Возраст растения: ");
            int age = Integer.parseInt(scanner.nextLine());
            out.print("Вес растения (в кг): ");
            int weight = Integer.parseInt(scanner.nextLine());
            out.print("Продолжительность жизни (лет): ");
            int timeLife = Integer.parseInt(scanner.nextLine());
            out.print("Количество: ");
            int count = Integer.parseInt(scanner.nextLine());

            PlantDto plantDto = PlantDto.builder()
                    .name(name)
                    .age(age)
                    .weight(weight)
                    .timeLife(timeLife)
                    .count(count)
                    .build();

            Plant plant = plantMapper.toEntity(plantDto);

            ecosystem.getPlants().add(plant);
            out.println("Растение добавлено.");

            out.print("Хотите добавить ещё одно растение? (да/нет): ");
            if (!scanner.nextLine().equalsIgnoreCase("да")) {
                break;
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

        out.print("Введите новый возраст для растения (текущий: " + plant.getAge() + "): ");
        plant.setAge(Integer.parseInt(scanner.nextLine()));

        out.print("Введите новый вес для растения (текущий: " + plant.getWeight() + "): ");
        plant.setWeight(Integer.parseInt(scanner.nextLine()));

        out.print("Введите новую продолжительность жизни для растения (текущая: " + plant.getTimeLife() + "): ");
        plant.setTimeLife(Integer.parseInt(scanner.nextLine()));

        out.print("Введите новое количество (текущая: " + plant.getCount() + "): ");
        plant.setCount(Integer.parseInt(scanner.nextLine()));

        out.println("Растение успешно обновлено: " + plant);
    }
}
