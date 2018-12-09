package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ChessController {

    private model.ChessTurn chessTurn;

    private Stage stage;

    public ChessController() {
    }

    public void setModel(final model.ChessTurn chessTurn) {
        this.chessTurn = chessTurn;
    }

    public void menu(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("ChessBoardFXML.fxml"));
        Pane root = fxmlLoader.load();
        controller.ChessBoardController chessBoardController = new ChessBoardController();
        chessTurn.addObserver(chessBoardController);
        chessBoardController.setModel(chessTurn);
        Scene boardscene = new Scene(root);
        this.stage.setScene(boardscene);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
