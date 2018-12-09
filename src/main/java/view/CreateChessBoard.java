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

    private static final int LAST_ROW = 7;

    private static final int SEVENTHROW = 6;

    private static final int SIXTHROW = 5;

    private static final int FIFTHROW = 4;

    private static final int FOURTHROW = 3;

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
                if (row == 0) {
                    if (column == 0) {
                        Image blackimage = new Image(BLACKROOK);
                        ImageView imagevb = new ImageView(blackimage);
                        imagevb.setFitHeight(SIZE);
                        imagevb.setFitWidth(SIZE);
                        this.add(imagevb, column, row);
                    } else if (column == 1) {
                        Image blackimage = new Image(BLACKKNIGHT);
                        ImageView imagevb = new ImageView(blackimage);
                        imagevb.setFitHeight(SIZE);
                        imagevb.setFitWidth(SIZE);
                        this.add(imagevb, column, row);
                    } else if (column == 2) {
                        Image blackimage = new Image(BLACKBISHOP);
                        ImageView imagevb = new ImageView(blackimage);
                        imagevb.setFitHeight(SIZE);
                        imagevb.setFitWidth(SIZE);
                        this.add(imagevb, column, row);
                    } else if (column == FOURTHROW) {
                        Image blackimage = new Image("queen_black.png");
                        ImageView imagevb = new ImageView(blackimage);
                        imagevb.setFitHeight(SIZE);
                        imagevb.setFitWidth(SIZE);
                        this.add(imagevb, column, row);
                    } else if (column == FIFTHROW) {
                        Image blackimage = new Image("king_black.png");
                        ImageView imagevb = new ImageView(blackimage);
                        imagevb.setFitHeight(SIZE);
                        imagevb.setFitWidth(SIZE);
                        this.add(imagevb, column, row);
                    } else if (column == SIXTHROW) {
                        Image blackimage = new Image(BLACKBISHOP);
                        ImageView imagevb = new ImageView(blackimage);
                        imagevb.setFitHeight(SIZE);
                        imagevb.setFitWidth(SIZE);
                        this.add(imagevb, column, row);
                    } else if (column == SEVENTHROW) {
                        Image blackimage = new Image(BLACKKNIGHT);
                        ImageView imagevb = new ImageView(blackimage);
                        imagevb.setFitHeight(SIZE);
                        imagevb.setFitWidth(SIZE);
                        this.add(imagevb, column, row);
                    } else {
                        Image blackimage = new Image(BLACKROOK);
                        ImageView imagevb = new ImageView(blackimage);
                        imagevb.setFitHeight(SIZE);
                        imagevb.setFitWidth(SIZE);
                        this.add(imagevb, column, row);
                    }
                } else if (row == LAST_ROW) {
                    if (column == 0) {
                        Image whiteimage = new Image(WHITEROOK);
                        ImageView imagevw = new ImageView(whiteimage);
                        imagevw.setFitWidth(SIZE);
                        imagevw.setFitHeight(SIZE);
                        this.add(imagevw, column, row);
                    } else if (column == 1) {
                        Image whiteimage = new Image(WHITEKNIGHT);
                        ImageView imagevw = new ImageView(whiteimage);
                        imagevw.setFitWidth(SIZE);
                        imagevw.setFitHeight(SIZE);
                        this.add(imagevw, column, row);
                    } else if (column == 2) {
                        Image whiteimage = new Image(WHITEBISHOP);
                        ImageView imagevw = new ImageView(whiteimage);
                        imagevw.setFitWidth(SIZE);
                        imagevw.setFitHeight(SIZE);
                        this.add(imagevw, column, row);
                    } else if (column == FOURTHROW) {
                        Image whiteimage = new Image("queen_white.png");
                        ImageView imagevw = new ImageView(whiteimage);
                        imagevw.setFitWidth(SIZE);
                        imagevw.setFitHeight(SIZE);
                        this.add(imagevw, column, row);
                    } else if (column == FIFTHROW) {
                        Image whiteimage = new Image("king_white.png");
                        ImageView imagevw = new ImageView(whiteimage);
                        imagevw.setFitWidth(SIZE);
                        imagevw.setFitHeight(SIZE);
                        this.add(imagevw, column, row);
                    } else if (column == SIXTHROW) {
                        Image whiteimage = new Image(WHITEBISHOP);
                        ImageView imagevw = new ImageView(whiteimage);
                        imagevw.setFitWidth(SIZE);
                        imagevw.setFitHeight(SIZE);
                        this.add(imagevw, column, row);
                    } else if (column == SEVENTHROW) {
                        Image whiteimage = new Image(WHITEKNIGHT);
                        ImageView imagevw = new ImageView(whiteimage);
                        imagevw.setFitWidth(SIZE);
                        imagevw.setFitHeight(SIZE);
                        this.add(imagevw, column, row);
                    } else {
                        Image whiteimage = new Image(WHITEROOK);
                        ImageView imagevw = new ImageView(whiteimage);
                        imagevw.setFitWidth(SIZE);
                        imagevw.setFitHeight(SIZE);
                        this.add(imagevw, column, row);
                    }
                } else if (row == 1) {
                    Image bpawn1 = new Image(BLACKPAWN);
                    ImageView imagebp1 = new ImageView(bpawn1);
                    imagebp1.setFitHeight(SIZE);
                    imagebp1.setFitWidth(SIZE);
                    this.add(imagebp1, column, row);
                } else if (row == SEVENTHROW) {
                    Image wpawn1 = new Image(WHITEPAWN);
                    ImageView imagewp1 = new ImageView(wpawn1);
                    imagewp1.setFitHeight(SIZE);
                    imagewp1.setFitWidth(SIZE);
                    this.add(imagewp1, column, row);
                }
            }
            whitefield = !whitefield;
        }
    }
}
