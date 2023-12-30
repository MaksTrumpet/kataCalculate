package kataAcademy.converter;

public class RomanArabicConverter implements Converter {

    @Override
    public String convertToRoman(int number) {
        if (number < 1 || number > 3999) {
            throw new IllegalArgumentException("Number out of range for Roman numerals");
        }

        String[] romanSymbols = {"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M"};
        int[] arabicValues = {1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};

        StringBuilder result = new StringBuilder();
        int index = arabicValues.length - 1;

        while (number > 0) {
            if (number >= arabicValues[index]) {
                result.append(romanSymbols[index]);
                number -= arabicValues[index];
            } else {
                index--;
            }
        }

        return result.toString();
    }

    @Override
    public int convertToArabic(String roman) {
        if (roman == null || roman.isEmpty()) {
            throw new IllegalArgumentException("Invalid Roman numeral");
        }

        char[] romanNumerals = {'I', 'V', 'X', 'L', 'C', 'D', 'M'};

        int[] values = {1, 5, 10, 50, 100, 500, 1000};

        int result = 0;
        int prevValue = 0;

        for (int i = roman.length() - 1; i >= 0; i--) {
            char currentChar = roman.charAt(i);
            int currentIndex = indexOf(romanNumerals, currentChar);

            if (currentIndex == -1) {
                throw new IllegalArgumentException("Invalid Roman numeral");
            }

            int currentValue = values[currentIndex];

            if (currentValue < prevValue) {
                result -= currentValue;
            } else {
                result += currentValue;
            }

            prevValue = currentValue;
        }

        return result;
    }

    private int indexOf(char[] array, char target) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == target) {
                return i;
            }
        }
        return -1;
    }
}
