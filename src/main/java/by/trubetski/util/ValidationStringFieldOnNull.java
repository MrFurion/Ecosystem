package by.trubetski.util;

import java.util.Scanner;

import static java.lang.System.out;

public class ValidationStringFieldOnNull {
    public static String getInputString(Scanner scanner, String validPattern) {
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                out.println("Ошибка: значение не может быть пустым. Попробуйте снова.");
                continue;
            }
            if (!input.matches(validPattern)) {
                out.println("Ошибка: неверный ввод. Введите 1, 2 или 3.");
                continue;
            }
            return input;
        }
    }
}
