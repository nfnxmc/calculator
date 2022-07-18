package org.mnc;


import java.util.Arrays;

public class Calculator {
    private static final String NEW_LINE = "\n";
    public int add(String numbers) {
        String[] operands = numbers.split(",");
        if (operands.length > 1 && NEW_LINE.equals(operands[operands.length - 1])) throw new IllegalArgumentException();
        return Arrays.stream(operands).flatMap(s -> Arrays.stream(s.split("\\n"))).mapToInt(s -> s.isBlank() ? 0 : Integer.parseInt(s)).sum();
    }
}
