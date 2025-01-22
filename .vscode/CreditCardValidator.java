import java.util.Scanner;
public class CreditCardValidator {
// Method to check if the credit card number is valid
        public static boolean isValid(long number) {
        int sum = sumOfDoubleEvenPlace(number) + sumOfOddPlace(number);
        return (sum % 10 == 0) && (getSize(number) >= 13 && getSize(number) <= 16) && (prefixMatched(number, 4) || prefixMatched(number, 5) || prefixMatched(number, 37) || prefixMatched(number, 6));
    }
    // Get the sum of double even-place digits
    public static int sumOfDoubleEvenPlace(long number) {
        int sum = 0;
        String numStr = Long.toString(number);
        for (int i = numStr.length() - 2; i >= 0; i -= 2) {
            int digit = Character.getNumericValue(numStr.charAt(i));
            sum += getDigit(digit * 2);
        }
        return sum;
    }
    // Return this number if it is a single digit, otherwise return the sum of the two digits
    public static int getDigit(int number) {
        if (number < 10) {
            return number;
        }
        return number / 10 + number % 10;
    }
    // Return the sum of odd-place digits in number
    public static int sumOfOddPlace(long number) {
        int sum = 0;
        String numStr = Long.toString(number);
        for (int i = numStr.length() - 1; i >= 0; i -= 2) {
            sum += Character.getNumericValue(numStr.charAt(i));
        }
        return sum;
    }
    // Return true if the number d is a prefix for number
    public static boolean prefixMatched(long number, int d) {
        return getPrefix(number, getSize(d)) == d;
    }
    // Return the number of digits in d
    public static int getSize(long d) {
        return Long.toString(d).length();
    }
    // Return the first k number of digits from number
    public static long getPrefix(long number, int k) {
        String numStr = Long.toString(number);
        if (numStr.length() < k) {
            return number;
        }
        return Long.parseLong(numStr.substring(0, k));
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
    // Prompt the user to enter a credit card number
        System.out.printf("Enter a credit card number as a long integer: ");
        long number = input.nextLong();
        // Check if the credit card number is valid
        if (isValid(number)) {
            System.out.printf(number + " is valid");
        } else {
            System.out.printf(number + " is invalid");
        }
        input.close();
    }
}


