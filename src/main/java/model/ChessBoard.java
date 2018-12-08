package model;


/**
 * Klasse fuer das Spielbrett - Datenstruktur mit einem char[][].
 */
class ChessBoard {

    private static final int LAST_ARRAY_BOARD = 7;

    private static final String BLACK_PIECES = "prnbqk";

    private static final String WHITE_PIECE = "PRNBQK";

    private ChessPiece[][] chessboardarr;


    /**
     * Konstruktor der Klasse.
     */
    /* default */ChessBoard() {
        chessboardarr = new ChessPiece[ChessGame.COLUMN_ROW_COUNT][ChessGame.COLUMN_ROW_COUNT];
    }

    /**
     * @param row    Position in der Reihe
     * @param column Position in der Spalte
     * @param piece  Figur, die platziert werden soll oder '0'.
     */
    /* default */ void insertChessPiece(final int row, final int column, final String piece) {
        String color;
        if (BLACK_PIECES.contains(piece)) {
            color = "black";
        } else if (WHITE_PIECE.contains(piece)) {
            color = "white";
        } else {
            color = null;
        }
        chessboardarr[row][column] = new ChessPiece(piece, color);
    }

    /**
     * @param row    Position in der Reihe
     * @param column Position in der Spalte
     * @return true wenn eine Figur auf dem Feld ist
     */
    /* default */boolean hasChessPiece(final int row, final int column) {
        return (ChessGame.getValidInputChars().contains(ChessPiece.getBez(getChessPiece(row, column))));
    }

    /**
     * @param row    Position in der Reihe
     * @param column Position in der Spalte
     * @return gibt die Figur auf dem gesuchten Feld aus.
     */
    /* default */ChessPiece getChessPiece(final int row, final int column) {
        return (chessboardarr[row][column]);
    }

    /**
     * @return gibt die aktuelle Schachstellung zurueck.
     */
    /* default */String createCurrentChessBoard() {
        final StringBuilder stringbuilder = new StringBuilder();
        for (int i = ChessGame.FOR_LOOP_ZERO; i < ChessGame.COLUMN_ROW_COUNT; i++) {
            int sum = 0;
            for (int j = ChessGame.FOR_LOOP_ZERO; j < ChessGame.COLUMN_ROW_COUNT; j++) {
                if (ChessPiece.getBez(getChessPiece(i,j)).equals("1")) {
                    sum++;
                    if (sum == ChessGame.COLUMN_ROW_COUNT) {
                        stringbuilder.append(sum);
                    } else if (j == LAST_ARRAY_BOARD && sum > 0) {
                        stringbuilder.append(sum);
                    }
                } else {
                    if (sum > 0) {
                        stringbuilder.append(sum);
                        sum = 0;
                    }
                    stringbuilder.append(ChessPiece.getBez(getChessPiece(i, j)));
                }
            }
            if (i < LAST_ARRAY_BOARD) {
                stringbuilder.append('/');
            }
        }
        stringbuilder.append(" " + ChessGame.getTurnOrder());
        return stringbuilder.toString();
    }

}