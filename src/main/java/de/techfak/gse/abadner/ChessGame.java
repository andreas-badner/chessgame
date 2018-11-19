package de.techfak.gse.abadner;

/**
 * ChessGame Hauptklasse - Zur Ausgabe des Spielfelds unter bedingten Eingaben.
 */
public final class ChessGame {
    private static boolean userinput = true;

    private static int errorcode = 0;

    /**
     * Konstruktor der Klasse - ruft alle benoetigten Methoden auf.
     * @param input uebergebener Startparameter
     */
    private ChessGame(final String[] input) {
        final ChessBoard chessboard = new ChessBoard();
        try {
            ChessTurn.checkStartInput(input, chessboard);
            while (userinput) {
                ChessTurn.userTurn(chessboard);
            }
        } catch (ChessException e) {
            System.exit(errorcode);
        }
    }

    /**
     * Beendet bei leerer Eingabe das Programm.
     */
    /* default */static void exitApplication() {
        userinput = false;
    }

    /**
     * Passt den Fehlercode an.
     */
    /* default */static void changeExitCode(final int code) {
        errorcode = code;
    }

    /**
     * @param args nimmt Startparameter und gibt es weiter zur Ueberpruefung
     */
    public static void main(final String... args) {
        new ChessGame(args);
    }
}
