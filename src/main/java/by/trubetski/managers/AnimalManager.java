package by.trubetski.managers;

import by.trubetski.dto.AnimalDto;
import by.trubetski.enums.DietType;
import by.trubetski.enums.Gender;
import by.trubetski.mapper.AnimalMapper;
import by.trubetski.models.Animal;
import by.trubetski.models.Ecosystem;
import by.trubetski.util.ValidationIntFildOnNull;
import by.trubetski.util.ValidationStringFieldOnNull;
import by.trubetski.util.ValidationUtils;

import java.util.Scanner;

import static by.trubetski.constants.AnimalDtoConstants.VALUE_DIET_TYPE;
import static by.trubetski.constants.AnimalDtoConstants.VALUE_GENDER;
import static by.trubetski.constants.AnimalManagerConstants.ADD_ANOTHER_ANIMAL;
import static by.trubetski.constants.AnimalManagerConstants.AGE_ANIMAL;
import static by.trubetski.constants.AnimalManagerConstants.ANIMAL_ADD;
import static by.trubetski.constants.AnimalManagerConstants.ANIMAL_SUCCESSFULLY_UPDATE;
import static by.trubetski.constants.AnimalManagerConstants.COUNT_OF_OFFSPRING;
import static by.trubetski.constants.AnimalManagerConstants.CREAT_NEW_ANIMAL;
import static by.trubetski.constants.AnimalManagerConstants.DAILY_FOOD_REQUIREMENT;
import static by.trubetski.constants.AnimalManagerConstants.DIET_TYPE;
import static by.trubetski.constants.AnimalManagerConstants.GENDER;
import static by.trubetski.constants.AnimalManagerConstants.INPUT_NAME_OF_ANIMAL;
import static by.trubetski.constants.AnimalManagerConstants.INPUT_NEW;
import static by.trubetski.constants.AnimalManagerConstants.NAME_ANIMAL;
import static by.trubetski.constants.AnimalManagerConstants.NOT_FOUND_ANIMAL_BY_NAME;
import static by.trubetski.constants.AnimalManagerConstants.PRESENT_MOMENT;
import static java.lang.System.out;

public class AnimalManager {

    public static void addAnimalsToEcosystem(Scanner scanner, Ecosystem ecosystem) {
        AnimalMapper animalMapper = AnimalMapper.INSTANCE;

        while (true) {
            out.println(CREAT_NEW_ANIMAL);
            out.print(NAME_ANIMAL);
            String name = scanner.nextLine();

            out.print(GENDER);
            String genderType = ValidationStringFieldOnNull.getInputString(scanner, VALUE_GENDER);
            String gender = getGenderAnimal(genderType);

            out.print(DIET_TYPE);
            String type = ValidationStringFieldOnNull.getInputString(scanner, VALUE_DIET_TYPE);
            String dietType = getDietTypeAnimal(type);

            Integer age = ValidationIntFildOnNull.getInputInt(scanner, AGE_ANIMAL);
            if (age == null) continue;

            Integer offspringCount = ValidationIntFildOnNull.getInputInt(scanner, COUNT_OF_OFFSPRING);
            if (offspringCount == null) continue;

            Integer dailyFoodRequirement = ValidationIntFildOnNull.getInputInt(scanner, DAILY_FOOD_REQUIREMENT);
            if (dailyFoodRequirement == null) continue;

            AnimalDto animalDto = AnimalDto.builder()
                    .name(name)
                    .gender(gender)
                    .dietType(dietType)
                    .age(age)
                    .offspringCount(offspringCount)
                    .dailyFoodRequirement(dailyFoodRequirement)
                    .build();

            try {
                ValidationUtils.validate(animalDto);

                Animal animal = animalMapper.toEntity(animalDto);
                ecosystem.getAnimals().add(animal);
                out.println(ANIMAL_ADD);

                out.print(ADD_ANOTHER_ANIMAL);
                if (!scanner.nextLine().equalsIgnoreCase("да")) {
                    break;
                }
            } catch (IllegalArgumentException e) {
                out.println("Ошибка валидации: " + e.getMessage());
                out.println("Пожалуйста, введите данные снова.");
            }
        }
    }

    public static void updateAnimalInEcosystem(Scanner scanner, Ecosystem ecosystem) {
        out.print(INPUT_NAME_OF_ANIMAL);
        String animalName = scanner.nextLine();

        Animal animal = ecosystem.getAnimals().stream()
                .filter(a -> a.getName().equalsIgnoreCase(animalName))
                .findFirst()
                .orElse(null);

        if (animal == null) {
            out.println(NOT_FOUND_ANIMAL_BY_NAME);
            return;
        }

        Integer newAge = ValidationIntFildOnNull.getInputInt(scanner, INPUT_NEW + AGE_ANIMAL + PRESENT_MOMENT + animal.getAge() + "): ");
        if (newAge != null) {
            animal.setAge(newAge);
        }

        out.print(INPUT_NEW + GENDER + PRESENT_MOMENT + animal.getGender() + "): ");
        String genderType = ValidationStringFieldOnNull.getInputString(scanner, VALUE_GENDER);
        animal.setGender(getGenderAnimal(genderType));

        out.print(INPUT_NEW + DIET_TYPE + PRESENT_MOMENT + animal.getDietType() + "): ");
        String type = ValidationStringFieldOnNull.getInputString(scanner, VALUE_GENDER);
        animal.setDietType(getDietTypeAnimal(type));

        Integer newOffspringCount = ValidationIntFildOnNull.getInputInt(scanner, INPUT_NEW + COUNT_OF_OFFSPRING + PRESENT_MOMENT + animal.getOffspringCount() + "): ");
        if (newOffspringCount != null) {
            animal.setOffspringCount(newOffspringCount);
        }

        Integer newDailyFoodRequirement = ValidationIntFildOnNull.getInputInt(scanner, INPUT_NEW + DAILY_FOOD_REQUIREMENT + PRESENT_MOMENT + animal.getDailyFoodRequirement() + "): ");
        if (newDailyFoodRequirement != null) {
            animal.setDailyFoodRequirement(newDailyFoodRequirement);
        }

        out.println(ANIMAL_SUCCESSFULLY_UPDATE + animal);
    }

    private static String getDietTypeAnimal(String type) {
        return switch (type) {
            case "1" -> DietType.HERBIVORE.getDietType();
            case "2" -> DietType.CARNIVORE.getDietType();
            case "3" -> DietType.OMNIVORE.getDietType();
            default -> "";
        };
    }

    private static String getGenderAnimal(String type) {
        return switch (type) {
            case "1" -> Gender.MALE.getGender();
            case "2" -> Gender.FEMALE.getGender();
            case "3" -> Gender.BINARY.getGender();
            default -> "";
        };
    }
}
