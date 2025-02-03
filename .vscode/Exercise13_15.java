
import java.math.BigInteger;
import java.util.Scanner;

public class Exercise13_15 {
    public static void main(String[] args) {
        // Prompt the user to enter two Rational numbers
        @SuppressWarnings("resource")
        Scanner input = new Scanner(System.in);

        System.out.print("Enter rational r1 with numerator and denominator separated by a space: ");
        BigInteger n1 = new BigInteger(input.next());
        BigInteger d1 = new BigInteger(input.next());

        System.out.print("Enter rational r2 with numerator and denominator separated by a space: ");
        BigInteger n2 = new BigInteger(input.next());
        BigInteger d2 = new BigInteger(input.next());

        // Create two RationalUsingBigInteger objects
        RationalUsingBigInteger r1 = new RationalUsingBigInteger(n1, d1);
        RationalUsingBigInteger r2 = new RationalUsingBigInteger(n2, d2);

        // Display results
        System.out.println(r1 + " + " + r2 + " = " + r1.add(r2));
        System.out.println(r1 + " - " + r2 + " = " + r1.subtract(r2));
        System.out.println(r1 + " * " + r2 + " = " + r1.multiply(r2));
        System.out.println(r1 + " / " + r2 + " = " + r1.divide(r2));
        System.out.println(r2 + " is " + r2.doubleValue());
    }
}
