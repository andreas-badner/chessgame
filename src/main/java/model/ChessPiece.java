package model;

/**
 * Klasse, die Figurenobjekte erzeugt und deren Daten speichert.
 */
class ChessPiece {
    private String bez;
    private String color;
    ChessPiece(String bez, String farbe) {
        this.bez = bez;
        this.color = farbe;
    }

    static String getBez(ChessPiece figur) {
        return figur.bez;
    }

    String getColor(ChessPiece figur) {
        return figur.color;
    }
}
