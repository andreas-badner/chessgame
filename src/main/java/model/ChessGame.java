package model;


import java.util.Observable;

/**
 * ChessGame Hauptklasse - Zur Ausgabe des Spielfelds unter bedingten Eingaben.
 */
public final class ChessGame extends Observable {
    private static boolean userinput = true;

    private static int errorcode = 0;

    public final ChessBoard chessBoard;

    public final ChessTurn chessTurn;


    /**
     * Konstruktor der Klasse - ruft alle benoetigten Methoden auf.
     *
     * @param args uebergebener Startparameter
     */
    ChessGame(final String[] args) {
        chessBoard = new ChessBoard();
        chessTurn = new ChessTurn();
        if (args.length == 1 && args[0].equals("standardgui")) {
            try {
                chessTurn.insertstandard(chessBoard);
            } catch (ChessException e) {
                System.exit(errorcode);
            }
        } else {
            try {
                chessTurn.checkStartInput(args, chessBoard);
                while (userinput) {
                    chessTurn.userTurn(chessBoard);
                }
            } catch (ChessException e) {
                System.exit(errorcode);
            }
        }
    }

    public ChessBoard getChessBoard() {
        return this.chessBoard;
    }

    /**
     * Beendet bei leerer Eingabe das Programm.
     */
    /* default */
    static void exitApplication() {
        userinput = false;
    }

    /**
     * Passt den Fehlercode an.
     */
    /* default */
    static void changeExitCode(final int code) {
        errorcode = code;
    }

    /**
     * @param args nimmt Startparameter und gibt es weiter zur Ueberpruefung
     */
    public static void main(final String... args) {
        if (args.length == 1 && args[0].equals("--gui")) {
            ChessGUI.main(args);
        } else {
            new ChessGame(args);
        }
    }
}
