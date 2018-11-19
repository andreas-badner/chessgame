package de.techfak.gse.abadner;

/**
 * Klasse zum Abfangen von Exceptions.
 */
@SuppressWarnings("serial")
class ChessException extends Exception {
    /* default */ChessException() {
        super("Fehler.");
    }
}
