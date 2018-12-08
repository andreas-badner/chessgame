package controller;

import java.util.Observable;
import java.util.Observer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;


public class ChessController implements Observer {
    @FXML
    private Text instruction;

    private model.ChessTurn chessTurn;

    public ChessController() {
    }

    @FXML
    public void initialize() {
    }

    public void setModel(final model.ChessTurn chessTurn) {
        this.chessTurn = chessTurn;
    }

    @Override
    public void update(final Observable observable, final Object o) {
        if (observable.equals(this.chessTurn) && !chessTurn.getuserinput()) {
            System.exit(0);
        }
    }

    public void newgame(ActionEvent event) {
        this.chessTurn.runstandard();
    }

    public void setChessTurn(model.ChessTurn chessturn) {
        this.chessTurn = chessturn;
    }
}
