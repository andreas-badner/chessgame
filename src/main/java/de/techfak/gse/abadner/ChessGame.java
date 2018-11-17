package de.techfak.gse.abadner;
import java.util.Scanner;

/**
 * ChessGame Hauptklasse - Zur Ausgabe des Spielfelds unter bedingten Eingaben.
 */
public class ChessGame {
    private static final int EXIT_INTEGER = 0;

    /**
     *
     * @param args Startparameter wird geprueft fuer die Ausgabe des Spielfelds
     */
    public static void main(final String... args) {
        System.out.println("Hello abadner!");
        if (args.length == 0) { //Kein Startparameter
            System.out.println("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w"); //Gibt Grundstellung in FEN aus
        }
        Scanner reader = new Scanner(System.in);
        String input =  reader.nextLine();
        if (input.equals("")) {
            System.out.println("Applikation wird beendet");
            reader.close();
            System.exit(EXIT_INTEGER);
        }
    }
}
