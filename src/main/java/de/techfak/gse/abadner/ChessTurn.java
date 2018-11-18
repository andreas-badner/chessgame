package de.techfak.gse.abadner;

import java.util.Scanner;

/**
 * Klasse fuer die Verarbeitung und Ueberpruefung der Eingaben.
 */
class ChessTurn {

    static final int COLUMN_ROW_COUNT = 8;

    static final int FOR_LOOP_ZERO = 0;

    private static boolean whiteturn;

    private static final String VALID_INPUT_CHARS = "prnbqkPRNBQK";

    private static final String VALID_INPUT_NUMBERS = "12345678";

    private static final String VALID_TURN_CHARS = "abcdefgh";

    private static final String VALID_TURN_NUMBERS = "87654321";

    private static final int FOURTH_CHAR = 3;

    private static final int FIFTH_CHAR = 4;

    private static final int LENGTH_OF_TURN_INPUT = 5;

    private static final int INVALID_INPUT_ERROR = 100;

    private static final int INVALID_TURN_ERROR = 101;

    private static final int INVALID_NO_PIECES = 102;

    private static final int INVALID_TURN_ORDER = 103;

    private static final int INVALID_NO_MOVE = 104;

    ChessTurn() {
    }

    /**
     * Methode fuehrt Zug durch, wenn Zug valide ist.
     */
    void userTurn(ChessBoard chessboard) throws ChessException {
        final Scanner reader = new Scanner(System.in);
        final String turns = reader.nextLine();
        if (turns.equals("")) { //Leere Eingabe zum beenden der Applikation
            System.out.println("Programm wird beendet");
            reader.close();
            ChessGame.exitApplication();
        } else {
            if (turns.charAt(turns.length() - 1) != ';') {
                ChessGame.changeExitCode(INVALID_TURN_ERROR);
                throw new ChessException();
            }
            final String[] turnsplit = turns.split(";", 0);
            for (String currentturn : turnsplit) {
                char char0 = currentturn.charAt(0);
                char char1 = currentturn.charAt(1);
                char char2 = currentturn.charAt(2);
                char char3 = currentturn.charAt(FOURTH_CHAR);
                char char4 = currentturn.charAt(FIFTH_CHAR);
                boolean validchar0 = VALID_TURN_CHARS.indexOf(char0) >= 0;
                boolean validchar1 = VALID_TURN_NUMBERS.indexOf(char1) >= 0;
                boolean validchar2 = char2 == '-';
                boolean validchar3 = VALID_TURN_CHARS.indexOf(char3) >= 0;
                boolean validchar4 = VALID_TURN_NUMBERS.indexOf(char4) >= 0;
                if (currentturn.length() == LENGTH_OF_TURN_INPUT) {
                    if (validchar0 && validchar1 && validchar2 && validchar3 && validchar4) {
                        int startrow = VALID_TURN_NUMBERS.indexOf(char1);
                        int startcolumn = VALID_TURN_CHARS.indexOf(char0);
                        int endrow = VALID_TURN_NUMBERS.indexOf(char4);
                        int endcolumm = VALID_TURN_CHARS.indexOf(char3);
                        if (chessboard.hasChessPiece(startrow, startcolumn)) {
                            String chesspiece = chessboard.getChessPiece(startrow, startcolumn);
                            boolean whitepiece = chesspiece.equals(chesspiece.toUpperCase());
                            if (whiteturn && whitepiece || !whiteturn && !whitepiece) {
                                if (startrow != endrow && startcolumn != endcolumm) {
                                    chessboard.insertChessPiece(startrow, startcolumn, "1");
                                    chessboard.insertChessPiece(endrow, endcolumm, chesspiece);
                                    whiteturn = !whiteturn;
                                } else {
                                    ChessGame.changeExitCode(INVALID_NO_MOVE);
                                    throw new ChessException();
                                }
                            } else {
                                ChessGame.changeExitCode(INVALID_TURN_ORDER);
                                throw new ChessException();
                            }

                        } else {
                            ChessGame.changeExitCode(INVALID_NO_PIECES);
                            throw new ChessException();
                        }
                    }
                } else {
                    ChessGame.changeExitCode(INVALID_TURN_ERROR);
                    throw new ChessException();
                }
            }
            System.out.println(chessboard.createCurrentChessBoard());
        }
    }

    /**
     * @param input Startparameter wird geprueft fuer die Ausgabe des Spielfelds
     */
    void checkStartInput(String[] input, ChessBoard chessboard) throws ChessException {
        if (input.length == 0) { //Kein Startparameter => Grundstellung
            String grundstellung = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w";
            String stellung = grundstellung.substring(0, grundstellung.length() - 2);
            String startergrund = grundstellung.substring(grundstellung.length() - 1);
            if (validString(stellung, startergrund, chessboard)) {
                System.out.println(grundstellung);
            }
        } else if (input.length == 1) { //Startparameter => Wird ueberprueft und ggf. ubernommen
            String customstellung = input[0].substring(0, input[0].length() - 2);
            String startercustom = input[0].substring(input[0].length() - 1);
            if (validString(customstellung, startercustom, chessboard)) {
                System.out.println(input[0]);
            } else {
                ChessGame.changeExitCode(INVALID_INPUT_ERROR);
                throw new ChessException();
            }
        } else {
            ChessGame.changeExitCode(INVALID_INPUT_ERROR);
            throw new ChessException();
        }
    }

    /**
     * @param input gegebene Schachstellung vom Nutzer, die ueberprueft werden muss
     * @return true wenn die Schachstellung nach der Notation zulaessig ist
     * KOMPLEXITAET VERBESSERN
     */
    private static boolean validString(String input, String starter, ChessBoard chessboard) throws ChessException {
        final String[] zeilen = input.split("/", COLUMN_ROW_COUNT);
        char currentchar;
        for (int i = FOR_LOOP_ZERO; i < COLUMN_ROW_COUNT; i++) {
            int rowsum = 0;
            int fillone;
            for (int j = FOR_LOOP_ZERO; j < zeilen[i].length(); j++) {
                currentchar = zeilen[i].charAt(j);
                if (VALID_INPUT_CHARS.indexOf(currentchar) >= 0) {
                    rowsum++;
                    chessboard.insertChessPiece(i, j, Character.toString(currentchar));
                } else if (VALID_INPUT_NUMBERS.indexOf(currentchar) >= 0) {
                    rowsum += Character.getNumericValue(currentchar);
                    fillone = Character.getNumericValue(currentchar);
                    for (int k = j; k < fillone; k++) {
                        chessboard.insertChessPiece(i, k, "1");
                    }
                } else {
                    ChessGame.changeExitCode(INVALID_INPUT_ERROR);
                    throw new ChessException();
                }
            }
            if (rowsum != COLUMN_ROW_COUNT) {
                ChessGame.changeExitCode(INVALID_INPUT_ERROR);
                throw new ChessException();
            }
        }
        if (starter.length() == 1) {
            switch (starter) {
                case "w":
                    whiteturn = true;
                    return true;
                case "b":
                    whiteturn = false;
                    return true;
                default:
                    ChessGame.changeExitCode(INVALID_INPUT_ERROR);
                    throw new ChessException();
            }
        } else {
            ChessGame.changeExitCode(INVALID_INPUT_ERROR);
            throw new ChessException();
        }
    }


    static String getValidInputNumbers() {
        return VALID_INPUT_NUMBERS;
    }

    static String getValidInputChars() {
        return VALID_INPUT_CHARS;
    }
}
