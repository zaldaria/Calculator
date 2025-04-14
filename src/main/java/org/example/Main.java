package org.example;

public class Main {
    public static void main(String[] args) {
        String[] testExpressions = {
                "3 + 4 * 2 / (1 - 5)",
                "2 * (5 + 5 * 2) / 3",
                "10.5 + 2 * 3.5",
                "3 + (2 * 5)",
                "3 + 2 * 5",
                "100 / (2 * 5)",
                "3 + ",
                "3 + (2 * 5",
                "3 + 2 * * 5",
                "3 / 0"
        };
        ExpressionCalculator calculator = new ExpressionCalculator();
        for (String expr : testExpressions) {
            System.out.print("Expression: " + expr + " => ");
            try {
                double result = calculator.evaluate(expr);
                System.out.println("Result: " + result);
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}

