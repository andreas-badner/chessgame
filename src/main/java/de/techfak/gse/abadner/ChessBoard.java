package de.techfak.gse.abadner;


/**
 * Klasse fuer das Spielbrett - Datenstruktur mit einem char[][].
 */
public class ChessBoard {
    private static char[][] chessboardarr;

    /**
     * Konstruktor, der den Array erzeugt.
     */
    ChessBoard() {
        chessboardarr = new char[ChessGame.COLUMN_ROW_COUNT][ChessGame.COLUMN_ROW_COUNT];
    }

    /**
     * @param row    Position in der Reihe
     * @param column Position in der Spalte
     * @param piece  Figur, die platziert werden soll oder '0'.
     */
    static void insertChessPiece(int row, int column, char piece) {
        chessboardarr[row][column] = piece;
    }

    /**
     * @param row    Position in der Reihe
     * @param column Position in der Spalte
     * @return true wenn eine Figur auf dem Feld ist
     */
    public boolean hasChessPiece(int row, int column) {
        return (chessboardarr[row][column] != 0);
    }

    /**
     * @param row    Position in der Reihe
     * @param column Position in der Spalte
     * @return gibt die Figur auf dem gesuchten Feld aus.
     */
    public char getChessPiece(int row, int column) {
        return (chessboardarr[row][column]);
    }

    /**
     * @return gibt die aktuelle Schachstellung zurueck.
     */
    public String createCurrentChessBoard() {
        StringBuilder stringbuilder = new StringBuilder();
        for (int i = ChessGame.FOR_LOOP_ZERO; i < ChessGame.COLUMN_ROW_COUNT; i++) {
            for (int j = ChessGame.FOR_LOOP_ZERO; j < ChessGame.COLUMN_ROW_COUNT; j++) {
                stringbuilder.append(getChessPiece(i, j));
            }
            if (i < ChessGame.LAST_ARRAY_BOARD) {
                stringbuilder.append("/");
            }
        }
        return stringbuilder.toString();
    }
}
