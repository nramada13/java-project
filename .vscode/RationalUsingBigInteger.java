
import java.math.BigInteger;

public class RationalUsingBigInteger extends Number implements Comparable<RationalUsingBigInteger> {
    // Data fields for numerator and denominator
    private BigInteger numerator;
    private BigInteger denominator;

    // Default constructor
    public RationalUsingBigInteger() {
        this(BigInteger.ZERO, BigInteger.ONE);
    }

    // Constructor with specified numerator and denominator
    public RationalUsingBigInteger(BigInteger numerator, BigInteger denominator) {
        if (denominator.equals(BigInteger.ZERO)) {
            throw new ArithmeticException("Denominator cannot be zero.");
        }

        // Simplify fraction using GCD
        BigInteger gcd = numerator.gcd(denominator);
        this.numerator = numerator.divide(gcd);
        this.denominator = denominator.divide(gcd);

        // Ensure denominator is always positive
        if (this.denominator.compareTo(BigInteger.ZERO) < 0) {
            this.numerator = this.numerator.negate();
            this.denominator = this.denominator.negate();
        }
    }

    // Getter for numerator
    public BigInteger getNumerator() {
        return numerator;
    }

    // Getter for denominator
    public BigInteger getDenominator() {
        return denominator;
    }

    // Addition method
    public RationalUsingBigInteger add(RationalUsingBigInteger secondRational) {
        BigInteger num = (numerator.multiply(secondRational.getDenominator()))
                .add(denominator.multiply(secondRational.getNumerator()));
        BigInteger den = denominator.multiply(secondRational.getDenominator());
        return new RationalUsingBigInteger(num, den);
    }

    // Subtraction method
    public RationalUsingBigInteger subtract(RationalUsingBigInteger secondRational) {
        BigInteger num = (numerator.multiply(secondRational.getDenominator()))
                .subtract(denominator.multiply(secondRational.getNumerator()));
        BigInteger den = denominator.multiply(secondRational.getDenominator());
        return new RationalUsingBigInteger(num, den);
    }

    // Multiplication method
    public RationalUsingBigInteger multiply(RationalUsingBigInteger secondRational) {
        BigInteger num = numerator.multiply(secondRational.getNumerator());
        BigInteger den = denominator.multiply(secondRational.getDenominator());
        return new RationalUsingBigInteger(num, den);
    }

    // Division method
    public RationalUsingBigInteger divide(RationalUsingBigInteger secondRational) {
        BigInteger num = numerator.multiply(secondRational.getDenominator());
        BigInteger den = denominator.multiply(secondRational.getNumerator());
        return new RationalUsingBigInteger(num, den);
    }

    // Convert to double
    public double doubleValue() {
        return numerator.doubleValue() / denominator.doubleValue();
    }

    // Override toString method
    @Override
    public String toString() {
        if (denominator.equals(BigInteger.ONE)) {
            return numerator.toString();
        } else {
            return numerator + "/" + denominator;
        }
    }

    // Implement compareTo method for sorting
    @Override
    public int compareTo(RationalUsingBigInteger other) {
        return this.subtract(other).getNumerator().compareTo(BigInteger.ZERO);
    }

    // Override abstract methods from Number
    @Override
    public int intValue() {
        return (int) doubleValue();
    }

    @Override
    public long longValue() {
        return (long) doubleValue();
    }

    @Override
    public float floatValue() {
        return (float) doubleValue();
    }
}