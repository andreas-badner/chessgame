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
public class ChessGame extends Application {

    /**
     * main-Methode.
     *
     * @param args prueft Startparameter auf "--gui", sonst Kommandozeile
     */
    public static void main(String[] args) {
        if (args.length == 1) {
            if (args[0].equals("--gui")) {
                launch();
            }
        } else {
            model.ChessTurn chessturn = new model.ChessTurn();
            chessturn.run(args);
        }
        System.exit(0);
    }

    @Override
    public void start(final Stage stage) throws IOException {
        model.ChessTurn chessturn = new model.ChessTurn();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("ChessFXML.fxml"));
        Pane root = fxmlLoader.load();
        controller.ChessController chessController = fxmlLoader.getController();
        chessturn.addObserver(chessController);
        chessController.setModel(chessturn);
        chessController.setChessTurn(chessturn);
        Scene scene = new Scene(root);
        stage.setTitle("ChessGame");
        stage.setScene(scene);
        stage.show();
    }
}
