package de.techfak.gse.abadner;

/**
 * Klasse zum Abfangen der Exceptions.
 */
class ChessException extends Exception {
     ChessException() {
        super("Unerlaubte Stellung");
    }
}
