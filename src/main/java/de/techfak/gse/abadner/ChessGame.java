package de.techfak.gse.abadner;

/**
 * ChessGame Hauptklasse - Zur Ausgabe des Spielfelds unter bedingten Eingaben.
 */
public final class ChessGame {
    private static boolean userinput = true;

    private static int errorcode = 0;


    private ChessGame(String[] input) {
        System.out.println("Hello abadner!");
        final ChessBoard chessboard = new ChessBoard();
        final ChessTurn chessturn = new ChessTurn();
        try {
            chessturn.checkStartInput(input, chessboard);
            while (userinput) {
                chessturn.userTurn(chessboard);
                System.out.println(chessboard.createCurrentChessBoard());
            }
        } catch (ChessException e) {
            System.exit(errorcode);
        }
    }

    /**
     * Beendet bei leerer Eingabe das Programm.
     */
    static void exitApplication() {
        //aendert userinput zu false.
        userinput = false;
    }

    /**
     * Passt den Fehlercode an.
     */
    static void changeExitCode(int code) {
        errorcode = code;
    }

    /**
     * @param args nimmt Startparameter und gibt es weiter zur Ueberpruefung
     */
    public static void main(final String... args) {
        new ChessGame(args);
    }
}
