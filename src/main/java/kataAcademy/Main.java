package kataAcademy;

import kataAcademy.converter.Converter;
import kataAcademy.converter.RomanArabicConverter;

import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    private static final Converter converter = new RomanArabicConverter();
    private static final String EXIT = "Exit";
    private static String currentMathOperation;
    private static String aString;
    private static String bString;
    private static boolean isRomanCalculation;

    public static void main(String[] args) {
        String inputData = sendResponse();
        while (!EXIT.equalsIgnoreCase(inputData)) {
            System.out.println(calc(inputData));
            inputData = sendResponse();
        }
    }

    public static String calc(String inputData) {
        int result = 0;
        getMathOperationSymbol(inputData);
        int a = getCurrentNumber(aString);
        int b = getCurrentNumber(bString);
        result = calculate(a, b);
        if (isRomanCalculation) {
            return converter.convertToRoman(result);
        }

        return String.valueOf(result);
    }

    private static int calculate(int a, int b) {

        return switch (currentMathOperation) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> a / b;
            default -> throw new IllegalStateException("Unexpected value: " + currentMathOperation);
        };
    }

    private static int getCurrentNumber(String data) {
        int number;
        try {
            number = Integer.parseInt(data);
        } catch (NumberFormatException e) {
            isRomanCalculation = true;
            number = converter.convertToArabic(data);
        }
        return number;
    }



    private static void getMathOperationSymbol(String inputData) throws IllegalArgumentException {
        char[] chars = inputData.toCharArray();

        for (char symbol : chars) {
            if (isMathOperationSymbol(symbol)) {
                currentMathOperation = String.valueOf(symbol);
            }
        }
        if (currentMathOperation == null) {
            throw new IllegalArgumentException("Math operation symbol not found");
        }
        String[] inputSplit = inputData.split(Pattern.quote(currentMathOperation));
        if (isNotValid(inputSplit)) {
            throw new IllegalArgumentException();
        }
        aString = inputSplit[0];
        bString = inputSplit[1];
    }

    private static boolean isMathOperationSymbol(char symbol) {
        return Arrays.asList('+', '-', '*', '/').contains(symbol);
    }

    private static boolean isNotValid(String[] data) {
        boolean isNumeric0 = data[0].matches("\\d+");
        boolean isNumeric2 = data[1].matches("\\d+");


        return isNumeric0 != isNumeric2;
    }

    private static String sendResponse() {
        System.out.println("Введите выражение:");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}