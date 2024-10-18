package by.trubetski.managers;

import by.trubetski.models.Ecosystem;
import by.trubetski.services.EcosystemServices;
import by.trubetski.services.impl.EcosystemServicesImpl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class InputManager {
    private static final EcosystemServices ecosystemServices = new EcosystemServicesImpl();
    private Map<String, Ecosystem> ecosystems;

    public InputManager() {
        this.ecosystems = new HashMap<>();
    }

    public static void input() {
        Scanner scanner = new Scanner(in);
        String choice;

        while (true) {
            out.println("\n--- Ecosystem Menu ---");
            out.println("1. Create new ecosystem");
            out.println("2. Load ecosystem from file");
            out.println("3. Save ecosystem to file");
            out.println("4. List all ecosystems");
            out.println("5. Exit");
            out.print("Enter your choice: ");
            choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    createNewEcosystem(scanner);
                    break;
                case "2":
                    loadEcosystem(scanner);
                    break;
                case "3":
                    saveEcosystem(scanner);
                    break;
                case "4":
                    ecosystemServices.listEcosystems();
                    break;
                case "5":
                    out.println("Exiting...");
                    return;
                default:
                    out.println("Invalid choice. Please try again.");
            }
        }
    }
    private static void createNewEcosystem(Scanner scanner) {
        System.out.print("Enter the name of the new ecosystem: ");
        String name = scanner.nextLine();
        Ecosystem newEcosystem = new Ecosystem(name);
        ecosystemServices.addEcosystem(newEcosystem);
        System.out.println("New ecosystem '" + name + "' created.");
    }

    private static void loadEcosystem(Scanner scanner) {
        System.out.print("Enter the name of the ecosystem: ");
        String ecosystemName = scanner.nextLine();
        System.out.print("Enter the plants file path: ");
        String plantsFile = scanner.nextLine();
        System.out.print("Enter the animals file path: ");
        String animalsFile = scanner.nextLine();

        try {
            ecosystemServices.loadEcosystemFromFile(ecosystemName, plantsFile, animalsFile);
            System.out.println("Ecosystem '" + ecosystemName + "' loaded from files.");
        } catch (IOException e) {
            System.out.println("Error loading ecosystem: " + e.getMessage());
        }
    }


    private static void saveEcosystem(Scanner scanner) {
        System.out.print("Enter the name of the ecosystem: ");
        String ecosystemName = scanner.nextLine();
        System.out.print("Enter the file path to save plants: ");
        String plantsFile = scanner.nextLine();
        System.out.print("Enter the file path to save animals: ");
        String animalsFile = scanner.nextLine();

        try {
            ecosystemServices.saveEcosystemToFile(ecosystemName, plantsFile, animalsFile);
            System.out.println("Ecosystem '" + ecosystemName + "' saved to files.");
        } catch (IOException e) {
            System.out.println("Error saving ecosystem: " + e.getMessage());
        }
    }
}
