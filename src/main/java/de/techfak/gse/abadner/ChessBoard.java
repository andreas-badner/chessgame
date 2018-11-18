package de.techfak.gse.abadner;


/**
 * Klasse fuer das Spielbrett - Datenstruktur mit einem char[][].
 */
class ChessBoard {

    private static final int LAST_ARRAY_BOARD = 7;

    private String[][] chessboardarr;


    /**
     * Konstruktor der Klasse.
     */
    ChessBoard() {
        chessboardarr = new String[ChessTurn.COLUMN_ROW_COUNT][ChessTurn.COLUMN_ROW_COUNT];
    }

    /**
     * @param row    Position in der Reihe
     * @param column Position in der Spalte
     * @param piece  Figur, die platziert werden soll oder '0'.
     */
    void insertChessPiece(int row, int column, String piece) {
        chessboardarr[row][column] = piece;
    }

    /**
     * @param row    Position in der Reihe
     * @param column Position in der Spalte
     * @return true wenn eine Figur auf dem Feld ist
     */
    boolean hasChessPiece(int row, int column) {
        return (ChessTurn.getValidInputChars().contains(chessboardarr[row][column]));
    }

    /**
     *
     */
    String[][] getChessboardarr() {
        return this.chessboardarr;
    }

    /**
     * @param row    Position in der Reihe
     * @param column Position in der Spalte
     * @return gibt die Figur auf dem gesuchten Feld aus.
     */
    String getChessPiece(int row, int column) {
        return (chessboardarr[row][column]);
    }

    /**
     * @return gibt die aktuelle Schachstellung zurueck.
     */
    String createCurrentChessBoard() {
        StringBuilder stringbuilder = new StringBuilder();
        for (int i = ChessTurn.FOR_LOOP_ZERO; i < ChessTurn.COLUMN_ROW_COUNT; i++) {
            int sum = 0;
            for (int j = ChessTurn.FOR_LOOP_ZERO; j < ChessTurn.COLUMN_ROW_COUNT; j++) {
                if (chessboardarr[i][j].equals("1")) {
                    sum++;
                    if (sum == ChessTurn.COLUMN_ROW_COUNT) {
                        stringbuilder.append(sum);
                    } else if (j == LAST_ARRAY_BOARD && sum > 0) {
                        stringbuilder.append(sum);
                    }
                } else {
                    if (sum > 0) {
                        stringbuilder.append(sum);
                        sum = 0;
                    }
                    stringbuilder.append(getChessPiece(i, j));
                }
            }
            if (i < LAST_ARRAY_BOARD) {
                stringbuilder.append("/");
            }
        }
        return stringbuilder.toString();
    }
}
