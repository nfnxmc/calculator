package org.mnc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculatorTest {

    @Test(expected = NumberFormatException.class)
    public void step1NumberFormat(){
        new Calculator().add("1,&");
    }

    @Test(expected = IllegalArgumentException.class)
    public void step1IllegalArgument(){
        new Calculator().add("1,2,3");
    }
    @Test
    public void step1() {
        Calculator calculator = new Calculator();

        assertEquals(0, calculator.add(""));
        assertEquals(1, calculator.add("1"));
        assertEquals(3, calculator.add("1,2"));
    }
}
