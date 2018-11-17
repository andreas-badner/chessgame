package de.techfak.gse.abadner;

import java.util.Scanner;

/**
 * ChessGame Hauptklasse - Zur Ausgabe des Spielfelds unter bedingten Eingaben.
 */
public class ChessGame {
    private static final int COLUMN_COUNT = 8;

    private static final int ROW_COUNT = 8;

    private static final int FOR_LOOP_ZERO = 0;

    private static final int SECOND_COLUMN = 2;

    private static final int THIRD_COLUMN = 3;

    private static final int FOURTH_COLUMN = 4;

    private static final int FIFTH_COLUMN = 5;

    private static final int SIXTH_COLUMN = 6;

    private static final int SEVENTH_COLUMN = 7;

    private static final int EIGHTH_COLUMN = 8;

    private static final int INVALID_INPUT_ERROR = 100;

    /**
     * @param args nimmt Startparameter und gibt es weiter zur Ueberpruefung
     */
    public static void main(final String... args) {
        System.out.println("Hello abadner!");
        int errorCode = 0;
        try {
            checkStartInput(args);
        } catch (ChessException chessexception) {
            errorCode = INVALID_INPUT_ERROR;
        }
        System.exit(errorCode);
    }

    /**
     * @param input Startparameter wird geprueft fuer die Ausgabe des Spielfelds
     */
    private static void checkStartInput(String[] input) throws ChessException {
        if (input.length == 0) { //Kein Startparameter => Grundstellung
            final String grundstellung = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w";
            System.out.println(grundstellung); //Gibt Grundstellung in FEN aus
        } else if (input.length == 2) { //Startparameter => Wird ueberprueft und ggf. ubernommen
            if (validString(input[0], input[1])) {
                System.out.println(input[0]);
            }
        } else { //unzulaessiger Startparameter {
            System.out.println("Error Code 100");

            final Scanner reader = new Scanner(System.in);
            final String userinput = reader.nextLine();
            if (userinput.equals("")) { //Leere Eingabe zum beenden der Applikation
                System.out.println("Applikation wird beendet");
                reader.close();
            }
        }
    }

    /**
     * @param boardinput gegebene Schachstellung vom Nutzer, die ueberprueft werden muss
     * @return true wenn die Schachstellung nach der Notation zulaessig ist
     * KOMPLEXITAET VERBESSERN
     */
    private static boolean validString(String boardinput, String starterplayer) throws ChessException {
        final String[] zeilen = boardinput.split("/", COLUMN_COUNT);
        for (int i = FOR_LOOP_ZERO; i < ROW_COUNT; i++) {
            int rowsum = 0;
            for (int j = FOR_LOOP_ZERO; j < zeilen[i].length(); j++) {
                switch (zeilen[i].charAt(j)) {
                    case 'r':
                    case 'n':
                    case 'b':
                    case 'q':
                    case 'k':
                    case 'p':
                    case '1':
                        rowsum++;
                        break;
                    case 'R':
                    case 'N':
                    case 'B':
                    case 'Q':
                    case 'K':
                    case 'P':
                        rowsum++;
                        break;
                    case '2':
                        rowsum += SECOND_COLUMN;
                        break;
                    case '3':
                        rowsum += THIRD_COLUMN;
                        break;
                    case '4':
                        rowsum += FOURTH_COLUMN;
                        break;
                    case '5':
                        rowsum += FIFTH_COLUMN;
                        break;
                    case '6':
                        rowsum += SIXTH_COLUMN;
                        break;
                    case '7':
                        rowsum += SEVENTH_COLUMN;
                        break;
                    case '8':
                        rowsum += EIGHTH_COLUMN;
                        break;
                    default:
                        throw new ChessException();
                }
            }
            if (rowsum != COLUMN_COUNT) {
                throw new ChessException();
            }
        }
        if (starterplayer.length() == 1 && (starterplayer.equals("w") || starterplayer.equals("b"))) {
            return true;
        } else {
            throw new ChessException();
        }
    }
}
