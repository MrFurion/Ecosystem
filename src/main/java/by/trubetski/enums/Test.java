package by.trubetski.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<Integer> list2 = new ArrayList<>(Arrays.asList(1, 2, 3));

        var stream = list2.stream()
                .peek(System.out::println);
        list2.addAll(List.of(5,6,7));

        stream.forEach(System.out::println);
    }


}
