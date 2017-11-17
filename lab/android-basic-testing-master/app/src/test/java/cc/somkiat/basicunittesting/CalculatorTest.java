package cc.somkiat.basicunittesting;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class CalculatorTest {
    @Test
    public void two_plus_two_equal_four(){
        Calculator calculator = new Calculator();
        int result = calculator.plus(2,2);
        assertEquals(4, result);
    }
    @Test
    public void seven_plus_two_equal_nine(){
        Calculator calculator = new Calculator();
        int result = calculator.plus(7, 2);
        assertEquals(9, result);
    }
}
