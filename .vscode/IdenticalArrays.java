import java.util.Scanner;
public class IdenticalArrays {
    // Method to check if two 2D arrays are identical
    public static boolean equals(int[][] m1, int[][] m2) {
        // Check dimensions first
        if (m1.length != m2.length || m1[0].length != m2[0].length) {
            return false;
        }
        // Compare each element
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m1[i].length; j++) {
                if (m1[i][j] != m2[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Create two 3x3 
        int[][] m1 = new int[3][3];
        int[][] m2 = new int[3][3];

        // Input for matrix m1
        System.out.printf("Enter m1 (a 3 by 3 matrix) row by row:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                m1[i][j] = input.nextInt();
            }
        }

        // Input for matrix m2
        System.out.printf("Enter m2 (a 3 by 3 matrix) row by row:");
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                m2[i][j] = input.nextInt();
            }
        }
        // Check if they are identical
        if (equals(m1, m2)) {
            System.out.printf("The two arrays are identical.");
        } else {
            System.out.printf("The two arrays are not identical.");
        }
        input.close();
    }
}