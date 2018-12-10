package view;

import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.image.Image;

/**
 * Klasse, die alles bezueglich des Schachbretts visualisiert.
 */
public class CreateChessBoard extends GridPane {
    private static final int COLUMN_ROW_COUNT = 8;

    private static boolean whitefield = true;

    private static final int SIZE = 100;

    private static final int RECTSIZE = 111;

    private static final String BLACKROOK = "rook_black.png";

    private static final String WHITEROOK = "rook_white.png";

    private static final String BLACKBISHOP = "bishop_black.png";

    private static final String WHITEBISHOP = "bishop_white.png";

    private static final String BLACKKNIGHT = "knight_black.png";

    private static final String WHITEKNIGHT = "knight_white.png";

    private static final String BLACKPAWN = "pawn_black.png";

    private static final String WHITEPAWN = "pawn_white.png";

    /**
     * Erstellt ein visuelles Schachbrett in Standardaufstellung.
     */
    public void initialize() {
        for (int row = 0; row < COLUMN_ROW_COUNT; row++) {
            for (int column = 0; column < COLUMN_ROW_COUNT; column++) {
                Rectangle rect = new Rectangle(RECTSIZE, RECTSIZE);
                if (whitefield) {
                    rect.setFill(Color.WHITE);
                    rect.setStroke(Color.WHITE);
                    whitefield = !whitefield;
                } else {
                    rect.setFill(Color.BROWN);
                    rect.setStroke(Color.BROWN);
                    whitefield = !whitefield;
                }
                this.add(rect, column, row);
            }
            whitefield = !whitefield;
        }
    }

    /**
     * Fuegt die Figuren visuell ein.
     * @param row Die Reihe auf dem Spielbrett.
     * @param column Die Spalte auf dem Spielbrett.
     * @param piece Die Bezeichung der Figur.
     */
    public void insertChessPiece(int row, int column, String piece) {
        switch (piece) {
            case "p":
                Image bpawn1 = new Image(BLACKPAWN);
                ImageView imagebp1 = new ImageView(bpawn1);
                imagebp1.setFitHeight(SIZE);
                imagebp1.setFitWidth(SIZE);
                this.add(imagebp1, column, row);
                break;
            case "P":
                Image wpawn1 = new Image(WHITEPAWN);
                ImageView imagewp1 = new ImageView(wpawn1);
                imagewp1.setFitHeight(SIZE);
                imagewp1.setFitWidth(SIZE);
                this.add(imagewp1, column, row);
                break;
            case "r":
                Image blackimage = new Image(BLACKROOK);
                ImageView imagevb = new ImageView(blackimage);
                imagevb.setFitHeight(SIZE);
                imagevb.setFitWidth(SIZE);
                this.add(imagevb, column, row);
                break;
            case "R":
                Image whiteimage = new Image(WHITEROOK);
                ImageView imagevw = new ImageView(whiteimage);
                imagevw.setFitWidth(SIZE);
                imagevw.setFitHeight(SIZE);
                this.add(imagevw, column, row);
                break;
            case "n":
                blackimage = new Image(BLACKKNIGHT);
                imagevb = new ImageView(blackimage);
                imagevb.setFitHeight(SIZE);
                imagevb.setFitWidth(SIZE);
                this.add(imagevb, column, row);
                break;
            case "N":
                whiteimage = new Image(WHITEKNIGHT);
                imagevw = new ImageView(whiteimage);
                imagevw.setFitWidth(SIZE);
                imagevw.setFitHeight(SIZE);
                this.add(imagevw, column, row);
                break;
            case "b":
                blackimage = new Image(BLACKBISHOP);
                imagevb = new ImageView(blackimage);
                imagevb.setFitHeight(SIZE);
                imagevb.setFitWidth(SIZE);
                this.add(imagevb, column, row);
                break;
            case "B":
                whiteimage = new Image(WHITEBISHOP);
                imagevw = new ImageView(whiteimage);
                imagevw.setFitWidth(SIZE);
                imagevw.setFitHeight(SIZE);
                this.add(imagevw, column, row);
                break;
            case "q":
                blackimage = new Image("queen_black.png");
                imagevb = new ImageView(blackimage);
                imagevb.setFitHeight(SIZE);
                imagevb.setFitWidth(SIZE);
                this.add(imagevb, column, row);
                break;
            case "Q":
                whiteimage = new Image("queen_white.png");
                imagevw = new ImageView(whiteimage);
                imagevw.setFitWidth(SIZE);
                imagevw.setFitHeight(SIZE);
                this.add(imagevw, column, row);
                break;
            case "k":
                blackimage = new Image("king_black.png");
                imagevb = new ImageView(blackimage);
                imagevb.setFitHeight(SIZE);
                imagevb.setFitWidth(SIZE);
                this.add(imagevb, column, row);
                break;
            case "K":
                whiteimage = new Image("king_white.png");
                imagevw = new ImageView(whiteimage);
                imagevw.setFitWidth(SIZE);
                imagevw.setFitHeight(SIZE);
                this.add(imagevw, column, row);
                break;
            default:
                break;
        }
    }
}
