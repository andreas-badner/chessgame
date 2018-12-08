package model;

import java.util.Observable;
import java.util.Scanner;

/**
 * ChessGame Hauptklasse - Zur Ausgabe des Spielfelds unter bedingten Eingaben.
 */
public final class ChessTurn extends Observable {

    static final int COLUMN_ROW_COUNT = 8;

    static final int FOR_LOOP_ZERO = 0;

    private static int errorcode = 0;

    private static boolean whiteturn; //true wenn weiss dran ist

    private static final String VALID_INPUT_CHARS = "prnbqkPRNBQK";

    private static final String VALID_INPUT_NUMBERS = "12345678";

    private static final String VALID_TURN_CHARS = "abcdefgh";

    private static final String VALID_TURN_NUMBERS = "87654321";

    private static final int FOURTH_CHAR_INDEX = 3;

    private static final int FIFTH_CHAR_INDEX = 4;

    private static final int LENGTH_OF_TURN_INPUT = 5;

    private static final int INVALID_INPUT_ERROR = 100;

    private static final int INVALID_TURN_ERROR = 101;

    private static final int INVALID_NO_PIECES = 102;

    private static final int INVALID_TURN_ORDER = 103;

    private static final int INVALID_NO_MOVE = 104;

    private static final String NO_PIECE_ON_FIELD = "1";

    private static final String WHITE_TURN_STRING = "w";

    private static final String BLACK_TURN_STRING = "b";

    private static ChessBoard chessboard;

    private static boolean userinput = true;

    /**
     * Konstruktor der Klasse - ruft alle benoetigten Methoden auf.
     */
    public ChessTurn() {
        chessboard = new ChessBoard();
    }

    /**
     * Ablauf der Zuege und Ueberpruefung des Startinputs.
     *
     * @param input Startinput fuer Startaufstellung
     */
    public void run(final String[] input) {
        try {
            checkStartInput(input, chessboard);
            while (userinput) {
                userTurn(chessboard);
            }
        } catch (ChessException e) {
            System.exit(errorcode);
        }
    }

    public void runstandard() {
        try {
            insertstandard();
            while (userinput) {
                userTurn(chessboard);
            }
        } catch (ChessException e) {
            System.exit(errorcode);
        }
    }

    /**
     * Beendet bei leerer Eingabe das Programm.
     */
    private void exitApplication() {
        userinput = false;
        setChanged();
        notifyObservers();
    }

    public boolean getuserinput() {
        return this.userinput;
    }

    /**
     * Passt den Fehlercode an.
     */
    private static void changeExitCode(final int code) {
        errorcode = code;
    }

    /**
     * Methode fuehrt Zug durch, wenn Zug valide ist.
     *
     * @param chessboard bekommt das erzeugte Schachbrett
     */
    private void userTurn(final ChessBoard chessboard) throws ChessException {
        try (Scanner reader = new Scanner(System.in)) {
            final String turns = reader.nextLine();
            if (turns.length() == 0) { //Leere Eingabe zum beenden der Applikation
                System.out.println("Programm wird beendet.");
                reader.close();
                exitApplication();
            } else {
                final String[] turnsplit = turns.split(";", 0);
                for (final String currentturn : turnsplit) {
                    if (currentturn.length() == LENGTH_OF_TURN_INPUT) {
                        final char char0 = currentturn.charAt(0);
                        final char char1 = currentturn.charAt(1);
                        final char char2 = currentturn.charAt(2);
                        final char char3 = currentturn.charAt(FOURTH_CHAR_INDEX);
                        final char char4 = currentturn.charAt(FIFTH_CHAR_INDEX);
                        if (VALID_TURN_CHARS.indexOf(char0) >= 0 && VALID_TURN_NUMBERS.indexOf(char1) >= 0
                            && char2 == '-'
                            && VALID_TURN_CHARS.indexOf(char3) >= 0 && VALID_TURN_NUMBERS.indexOf(char4) >= 0) {
                            final int startrow = VALID_TURN_NUMBERS.indexOf(char1);
                            final int startcolumn = VALID_TURN_CHARS.indexOf(char0);
                            final int endrow = VALID_TURN_NUMBERS.indexOf(char4);
                            final int endcolumm = VALID_TURN_CHARS.indexOf(char3);
                            if (chessboard.hasChessPiece(startrow, startcolumn)) {
                                final String chesspiece =
                                    ChessPiece.getBez(chessboard.getChessPiece(startrow, startcolumn));
                                final boolean whitepiece = chesspiece.equals(chesspiece.toUpperCase());
                                if (whiteturn && whitepiece || !whiteturn && !whitepiece) {
                                    if (startrow != endrow || startcolumn != endcolumm) {
                                        chessboard.insertChessPiece(startrow, startcolumn, NO_PIECE_ON_FIELD);
                                        chessboard.insertChessPiece(endrow, endcolumm, chesspiece);
                                        whiteturn = !whiteturn;
                                        setChanged();
                                        notifyObservers();
                                    } else {
                                        System.out.println(chessboard.createCurrentChessBoard());
                                        changeExitCode(INVALID_NO_MOVE); //Start und Ziel sind identisch.
                                        throw new ChessException();
                                    }
                                } else {
                                    System.out.println(chessboard.createCurrentChessBoard()); //ungueltige Spielreihenfolge.
                                    changeExitCode(INVALID_TURN_ORDER);
                                    throw new ChessException();
                                }
                            } else {
                                System.out.println(chessboard.createCurrentChessBoard()); //keine Figur auf Startfeld.
                                changeExitCode(INVALID_NO_PIECES);
                                throw new ChessException();
                            }
                        } else {
                            System.out.println(chessboard.createCurrentChessBoard()); //Zeichen im Zug ungueltig.
                            changeExitCode(INVALID_TURN_ERROR);
                            throw new ChessException();
                        }
                    } else {
                        System.out.println(chessboard.createCurrentChessBoard()); //Zug ist nicht 5 Zeichen.
                        changeExitCode(INVALID_TURN_ERROR);
                        throw new ChessException();
                    }
                }
                System.out.println(chessboard.createCurrentChessBoard());
            }
        }
    }


