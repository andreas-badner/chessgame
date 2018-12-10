package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ChessController {

    private Stage stage;

    public ChessController() {
    }

    public void menu(ActionEvent event) throws IOException {
        model.ChessTurn chessTurn = new model.ChessTurn();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("ChessBoardFXML.fxml"));
        Pane root = fxmlLoader.load();
        controller.ChessBoardController chessBoardController = new ChessBoardController();
        chessBoardController.setModel(chessTurn);
        chessTurn.addObserver(chessBoardController);
        chessBoardController.setModel(chessTurn);
        Scene boardscene = new Scene(root);
        this.stage.setScene(boardscene);
        chessTurn.runstandard();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
