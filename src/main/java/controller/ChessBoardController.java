package controller;

import java.util.Observable;
import java.util.Observer;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import view.CreateChessBoard;

public class ChessBoardController implements Observer {

    private model.ChessTurn chessTurn;

    @FXML
    private TextField zugrecht;

    @FXML
    private CreateChessBoard createChessBoard;

    public ChessBoardController() {
    }

    @FXML
    private void initialize() {
        this.createChessBoard.initialize();
        setzugrecht(true);
    }

    @FXML
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
        }
    }
}
