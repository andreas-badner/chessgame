package controller;

import java.util.Observable;
import java.util.Observer;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import view.CreateChessBoard;

/**
 * Controller fuer MVC, nachdem Spiel gestartet wurde.
 */
public class ChessBoardController implements Observer {

    private static final int COLUMN_ROW_COUNT = 8;

    private model.ChessTurn chessTurn;

    @FXML
    private TextField zugrecht = new TextField();

    @FXML
    private CreateChessBoard createChessBoard = new CreateChessBoard();

    public ChessBoardController() {
    }

    @FXML
    private void initialize() {
        this.createChessBoard.initialize();
    }

    private void setzugrecht(boolean recht) {
        if (recht) {
            zugrecht.setText("Turn: White");
        } else {
            zugrecht.setText("Turn: Black");
        }
    }

    void setModel(final model.ChessTurn chessTurn) {
        this.chessTurn = chessTurn;
    }

    @Override
    public void update(final Observable observable, final Object o) {
        if (observable.equals(this.chessTurn)) {
            if (chessTurn.getwhiteturn()) {
                setzugrecht(true);
            } else {
                setzugrecht(false);
            }
            insertChessPiece();
        }
    }

    @FXML
    private void insertChessPiece() {
        for (int row = 0; row < COLUMN_ROW_COUNT; row++) {
            for (int column = 0; column < COLUMN_ROW_COUNT; column++) {
                if (chessTurn.hasChessPiece(row, column)) {
                    this.createChessBoard.insertChessPiece(row, column, chessTurn.getPiecebez(row, column));
                }
            }
        }
    }
}
