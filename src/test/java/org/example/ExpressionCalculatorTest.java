package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ExpressionCalculatorTest {

    @Test
    public void testBasicOperations() {
        assertEquals(5.0, ExpressionCalculator.evaluate("2 + 3"));
        assertEquals(-1.0, ExpressionCalculator.evaluate("2 - 3"));
        assertEquals(6.0, ExpressionCalculator.evaluate("2 * 3"));
        assertEquals(0.5, ExpressionCalculator.evaluate("1 / 2"));
    }

    @Test
    public void testOperatorPrecedence() {
        assertEquals(7.0, ExpressionCalculator.evaluate("1 + 2 * 3"));
        assertEquals(9.0, ExpressionCalculator.evaluate("(1 + 2) * 3"));
        assertEquals(1.0, ExpressionCalculator.evaluate("3 + 4 * 2 / (1 - 5)"));
    }

    @Test
    public void testDoubleExpressions() {
        assertEquals(10.0, ExpressionCalculator.evaluate("2 * (5 + 5 * 2) / 3"));
        assertEquals(17.5, ExpressionCalculator.evaluate("10.5 + 2 * 3.5"));
        assertEquals(13.0, ExpressionCalculator.evaluate("3 + (2 * 5)"));
        assertEquals(13.0, ExpressionCalculator.evaluate("3 + 2 * 5"));
        assertEquals(10.0, ExpressionCalculator.evaluate("100 / (2 * 5)"));
    }

    @Test
    public void testInvalidExpressions() {
        assertThrows(IllegalArgumentException.class, () -> ExpressionCalculator.evaluate("3 + "));
        assertThrows(IllegalArgumentException.class, () -> ExpressionCalculator.evaluate("3 + (2 * 5"));
        assertThrows(IllegalArgumentException.class, () -> ExpressionCalculator.evaluate("3 + 2 * * 5"));
        assertThrows(IllegalArgumentException.class, () -> ExpressionCalculator.evaluate("3 / 0"));
        assertThrows(IllegalArgumentException.class, () -> ExpressionCalculator.evaluate("abc"));
    }

}