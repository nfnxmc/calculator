package org.mnc;


import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Calculator {
    private static final String NEW_LINE = "\n";
    public static final String DEFAULT_DELIMITER = ";";
    public static final List<String> ESCAPES = List.of("|", "*", "?", "+", ".");

    private String delimiter(String numbers) {
        if (Objects.isNull(numbers) || numbers.isBlank() || !numbers.startsWith("//")) {
            return DEFAULT_DELIMITER;
        }
        int firstIndexOfNewLine = numbers.indexOf("\n");
        String separator = numbers.substring(2, firstIndexOfNewLine);
        return ESCAPES.contains(separator) ? "\\" + separator : separator;
    }

    private int numbersStart(String numbers) {
        if (Objects.isNull(numbers) || numbers.isBlank() || !numbers.startsWith("//")) {
            return 0;
        }

        return numbers.indexOf("\n") + 1;
    }

    public int add(String numbers) {
        String delimiter = delimiter(numbers);
        int numbersStart = numbersStart(numbers);
        String[] operands = numbers.substring(numbersStart).split(delimiter);
        if (operands.length > 1 && NEW_LINE.equals(operands[operands.length - 1])) throw new IllegalArgumentException();
        return Arrays.stream(operands).flatMap(s -> Arrays.stream(s.split("\\n"))).mapToInt(s -> s.isBlank() ? 0 : Integer.parseInt(s)).sum();
    }
}
