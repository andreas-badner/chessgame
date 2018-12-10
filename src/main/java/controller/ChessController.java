package controller;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import view.CreateChessBoard;

/**
 * Controller fuer MVC,nachdem Spiel gestartet wurde.
 */
public class ChessController implements Observer {
    private static final int COLUMN_ROW_COUNT = 8;

    private Stage stage;

    private model.ChessGame chessGame;

    private TextField zugrecht = new TextField();

    private CreateChessBoard createChessBoard = new CreateChessBoard();

    public ChessController() {
    }

    public void menu(ActionEvent event) throws IOException {
        visualize();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setModel(final model.ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    private void setzugrecht(boolean recht) {
        if (recht) {
            zugrecht.setText("Turn: White");
        } else {
            zugrecht.setText("Turn: Black");
        }
    }

    /**
     * update Methode des Obeserveres - Aufruf bei Veraenderung in Model.
     *
     * @param observable Das zu observierende Objekt.
     * @param o          Das Objekt.
     */
    public void update(final Observable observable, final Object o) {
        if (observable.equals(this.chessGame)) {
            if (chessGame.chessTurn.getwhiteturn()) {
                setzugrecht(true);
            } else {
                setzugrecht(false);
            }
            visualize();
        }
    }

    private void insertChessPiece() {
        for (int row = 0; row < COLUMN_ROW_COUNT; row++) {
            for (int column = 0; column < COLUMN_ROW_COUNT; column++) {
                if (chessGame.chessBoard.hasChessPiece(row, column)) {
                    this.createChessBoard.insertChessPiece(row, column,
                                                           model.ChessPiece.getBez(chessGame.chessBoard.getChessPiece(row,
                                                                                                                      column)));
                }
            }
        }
    }

    private void visualize() {
        this.createChessBoard.createBoard();
        createChessBoard.visualize(this.stage);
        insertChessPiece();
    }
}
