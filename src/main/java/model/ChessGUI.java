package model;

import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

/**
 * Klasse zum Starten der Applikation.
 * Startet mit GUI oder Konsole.
 */
public class ChessGUI extends Application {

    /**
     * main-Methode.
     *
     * @param args prueft Startparameter auf "--gui", sonst Kommandozeile
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage stage) throws IOException {
        model.ChessGame chessGame = new model.ChessGame(new String[] {"standardgui"});
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("ChessFXML.fxml"));
        Pane root = fxmlLoader.load();
        controller.ChessController chessController = fxmlLoader.getController();
        chessGame.addObserver(chessController);
        chessController.setModel(chessGame);
        Scene menuscene = new Scene(root);
        stage.setTitle("ChessGame");
        stage.setScene(menuscene);
        chessController.setStage(stage);
        stage.setResizable(false);
        stage.show();
    }
}
