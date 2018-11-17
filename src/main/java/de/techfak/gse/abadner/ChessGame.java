package de.techfak.gse.abadner;

public class ChessGame {

    private static final int EXIT_INTEGER = 0;

    public static void main(final String... args) {
        System.out.println("Hello abadner!");
        if (args.length == 0) { //Kein Startparameter
            System.out.println("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w"); //Gibt Grundstellung in FEN aus
        }
    }
}
