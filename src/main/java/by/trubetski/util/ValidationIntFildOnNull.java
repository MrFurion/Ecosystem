package by.trubetski.util;

import java.util.Scanner;

import static java.lang.System.out;

public class ValidationIntFildOnNull {
    public static Integer getInputInt(Scanner scanner, String prompt) {
        while (true) {
            out.print(prompt);
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                out.println("Ошибка: значение не может быть пустым. Попробуйте снова.");
                continue;
            }
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                out.println("Ошибка: введите корректное число. Попробуйте снова.");
            }
        }
    }
}
