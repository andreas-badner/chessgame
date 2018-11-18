package de.techfak.gse.abadner;

import java.util.Scanner;

/**
 * ChessGame Hauptklasse - Zur Ausgabe des Spielfelds unter bedingten Eingaben.
 */
public class ChessGame {
    static final int COLUMN_ROW_COUNT = 8;

    static final int FOR_LOOP_ZERO = 0;

    static final int LAST_ARRAY_BOARD = 7;

    private static int errorcode = 0;

    private static final int INVALID_INPUT_ERROR = 100;

    private static final int INVALID_TURN_ERROR = 101;

    private static boolean userinput = true;

    private static final String VALID_INPUT_CHARS = "prnbqkPRNBQK";

    private static final String VALID_INPUT_NUMBERS = "1234567";

    private static final String VALID_TURN_CHARS = "abcdefgh";

    private static final String VALID_TURN_NUMBERS = "12345678";

    /**
     * @param args nimmt Startparameter und gibt es weiter zur Ueberpruefung
     */
    public static void main(final String... args) {
        System.out.println("Hello abadner!");
        ChessBoard chessboard = new ChessBoard();
        try {
            checkStartInput(args);
            while (userinput) {
                userTurn(args);
            }
        } catch (ChessException e) {
            errorcode = INVALID_INPUT_ERROR;
        }
        System.exit(errorcode);
    }

    /**
     * @param schachstellung aktuelle Schachstellung zur Auswertung des Zuges
     * @return aktualisierte Schachstellung
     */
    private static String[] userTurn(String[] schachstellung) throws ChessException {
        final Scanner reader = new Scanner(System.in);
        final String turns = reader.nextLine();
        if (turns.equals("")) { //Leere Eingabe zum beenden der Applikation
            System.out.println("Programm wird beendet");
            reader.close();
            userinput = false;
        }
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
        }
    }

    /**
     * @param boardinput gegebene Schachstellung vom Nutzer, die ueberprueft werden muss
     * @return true wenn die Schachstellung nach der Notation zulaessig ist
     * KOMPLEXITAET VERBESSERN
     */
    private static boolean validString(String boardinput, String starterplayer) throws ChessException {
        final String[] zeilen = boardinput.split("/", COLUMN_ROW_COUNT);
        for (int i = FOR_LOOP_ZERO; i < COLUMN_ROW_COUNT; i++) {
            int rowsum = 0;
            char currentchar;
            for (int j = FOR_LOOP_ZERO; j < zeilen[i].length(); j++) {
                currentchar = zeilen[i].charAt(j);
                if (VALID_INPUT_CHARS.indexOf(currentchar) >= 0) {
                    rowsum++;
                    ChessBoard.insertChessPiece(i, j, currentchar);
                } else if (VALID_INPUT_NUMBERS.indexOf(currentchar) >= 0) {
                    rowsum += Character.getNumericValue(currentchar);
                    for (int k = FOR_LOOP_ZERO; k < Character.getNumericValue(currentchar); k++) {
                        ChessBoard.insertChessPiece(i, k, '0');
                    }
                } else {
                    errorcode = INVALID_INPUT_ERROR;
                    throw new ChessException();
                }
            }
            if (rowsum != COLUMN_ROW_COUNT) {
                errorcode = INVALID_INPUT_ERROR;
                throw new ChessException();
            }
        }
        if (starterplayer.length() == 1 && (starterplayer.equals("w") || starterplayer.equals("b"))) {
            return true;
        } else {
            errorcode = INVALID_INPUT_ERROR;
            throw new ChessException();
        }
    }
}
