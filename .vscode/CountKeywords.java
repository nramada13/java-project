import java.io.*;
import java.util.*;

public class CountKeywords {
    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("Usage: java CountKeywords <source-file>");
            return;
        }

        String filename = args[0];
        File file = new File(filename);

        if (!file.exists()) {
            System.out.println("File " + filename + " does not exist");
            return;
        }

        System.out.println("The number of keywords in " + filename + " is " + countKeywords(file));
    }

    public static int countKeywords(File file) throws Exception {
        // Java keywords + `true`, `false`, and `null`
        String[] keywordArray = {
                "abstract", "assert", "boolean", "break", "byte", "case", "catch", "char", "class", "const",
                "continue", "default", "do", "double", "else", "enum", "extends", "final", "finally", "float",
                "for", "goto", "if", "implements", "import", "instanceof", "int", "interface", "long", "native",
                "new", "package", "private", "protected", "public", "return", "short", "static", "strictfp",
                "super", "switch", "synchronized", "this", "throw", "throws", "transient", "try", "void",
                "volatile", "while", "true", "false", "null"
        };

        Set<String> keywordSet = new HashSet<>(Arrays.asList(keywordArray));
        int count = 0;

        try (Scanner input = new Scanner(file)) {
            boolean inBlockComment = false;
            
            while (input.hasNextLine()) {
                String line = input.nextLine().trim();
                if (line.startsWith("/*")) inBlockComment = true; // Block comment start
                if (line.endsWith("*/")) {
                    inBlockComment = false; // Block comment end
                    continue;
                }
                if (inBlockComment || line.startsWith("//")) continue; // Ignore comments

                boolean inString = false;
                StringBuilder word = new StringBuilder();

                for (char ch : line.toCharArray()) {
                    if (ch == '"') inString = !inString; // Toggle string state

                    if (!inString && Character.isLetter(ch)) {
                        word.append(ch);
                    } else {
                        if (word.length() > 0) {
                            if (keywordSet.contains(word.toString())) count++; // Valid keyword
                            word.setLength(0); // Reset for next word
                        }
                    }
                }

                if (word.length() > 0 && keywordSet.contains(word.toString())) count++;
            }
        }

        return count;
    }
}


public class welcome {
    public static void main(String[]args){
        System.out.println("Welcome to Java!");
    }
}

PS C:\Users\noorr> cd C:\book\
PS C:\book> javac Welcome.java
PS C:\book> javac welcome.java
PS C:\book> java CountKeywords Welcome.java
The number of keywords in Welcome.java is 5


