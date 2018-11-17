package de.techfak.gse.abadner;
import java.util.Scanner;

/**
 * Mainclass for the ChessGame
 */
public class ChessGame {

    private static final int EXIT_INTEGER = 100;

    /**
     *
     * @param args Startparameter zur Ausgabe des Schachfelds
     */
    public static void main(final String... args) {
        System.out.println("Hello abadner!");
        if (args.length == 0) {
            System.out.println("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w");
        }
        Scanner reader = new Scanner(System.in);
        String eingabe = reader.next();
        if (eingabe.equals(" ")) {
            System.exit(EXIT_INTEGER);
        }
    }

}
