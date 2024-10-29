package by.trubetski.managers;

import by.trubetski.dto.AnimalDto;
import by.trubetski.enums.DietType;
import by.trubetski.mapper.AnimalMapper;
import by.trubetski.models.Animal;
import by.trubetski.models.Ecosystem;

import java.util.Scanner;

import static java.lang.System.out;

public class AnimalManager {

    public static final String ДОБАВЛЕНИЕ_НОВОГО_ЖИВОТНОГО = "Добавление нового животного:";
    public static final String НАЗВАНИЕ_ЖИВОТНОГО = "Название животного: ";
    public static final String ПОЛ_МУЖСКОЙ_ЖЕНСКИЙ = "Пол (Мужской/Женский): ";
    public static final String ТИП_ПИТАНИЯ_ТРАВОЯДНОЕ_1_ХИЩНИК_2_ВСЕЯДНОЕ_3 = "Тип питания (Травоядное : 1 /Хищник : 2 /Всеядное : 3): ";
    public static final String ВОЗРАСТ_ЖИВОТНОГО = "Возраст животного: ";
    public static final String КОЛИЧЕСТВО_ПОТОМСТВА = "Количество потомства: ";
    public static final String ЕЖЕДНЕВНАЯ_ПОТРЕБНОСТЬ_В_ПИЩЕ_КГ = "Ежедневная потребность в пище (кг): ";
    public static final String ЖИВОТНОЕ_ДОБАВЛЕНО = "Животное добавлено.";
    public static final String ХОТИТЕ_ДОБАВИТЬ_ЕЩЁ_ОДНО_ЖИВОТНОЕ_ДА_НЕТ = "Хотите добавить ещё одно животное? (да/нет): ";
    public static final String ВВЕДИТЕ_ИМЯ_ЖИВОТНОГО_ДЛЯ_ОБНОВЛЕНИЯ = "Введите имя животного для обновления: ";
    public static final String ЖИВОТНОЕ_С_ТАКИМ_ИМЕНЕМ_НЕ_НАЙДЕНО = "Животное с таким именем не найдено.";
    public static final String ВВЕДИТЕ_НОВЫЙ = "Введите новый ";
    public static final String ЖИВОТНОЕ_УСПЕШНО_ОБНОВЛЕНО = "Животное успешно обновлено: ";

    public static void addAnimalsToEcosystem(Scanner scanner, Ecosystem ecosystem) {
        AnimalMapper animalMapper = AnimalMapper.INSTANCE;

        while (true) {
            out.println(ДОБАВЛЕНИЕ_НОВОГО_ЖИВОТНОГО);
            out.print(НАЗВАНИЕ_ЖИВОТНОГО);
            String name = scanner.nextLine();
            out.print(ПОЛ_МУЖСКОЙ_ЖЕНСКИЙ);
            String gender = scanner.nextLine();
            out.print(ТИП_ПИТАНИЯ_ТРАВОЯДНОЕ_1_ХИЩНИК_2_ВСЕЯДНОЕ_3);
            String type = scanner.nextLine();

            String dietType = getDietTypeAnimal(type);

            out.print(ВОЗРАСТ_ЖИВОТНОГО);
            int age = Integer.parseInt(scanner.nextLine());
            out.print(КОЛИЧЕСТВО_ПОТОМСТВА);
            int offspringCount = Integer.parseInt(scanner.nextLine());
            out.print(ЕЖЕДНЕВНАЯ_ПОТРЕБНОСТЬ_В_ПИЩЕ_КГ);
            int dailyFoodRequirement = Integer.parseInt(scanner.nextLine());

            AnimalDto animalDto = AnimalDto.builder()
                    .name(name)
                    .gender(gender)
                    .dietType(dietType)
                    .age(age)
                    .offspringCount(offspringCount)
                    .dailyFoodRequirement(dailyFoodRequirement)
                    .build();

            Animal animal = animalMapper.toEntity(animalDto);

            ecosystem.getAnimals().add(animal);
            out.println(ЖИВОТНОЕ_ДОБАВЛЕНО);

            out.print(ХОТИТЕ_ДОБАВИТЬ_ЕЩЁ_ОДНО_ЖИВОТНОЕ_ДА_НЕТ);
            if (!scanner.nextLine().equalsIgnoreCase("да")) {
                break;
            }
        }
    }

    public static void updateAnimalInEcosystem(Scanner scanner, Ecosystem ecosystem) {
        out.print(ВВЕДИТЕ_ИМЯ_ЖИВОТНОГО_ДЛЯ_ОБНОВЛЕНИЯ);
        String animalName = scanner.nextLine();

        Animal animal = ecosystem.getAnimals().stream()
                .filter(a -> a.getName().equalsIgnoreCase(animalName))
                .findFirst()
                .orElse(null);

        if (animal == null) {
            out.println(ЖИВОТНОЕ_С_ТАКИМ_ИМЕНЕМ_НЕ_НАЙДЕНО);
            return;
        }

        out.print(ВВЕДИТЕ_НОВЫЙ + ВОЗРАСТ_ЖИВОТНОГО + " (текущий: " + animal.getAge() + "): ");
        animal.setAge(Integer.parseInt(scanner.nextLine()));

        out.print(ВВЕДИТЕ_НОВЫЙ + ПОЛ_МУЖСКОЙ_ЖЕНСКИЙ + " (текущий: " + animal.getGender() + "): ");
        animal.setGender(scanner.nextLine());

        out.print(ВВЕДИТЕ_НОВЫЙ + ТИП_ПИТАНИЯ_ТРАВОЯДНОЕ_1_ХИЩНИК_2_ВСЕЯДНОЕ_3 +
                " (текущий: " + animal.getDietType() + "): ");
        String type = scanner.nextLine();

        animal.setDietType(getDietTypeAnimal(type));

        out.print(ВВЕДИТЕ_НОВЫЙ + КОЛИЧЕСТВО_ПОТОМСТВА + " (текущий: " + animal.getOffspringCount() + "): ");
        animal.setOffspringCount(Integer.parseInt(scanner.nextLine()));

        out.print(ВВЕДИТЕ_НОВЫЙ + ЕЖЕДНЕВНАЯ_ПОТРЕБНОСТЬ_В_ПИЩЕ_КГ + " (текущий: " + animal.getDailyFoodRequirement() + "): ");
        animal.setDailyFoodRequirement(Integer.parseInt(scanner.nextLine()));

        out.println(ЖИВОТНОЕ_УСПЕШНО_ОБНОВЛЕНО + animal);
    }

    private static String getDietTypeAnimal(String type) {
        return switch (type) {
            case "1" -> DietType.HERBIVORE.getDietType();
            case "2" -> DietType.CARNIVORE.getDietType();
            case "3" -> DietType.OMNIVORE.getDietType();
            default -> "";
        };
    }
}
