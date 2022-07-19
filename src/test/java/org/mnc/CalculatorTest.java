package org.mnc;

import org.junit.Test;

import java.util.logging.Logger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CalculatorTest {

    public static final Logger LOGGER = Logger.getLogger("CalculatorTestLogger");

    @Test(expected = NumberFormatException.class)
    public void step1NumberFormat() {
        try {
            new Calculator().add("1,&");
        } catch (NegativeNumberException e) {
            LOGGER.info(e.getMessage());
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void step3EmptyLastLine() {
        try {
            new Calculator().add("1,\n");
        } catch (NegativeNumberException e) {
            LOGGER.info(e.getMessage());
        }
    }

    @Test
    public void step1() {
        Calculator calculator = new Calculator();

        try {
            assertEquals(0, calculator.add(""));
            assertEquals(1, calculator.add("1"));
            assertEquals(3, calculator.add("1;2"));
            assertEquals(45, calculator.add("0;1;2;3;4;5;6;7;8;9"));
        } catch (NegativeNumberException e) {
            LOGGER.info(e.getMessage());
        }

    }

    @Test
    public void step3() {
        Calculator calculator = new Calculator();
        try {
            assertEquals(0, calculator.add(""));
            assertEquals(1, calculator.add("1\n"));
            assertEquals(6, calculator.add("1\n2;3"));
        } catch (NegativeNumberException e) {
            LOGGER.info(e.getMessage());
        }
    }

    @Test
    public void step4() {
        Calculator calculator = new Calculator();
        try {
            assertEquals(0, calculator.add("//;\n"));
            assertEquals(6, calculator.add("//|\n1\n2|3"));
            assertEquals(6, calculator.add("//+\n1+2+3"));
        } catch (NegativeNumberException e) {
            LOGGER.info(e.getMessage());
        }
    }

    @Test(expected = NegativeNumberException.class)
    public void step5NegativeNumbers() throws NegativeNumberException {
        Calculator calculator = new Calculator();
        try {
            assertEquals(6, calculator.add("//|\n1\n2|-3"));
        } catch (NegativeNumberException ne) {
            assertTrue(ne.getMessage().contains("-3"));
            throw ne;
        }
        try {
            assertEquals(6, calculator.add("//|\n1\n2|-1|-5"));
        } catch (NegativeNumberException ne) {
            assertTrue(ne.getMessage().contains("-1"));
            assertTrue(ne.getMessage().contains("-5"));
            throw ne;
        }
    }

    @Test
    public void ignoreNumberBiggerThan1000() {
        Calculator calculator = new Calculator();
        try {
            assertEquals(0, calculator.add("//;\n"));
            assertEquals(1006, calculator.add("//|\n1\n2|3|1000"));
            assertEquals(6, calculator.add("//+\n1+2+3+1002"));
            assertEquals(6, calculator.add("//+\n1+2+3+1002+999999"));
        } catch (NegativeNumberException e) {
            LOGGER.info(e.getMessage());
        }
    }

    @Test
    public void step7DifferentLengthSeparator() {
        Calculator calculator = new Calculator();
        try {
            assertEquals(0, calculator.add("//***\n"));
            assertEquals(1006, calculator.add("//|sep\n1\n2|sep3|sep1000"));
            assertEquals(6, calculator.add("//***\n1***2***3"));
        } catch (NegativeNumberException e) {
            LOGGER.info(e.getMessage());
        }
    }

    @Test
    public void step8MultipleDelimiters() {
        Calculator calculator = new Calculator();
        try {
            assertEquals(0, calculator.add("//***\n"));
            assertEquals(1006, calculator.add("//[n][;][***]\n1***2n3;1000"));
            assertEquals(6, calculator.add("//[*][%]\n1*2%3"));
        } catch (NegativeNumberException e) {
            LOGGER.info(e.getMessage());
        }
    }
}
