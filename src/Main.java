import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {
        String rootPath = Paths.get("").toAbsolutePath(). toString();
        String subPath = "/src/";

        String input = "150.5 + 5 / 50 - 10 * 1000 %";

        String file = rootPath + subPath;
        File f = new File(file + "Lexer.java");
        if (!f.exists()) {
            System.out.println("Path: " + file);
            File sourceCode = new File(file + "language.lex");

            jflex.Main.generate(sourceCode);
            return;
        } else {
            Lexer lexer = new Lexer(new StringReader(input));
            Token token;
            while (true) {
                try {
                    token = lexer.yylex();
                } catch (IOException exc) {
                    System.out.println("Lexic failed");
                    exc.printStackTrace();
                    return;
                }

                if (token == null) {
                    System.out.println("Finished reading");
                    return;
                }

                switch (token) {
                    case ERROR:
                        System.out.println("ERROR - " + "'" + lexer.lexeme + "'");
                        break;
                    case INTEGER:
                        System.out.println("INTEGER - " + "'" + lexer.lexeme + "'");
                        break;
                    case FLOAT:
                        System.out.println("FLOAT - " + "'" + lexer.lexeme + "'");
                        break;
                    case OPERATOR:
                        System.out.println("OPERATOR - " + "'" + lexer.lexeme + "'");
                }
            }
        }
    }
}