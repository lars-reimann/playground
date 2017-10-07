import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class JavaCodeStyler {

    private static final String[] KEYWORDS = {"abstract", "assert", "boolean",
                    "break", "byte", "case", "catch", "char", "class", "const",
                    "continue", "default", "do", "double", "else", "enum",
                    "extends", "final", "finally", "float", "for", "goto",
                    "if", "implements", "import", "int", "instanceof",
                    "interface", "long", "native", "new", "package", "private",
                    "protected", "public", "return", "short", "static",
                    "strictfp", "super", "switch", "synchronized", "this",
                    "throw", "throws", "transient", "try", "void", "volatile",
                    "while", "false", "null", "true"};

    public static void main(String[] args) {
        int indentation = 0;

        File file = new File(getInput());
        try {
            Scanner fileReader = new Scanner(new FileReader(file));
            System.out.print("<html><body><pre><code>");
            while (fileReader.hasNext()) {
                String line = fileReader.nextLine();
                Scanner lineReader = new Scanner(line);
                Scanner indentationReader = new Scanner(line);
                if (indentationReader.hasNext()) {
                    if ("}".equals(indentationReader.next())) {
                        indentation--;
                    }
                }
                for (int i = 0; i < indentation; i++) {
                    System.out.print("    ");
                }
                while (lineReader.hasNext()) {
                    String word = lineReader.next();
                    if (isKeyword(word)) {
                        System.out.print("<strong><font color=\"blue\">" +
                                         word + "</font></strong> ");
                    } else {
                        System.out.print(word + " ");
                        if ("{".equals(word)) {
                            indentation++;
                        }
                    }
                }
                System.out.println();
            }
            System.out.print("</code></pre></body></html>");
        } catch (FileNotFoundException exception) {
            System.out.println("Datei wurde nicht gefunden");
        }
    }

    private static String getInput() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bitte Ziel eingeben");
        return scanner.nextLine();
    }

    private static boolean isKeyword(final String word) {
        for (String keyword : KEYWORDS) {
            if (keyword.equals(word)) {
                return true;
            }

        }
        return false;
    }
}
