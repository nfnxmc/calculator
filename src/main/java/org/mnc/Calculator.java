package org.mnc;


public class Calculator {
    public int add(String numbers) {
        if (numbers.isBlank()) return 0;
        String[] operands = numbers.split(",");
        if (operands.length > 2) throw new IllegalArgumentException("Can add up to two numbers");
        return operands.length == 1 ? Integer.parseInt(operands[0]) : Integer.parseInt(operands[0]) + Integer.parseInt(operands[1]);
    }
}
