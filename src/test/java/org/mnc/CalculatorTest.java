package org.mnc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculatorTest {

    @Test(expected = NumberFormatException.class)
    public void step1NumberFormat(){
        new Calculator().add("1,&");
    }
    @Test(expected = IllegalArgumentException.class)
    public void step3EmptyLastLine(){
        new Calculator().add("1,\n");
    }

    @Test
    public void step1() {
        Calculator calculator = new Calculator();

        assertEquals(0, calculator.add(""));
        assertEquals(1, calculator.add("1"));
        assertEquals(3, calculator.add("1,2"));
        assertEquals(45, calculator.add("0,1,2,3,4,5,6,7,8,9"));
    } @Test
    public void step3() {
        Calculator calculator = new Calculator();

        assertEquals(0, calculator.add(""));
        assertEquals(1, calculator.add("1\n"));
        assertEquals(6, calculator.add("1\n2,3"));
    }
}