    /**
     * @param input Startparameter wird geprueft fuer die Ausgabe des Spielfelds - muss sFEN sein.
     */
    private void checkStartInput(final String[] input, final ChessBoard chessboard) throws ChessException {
        if (input.length == 0) { //Kein Startparameter => Grundstellung
            insertstandard();
        } else if (input.length == 1) { //Startparameter => Wird ueberprueft und ggf. ubernommen
            final String customstellung = input[0].substring(0, input[0].length() - 2);
            final String startercustom = input[0].substring(input[0].length() - 1);
            if (validString(customstellung, startercustom, chessboard)) {
                System.out.println(input[0]);
                setChanged();
                notifyObservers();
            } else {
                changeExitCode(INVALID_INPUT_ERROR); //String ist nicht sFEN - ungueltig.
                throw new ChessException();
            }
        } else {
            changeExitCode(INVALID_INPUT_ERROR); //Startparameter ist laenger als 1 und somit ungueltig.
            throw new ChessException();
        }
    }

    private void insertstandard() throws ChessException {
        final String grundstellung = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w";
        final String stellung = grundstellung.substring(0, grundstellung.length() - 2);
        final String startergrund = grundstellung.substring(grundstellung.length() - 1);
        if (validString(stellung, startergrund, chessboard)) {
            System.out.println(grundstellung);
            setChanged();
            notifyObservers();
        }
    }

    /**
     * @param input gegebene Schachstellung vom Nutzer, die ueberprueft werden muss
     * @return true wenn die Schachstellung nach der Notation zulaessig ist
     */
    private static boolean validString(final String input, final String starter,
                                       final ChessBoard chessboard) throws ChessException {
        final String[] zeilen = input.split("/", COLUMN_ROW_COUNT);
        if (zeilen.length != COLUMN_ROW_COUNT) {
            changeExitCode(INVALID_INPUT_ERROR); //acht Reihen sind nicht belegt.
            throw new ChessException();
        }
        char currentchar;
        for (int i = FOR_LOOP_ZERO; i < COLUMN_ROW_COUNT; i++) {
            int rowsum = 0;
            int fillone;
            for (int j = FOR_LOOP_ZERO; j < zeilen[i].length(); j++) {
                currentchar = zeilen[i].charAt(j);
                if (VALID_INPUT_CHARS.indexOf(currentchar) >= 0 && rowsum < COLUMN_ROW_COUNT) {
                    rowsum++;
                    chessboard.insertChessPiece(i, j, Character.toString(currentchar));
                } else if (VALID_INPUT_NUMBERS.indexOf(currentchar) >= 0 && rowsum < COLUMN_ROW_COUNT) {
                    rowsum += Character.getNumericValue(currentchar);
                    fillone = Character.getNumericValue(currentchar);
                    for (int k = j; k < fillone; k++) {
                        chessboard.insertChessPiece(i, k, NO_PIECE_ON_FIELD);
                    }
                } else {
                    changeExitCode(INVALID_INPUT_ERROR); //char ist nicht fuer sFEN gueltig.
                    throw new ChessException();
                }
            }
            if (rowsum != COLUMN_ROW_COUNT) {
                changeExitCode(INVALID_INPUT_ERROR); //weniger/mehr als acht Felder belegt.
                throw new ChessException();
            }
        }
        if (starter.length() == 1) {
            switch (starter) {
                case WHITE_TURN_STRING:
                    whiteturn = true;
                    return true;
                case BLACK_TURN_STRING:
                    whiteturn = false;
                    return true;
                default:
                    changeExitCode(INVALID_INPUT_ERROR); //Startspieler-String ist weder b noch w
                    throw new ChessException();
            }
        } else {
            changeExitCode(INVALID_INPUT_ERROR); //Startspieler-String ist falsch
            throw new ChessException();
        }
    }

    /**
     * @return gibt zurueck, wer gerade dran ist.
     */
    /* default */
    static String getTurnOrder() {
        if (whiteturn) {
            return WHITE_TURN_STRING;
        } else {
            return BLACK_TURN_STRING;
        }
    }

    static String getValidInputChars() {
        return VALID_INPUT_CHARS;
    }
}
