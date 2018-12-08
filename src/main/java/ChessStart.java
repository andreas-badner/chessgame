import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import controller.ChessController;
import model.ChessGame;

/**
 * Klasse zum Starten der Applikation.
 * Startet mit GUI oder Konsole.
 */
public class ChessStart extends Application {

    /**
     * main-Methode.
     *
     * @param args prueft Startparameter auf "--gui", sonst Kommandozeile
     */
    public static void main(String[] args) {
        if (args.length == 1 && args[0].equals("--git")) {
            launch();
        } else {
            ChessGame chessgame = new ChessGame();
            chessgame.run(args);
        }
        System.exit(0);
    }

    @Override
    public void start(final Stage stage) throws IOException {
        ChessGame chessgame = new ChessGame();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("ChessFXML.fxml"));
        Pane root = fxmlLoader.load();
        ChessController chessController = fxmlLoader.getController();
        //chessgame.addObserver(chessController);
        //chessController.setModel(chessgame);
        Scene scene = new Scene(root);
        stage.setTitle("ChessGame");
        stage.setScene(scene);
        stage.show();
        String[] args = {};
        chessgame.run(args);
    }
}
