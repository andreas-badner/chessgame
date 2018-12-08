import java.io.IOException;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

import controller.ChessController;
import model.ChessTurn;

/**
 * Klasse zum Starten der Applikation.
 * Startet mit GUI oder Konsole.
 */
public class ChessGame extends Application {

    /**
     * main-Methode.
     *
     * @param args prueft Startparameter auf "--gui", sonst Kommandozeile
     */
    public static void main(String[] args) {
        if (args.length == 1 && args[0].equals("--gui")) {
            launch();
        } else {
            ChessTurn chessturn = new ChessTurn();
            chessturn.run(args);
        }
        System.exit(0);
    }

    @Override
    public void start(final Stage stage) throws IOException {
        ChessTurn chessturn = new ChessTurn();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("ChessFXML.fxml"));
        Pane root = fxmlLoader.load();
        ChessController chessController = fxmlLoader.getController();
        chessturn.addObserver(chessController);
        chessController.setModel(chessturn);
        chessController.setChessTurn(chessturn);
        Scene scene = new Scene(root);
        stage.setTitle("ChessGame");
        stage.setScene(scene);
        stage.show();
    }
}
