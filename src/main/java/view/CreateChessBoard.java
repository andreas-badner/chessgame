package view;

import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import controller.ChessController;

/**
 * Klasse, die alles bezueglich des Schachbretts visualisiert.
 */
public class CreateChessBoard {

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

    private TextField starter;

    private GridPane gpane;

    public CreateChessBoard() {
    }

    /**
     * Erstellt ein visuelles Schachbrett in Standardaufstellung.
     */
    public void createBoard() {
        this.gpane = new GridPane();
        this.starter = new TextField();
    }

    /**
     * Fuegt die Figuren visuell ein.
     *
     * @param row    Die Reihe auf dem Spielbrett.
     * @param column Die Spalte auf dem Spielbrett.
     * @param piece  Die Bezeichung der Figur.
     */
    public void insertChessPiece(int row, int column, String piece) {
        switch (piece) {
            case "p":
                Image bpawn1 = new Image(BLACKPAWN);
                ImageView imagebp1 = new ImageView(bpawn1);
                imagebp1.setFitHeight(SIZE);
                imagebp1.setFitWidth(SIZE);
                gpane.add(imagebp1, column, row);
                GridPane.setHalignment(imagebp1, HPos.CENTER);
                break;
            case "P":
                Image wpawn1 = new Image(WHITEPAWN);
                ImageView imagewp1 = new ImageView(wpawn1);
                imagewp1.setFitHeight(SIZE);
                imagewp1.setFitWidth(SIZE);
                gpane.add(imagewp1, column, row);
                GridPane.setHalignment(imagewp1, HPos.CENTER);
                break;
            case "r":
                Image blackimage = new Image(BLACKROOK);
                ImageView imagevb = new ImageView(blackimage);
                imagevb.setFitHeight(SIZE);
                imagevb.setFitWidth(SIZE);
                gpane.add(imagevb, column, row);
                GridPane.setHalignment(imagevb, HPos.CENTER);
                break;
            case "R":
                Image whiteimage = new Image(WHITEROOK);
                ImageView imagevw = new ImageView(whiteimage);
                imagevw.setFitWidth(SIZE);
                imagevw.setFitHeight(SIZE);
                gpane.add(imagevw, column, row);
                GridPane.setHalignment(imagevw, HPos.CENTER);
                break;
            case "n":
                blackimage = new Image(BLACKKNIGHT);
                imagevb = new ImageView(blackimage);
                imagevb.setFitHeight(SIZE);
                imagevb.setFitWidth(SIZE);
                gpane.add(imagevb, column, row);
                GridPane.setHalignment(imagevb, HPos.CENTER);
                break;
            case "N":
                whiteimage = new Image(WHITEKNIGHT);
                imagevw = new ImageView(whiteimage);
                imagevw.setFitWidth(SIZE);
                imagevw.setFitHeight(SIZE);
                gpane.add(imagevw, column, row);
                GridPane.setHalignment(imagevw, HPos.CENTER);
                break;
            case "b":
                blackimage = new Image(BLACKBISHOP);
                imagevb = new ImageView(blackimage);
                imagevb.setFitHeight(SIZE);
                imagevb.setFitWidth(SIZE);
                gpane.add(imagevb, column, row);
                GridPane.setHalignment(imagevb, HPos.CENTER);
                break;
            case "B":
                whiteimage = new Image(WHITEBISHOP);
                imagevw = new ImageView(whiteimage);
                imagevw.setFitWidth(SIZE);
                imagevw.setFitHeight(SIZE);
                gpane.add(imagevw, column, row);
                GridPane.setHalignment(imagevw, HPos.CENTER);
                break;
            case "q":
                blackimage = new Image("queen_black.png");
                imagevb = new ImageView(blackimage);
                imagevb.setFitHeight(SIZE);
                imagevb.setFitWidth(SIZE);
                gpane.add(imagevb, column, row);
                GridPane.setHalignment(imagevb, HPos.CENTER);
                break;
            case "Q":
                whiteimage = new Image("queen_white.png");
                imagevw = new ImageView(whiteimage);
                imagevw.setFitWidth(SIZE);
                imagevw.setFitHeight(SIZE);
                gpane.add(imagevw, column, row);
                GridPane.setHalignment(imagevw, HPos.CENTER);
                break;
            case "k":
                blackimage = new Image("king_black.png");
                imagevb = new ImageView(blackimage);
                imagevb.setFitHeight(SIZE);
                imagevb.setFitWidth(SIZE);
                gpane.add(imagevb, column, row);
                GridPane.setHalignment(imagevb, HPos.CENTER);
                break;
            case "K":
                whiteimage = new Image("king_white.png");
                imagevw = new ImageView(whiteimage);
                imagevw.setFitWidth(SIZE);
                imagevw.setFitHeight(SIZE);
                gpane.add(imagevw, column, row);
                GridPane.setHalignment(imagevw, HPos.CENTER);
                break;
            default:
                break;
        }
    }

    /**
     * Setting up the ChessBoard.
     *
     * @param stage Using the existing Stage.
     */
    public void visualize(Stage stage, ChessController chessController, String start) {
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
                gpane.add(rect, column, row);
            }
            whitefield = !whitefield;
        }
        gpane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(final MouseEvent event) {
                Node node = event.getPickResult().getIntersectedNode();
                int row = GridPane.getRowIndex(node);
                int column = GridPane.getColumnIndex(node);
                chessController.mouseclick(row, column);
            }
        });
        HBox hbox = new HBox(10);
        VBox vbox = new VBox(10);
        starter.setText(start);
        starter.setDisable(true);
        Button buttonsave = new Button("Save as sFEN file");
        Button buttonload = new Button("Load a sFEN file");
        hbox.getChildren().addAll(starter, buttonsave, buttonload);
        vbox.getChildren().addAll(hbox, gpane);
        Scene scene = new Scene(vbox);
        stage.setScene(scene);
    }

    public void highlightfield(int row, int column) {
        Light.Distant light = new Light.Distant();
        light.setAzimuth(0);
        Lighting highlight = new Lighting();
        highlight.setLight(light);
        highlight.setSurfaceScale(1.0);
        Node node = getNode(row, column);
        if (node != null) {
            node.setEffect(highlight);
        }
    }

    public void removehighlight(int row, int column) {
        Node node = getNode(row, column);
        if (node != null) {
            node.setEffect(null);
        }
    }

    private Node getNode(int row, int column) {
        for (Node node : gpane.getChildren()) {
            if (GridPane.getColumnIndex(node) == column && GridPane.getRowIndex(node) == row) {
                return node;
            }
        }
        return null;
    }
}
