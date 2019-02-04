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

    /**
     * Method to take a turn when playing with gui.
     * @param startrow on chessfield.
     * @param startcolumn on chessfield.
     * @param endrow on chessfield.
     * @param endcolumn on chessfield.
     * @param chessBoard object for saved array.
     */
    public void guiturn(int startrow, int startcolumn, int endrow, int endcolumn, ChessBoard chessBoard) {
        chessTurn.guiturn(startrow, startcolumn, endrow, endcolumn, chessBoard);
        setChanged();
        notifyObservers();
    }

    /**
     * Updates game/array, i.e. when a .fen-file is loaded.
     * @param input has the new linup.
     */
    public void changeGame(String[] input) {
        try {
            chessTurn.checkStartInput(input, chessBoard);
        } catch (ChessException e) {
            System.exit(errorcode);
        }
        setChanged();
        notifyObservers();
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
