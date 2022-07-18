package org.mnc;


import java.util.Arrays;

public class Calculator {
    public int add(String numbers){
        return Arrays.stream(numbers.split(",")).mapToInt(s -> s.isBlank() ? 0: Integer.parseInt(s)).sum();
    }
}
