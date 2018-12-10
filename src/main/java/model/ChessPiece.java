package model;

/**
 * Klasse, die Figurenobjekte erzeugt und deren Daten speichert.
 */
public class ChessPiece {
    private String bez;

    private String color;

    ChessPiece(String bez, String farbe) {
        this.bez = bez;
        this.color = farbe;
    }

    public static String getBez(ChessPiece figur) {
        return figur.bez;
    }

    String getColor(ChessPiece figur) {
        return figur.color;
    }
}
