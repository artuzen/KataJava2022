import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Calculator {
    private static java.lang.Exception Exception;

    public static void main(String[] args) {
        String line = null;
        try (Scanner scanner = new Scanner(System.in)){
            line = scanner.nextLine();
        } catch (Exception e) {

        }
        System.out.println(calc(line));
    }

    enum RomanNumeral {
        I(1), IV(4), V(5), IX(9), X(10),
        XL(40), L(50), XC(90), C(100),
        CD(400), D(500), CM(900), M(1000);

        private int value;

        RomanNumeral(int value){
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static List getReverseSortedValues() {
            return Arrays.stream(values())
                    .sorted(Comparator.comparing((RomanNumeral e) -> e.value).reversed())
                    .collect(Collectors.toList());
        }
    }

    public static int romanToArabic(String input) {
        String romanNumeral = input.toUpperCase();
        int result = 0;

        List romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;

        while ((romanNumeral.length() > 0) && (i < romanNumerals.size())) {
            RomanNumeral symbol = (RomanNumeral) romanNumerals.get(i);
            if (romanNumeral.startsWith(symbol.name())) {
                result += symbol.getValue();
                romanNumeral = romanNumeral.substring(symbol.name().length());
            } else {
                i++;
            }
        }

        return result;
    }

    public static String arabicToRoman(int number) {
        List romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;
        StringBuilder sb = new StringBuilder();

        while ((number > 0) && (i < romanNumerals.size())) {
            RomanNumeral currentSymbol = (RomanNumeral) romanNumerals.get(i);
            if (currentSymbol.getValue() <= number) {
                sb.append(currentSymbol.name());
                number -= currentSymbol.getValue();
            } else {
                i++;
            }
        }
        return sb.toString();
    }

    public static String calc(String line){
        try {
            String firstNumber = line.substring(0, line.indexOf(" "));
            String operandLine = line.substring(line.indexOf(" ") + 1, line.indexOf(" ") + 2);
            String secondNumber = line.substring(line.indexOf(" ") + 3);

            if (line.indexOf(" ", line.indexOf(" ")+3) != -1) {
                throw Exception;
            }

            if ((firstNumber.startsWith("I") || firstNumber.startsWith("V") || firstNumber.startsWith("X")) &&
                    secondNumber.startsWith("I") || secondNumber.startsWith("V") || secondNumber.startsWith("X")) {
                line = calcArabic(firstNumber, secondNumber, operandLine);
            } else {
                line = calcRoman(firstNumber, secondNumber, operandLine);
            }

        } catch (Exception e){
            line = "throws Exception";
        }
        return line;
    }

    private static String calcArabic(String firstNumber, String secondNumber, String operandLine) {
        String line = null;
        try {
            int second = romanToArabic(secondNumber);
            int first = romanToArabic(firstNumber);
            char operation = operandLine.charAt(0);

            if ((first < 1) || (second <1)) {
                throw Exception;
            }

            if ((first > 10) || (second > 10)) {
                throw Exception;
            }

            int result = 0;
            switch (operation) {
                case '+':
                    result = first + second;
                    break;
                case '-':
                    result = first - second;
                    break;
                case '*':
                    result = first * second;
                    break;
                case '/':
                    result = first / second;
                    break;
            }

            if (result < 0) {
                throw Exception;
            }

            line = arabicToRoman(result);

        } catch (Exception e) {
            line = "throws Exception";
        }
        return line;
    }

    private static String calcRoman(String firstNumber, String secondNumber, String operandLine) {
        String line = null;
        try {
            int second = Integer.parseInt(secondNumber);
            int first = Integer.parseInt(firstNumber);
            char operation = operandLine.charAt(0);

            if ((first < 1) || (second <1)) {
                throw Exception;
            }

            if ((first > 10) || (second > 10)) {
                throw Exception;
            }

            int result = 0;
            switch (operation) {
                case '+':
                    result = first + second;
                    break;
                case '-':
                    result = first - second;
                    break;
                case '*':
                    result = first * second;
                    break;
                case '/':
                    result = first / second;
                    break;
            }
            line = Integer.toString(result);
        } catch (Exception e) {
            line = "throws Exception";
        }
        return line;
    }
}
