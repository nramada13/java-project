// Custom Exception Class

class BinaryFormatException extends Exception {

    public BinaryFormatException(String message) {
        super(message);

    }
}
// Main Class with the bin2Dec Method

public class BinaryFormatExcepton {
    public static void main(String[] args) {
        try {
            String binaryString = "10101";
            int decimalValue = bin2Dec(binaryString);
            System.out.println("The decimal value of " + binaryString + " is " + decimalValue);

            // Test with an invalid binary string
            String invalidBinaryString = "10201";
            @SuppressWarnings("unused")
            int invalidDecimalValue = bin2Dec(invalidBinaryString); // This should throw an exception

        } catch (BinaryFormatException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    public static int bin2Dec(String binaryString) throws BinaryFormatException {
        for (char c : binaryString.toCharArray()) {
            if (c != '0' && c != '1') {
                throw new BinaryFormatException("The string \"" + binaryString + "\" is not a valid binary string.");
            }
        }
        // Convert binary string to decimal
        int decimalValue = 0;
        for (int i = 0; i < binaryString.length(); i++) {
            decimalValue = decimalValue * 2 + (binaryString.charAt(i) - '0');
        }
        return decimalValue;
    }

}