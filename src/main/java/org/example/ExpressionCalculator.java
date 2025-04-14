package org.example;
import java.util.Stack;

/**
 * Калькулятор математических выражений, представленных в виде строки.
 * Поддерживает операции: +, -, *, /, скобки () и десятичные числа.
 * Использует алгоритм на основе двух стеков (чисел и операторов) с учётом приоритетов операций. *
 */
public class ExpressionCalculator {
    public ExpressionCalculator(){}

    /**
     * Вычисляет значение математического выражения.
     *
     * @param expression строка с математическим выражением (например, "2 * (3 + 4)")
     * @return результат вычисления
     * @throws IllegalArgumentException если выражение содержит некорректные символы,
     * несогласованные скобки, деление на ноль, синтаксические ошибки
     */
    public static double evaluate(String expression) throws IllegalArgumentException {
        Stack<Double> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            //System.out.println(operators);
            //System.out.println(numbers);

            char c = expression.charAt(i);

            // Пропускаем пробелы
            if (c == ' ') {
                continue;
            }

            // Если символ - цифра, собираем все число
            if (Character.isDigit(c) || c == '.') {
                StringBuilder numStr = new StringBuilder();
                while (i < expression.length() &&
                        (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    numStr.append(expression.charAt(i++));
                }
                i--;

                try {
                    numbers.push(Double.parseDouble(numStr.toString()));
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid number format: " + numStr);
                }
            }
            // Обработка открывающей скобки
            else if (c == '(') {
                operators.push(c);
            }
            // Обработка закрывающей скобки
            else if (c == ')') {
                while (!operators.isEmpty() && operators.peek() != '(') {
                    applyOperation(numbers, operators);
                }
                if (operators.isEmpty()) {
                    throw new IllegalArgumentException("Mismatched parentheses");
                }
                operators.pop(); // Удаляем открывающую скобку
            }
            // Обработка операторов
            else if (isOperator(c)) {
                while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(c)) {
                    applyOperation(numbers, operators);
                }
                operators.push(c);
            } else {
                throw new IllegalArgumentException("Invalid character: " + c);
            }
        }

        // Выполняем оставшиеся операции
        while (!operators.isEmpty()) {
            if (operators.peek() == '(') {
                throw new IllegalArgumentException("Mismatched parentheses");
            }
            applyOperation(numbers, operators);
        }

        if (numbers.size() != 1 || !operators.isEmpty()) {
            throw new IllegalArgumentException("Invalid expression");
        }

        return numbers.pop();
    }
    /**
     * Проверяет, является ли символ оператором.
     *
     * @param c проверяемый символ
     * @return true, если символ это +, -, * или /
     */
    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    /**
     * Применяет операцию к двум верхним числам из стека.
     *
     * @param numbers стек чисел
     * @param operators стек операторов
     * @throws IllegalArgumentException если недостаточно чисел в стеке, деление на ноль, неизвестный оператор
     */
    private static void applyOperation(Stack<Double> numbers, Stack<Character> operators) {
        if (numbers.size() < 2 || operators.isEmpty()) {
            throw new IllegalArgumentException("Invalid expression");
        }

        char op = operators.pop();
        double b = numbers.pop();
        double a = numbers.pop();

        switch (op) {
            case '+':
                numbers.push(a + b);
                break;
            case '-':
                numbers.push(a - b);
                break;
            case '*':
                numbers.push(a * b);
                break;
            case '/':
                if (b == 0) {
                    throw new IllegalArgumentException("Division by zero");
                }
                numbers.push(a / b);
                break;
            default:
                throw new IllegalArgumentException("Unknown operator: " + op);
        }
    }

    /**
     * Возвращает приоритет оператора.
     *
     * @param op оператор (+, -, *, /)
     * @return числовой приоритет (2 для * /, 1 для + -)
     */
    private static int precedence(char op) {
        switch (op) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return 0;
        }
    }

}
