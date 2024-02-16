
import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите выражение: " + "для выхода введите exit: ");

        while (true) {
            String input = scanner.nextLine();
            System.out.println(calc(input));
            if (input.equalsIgnoreCase("exit"))
                break;


        }

    }

    public static String calc(String input) {


        String[] numbers = input.split(" ");
        try {
            if (numbers.length != 3) {
                System.out.println("Не выерный формат!");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new RuntimeException(e);
        }
        String firstNumber = numbers[0];
        String operator = numbers[1];
        String secondNumber = numbers[2];


        int num1;
        int num2;

        try {
            if (isArabic(firstNumber) & isArabic(secondNumber)) {
                num1 = toArabic(firstNumber);
                num2 = toArabic(secondNumber);
            } else {
                num1 = toRoman(firstNumber);
                num2 = toRoman(secondNumber);
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }


        if ((num1 < 1 || num1 > 10) || (num2 < 1 || num2 > 10)) {
            throw new IllegalArgumentException("Числа должны быть не меньше 1  и не больше 10");

        }


        String result;
        int value = switch (operator) {
            case "+" -> num1 + num2;
            case "-" -> num1 - num2;
            case "*" -> num1 * num2;
            case "/" -> num1 / num2;
            default -> throw new IllegalArgumentException("Неверная операция");
        };

        if (isRoman(firstNumber) & isRoman(secondNumber)) {
            if (value > 0) {
                result = convert(value);
                return result;

            } else {
                throw new IllegalArgumentException("Римские числа не могу быть меньше 0");
            }
        } else if (isArabic(firstNumber) & isArabic(secondNumber)) {

            result = String.valueOf(value);

            return result;
        } else if (!isRoman(firstNumber) & isArabic(secondNumber) || !isArabic(firstNumber) & isArabic(secondNumber)) {
            System.out.println("Числа должны быть одинаковыми");
        }
        return "";


    }


    static boolean isRoman(String s) {
        List<String> list = List.of("I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X");
        try {
            for (String string : list) {
                if (string.equals(s)) {
                    return true;

                }

            }

        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    static boolean isArabic(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException exception) {
            return false;
        }
    }

    static int toRoman(String s) {


        if (isRoman(s)) {
            HashMap<Character, Integer> romanMap = new HashMap<>();
            romanMap.put('I', 1);
            romanMap.put('V', 5);
            romanMap.put('X', 10);

            int result = 0;
            int prevVelue = 0;
            for (int i = s.length() - 1; i >= 0; i--) {
                int curValue = romanMap.get(s.charAt(i));
                if (curValue < prevVelue) {
                    result -= curValue;
                } else {
                    result += curValue;
                }
                prevVelue = curValue;
            }
            return result;

        } else {

            throw new IllegalArgumentException("Не верный формат римских чисел");

        }


    }


    static int toArabic(String s) {
        if (isArabic(s)) {
            return Integer.parseInt(s);
        } else {
            throw new IllegalArgumentException("Не верный формат арабских чисел");
        }
    }

    static String convert(int value) {
        TreeMap<Integer, String> intToArab = new TreeMap<>();
        intToArab.put(100, "C");
        intToArab.put(90, "XC");
        intToArab.put(50, "L");
        intToArab.put(40, "XL");
        intToArab.put(10, "X");
        intToArab.put(9, "IX");
        intToArab.put(5, "V");
        intToArab.put(4, "IV");
        intToArab.put(1, "I");


        String r = "";
        for (int ArabKey : intToArab.descendingKeySet()) {
            while (value >= ArabKey) {
                r  +=(intToArab.get(ArabKey));
                value -= ArabKey;
            }

        }
        return r.toString();


    }


}
