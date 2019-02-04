package model;

public class ChessRules {
    private static final int MAXMOVESKING = 8;

    private static final int MAXMOVESKNIGHTS = 8;

    private static final int MAXMOVESPAWN = 4;

    private static final int MAXMOVESROOK = 14;

    private static final int MAXMOVESBISHOP = 13;

    private static final int MAXMOVESQUEEN = 27;

    /**
     * Returns a list of possibles moves for the specific piece.
     *
     * @param row
     * @param column
     * @param piece
     */
    public void createpathlist(int row, int column, String piece) {
        String[] moves;
        switch (piece) {
            case "p":
                moves = new String[MAXMOVESPAWN];
                break;
            case "P":
                moves = new String[MAXMOVESPAWN];
                break;
            case "r":
            case "R":
                moves = new String[MAXMOVESROOK];
                break;
            case "n":
            case "N":
                moves = new String[MAXMOVESKNIGHTS];
                break;
            case "b":
            case "B":
                moves = new String[MAXMOVESBISHOP];
                break;
            case "q":
            case "Q":
                moves = new String[MAXMOVESQUEEN];
                break;
            case "k":
            case "K":
                moves = new String[MAXMOVESKING];
                break;
            default:
                break;
        }
    }

}
