import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class GroupingSymbolChecker {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java GroupingSymbolChecker <source-file>");
            return;
        }

        String filename = args[0];

        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            Stack<Character> stack = new Stack<>();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                for (char ch : line.toCharArray()) {
                    if (ch == '(' || ch == '{' || ch == '[') {
                        stack.push(ch);
                    } else if (ch == ')' || ch == '}' || ch == ']') {
                        if (stack.isEmpty()) {
                            System.out.println("Unmatched closing symbol: " + ch);
                            scanner.close();
                            return;
                        }
                        char lastOpened = stack.pop();
                        if (!isMatchingPair(lastOpened, ch)) {
                            System.out.println("Mismatched symbols: " + lastOpened + " and " + ch);
                            scanner.close();
                            return;
                        }
                    }
                }
            }
            scanner.close();

            if (stack.isEmpty()) {
                System.out.println("Correct grouping pairs");
            } else {
                System.out.println("Unmatched opening symbols remain in the stack");
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        }
    }

    private static boolean isMatchingPair(char open, char close) {
        return (open == '(' && close == ')') ||
               (open == '{' && close == '}') ||
               (open == '[' && close == ']');
    }
}
