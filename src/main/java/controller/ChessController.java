package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

import model.ChessPiece;
import view.CreateChessBoard;

/**
 * Controller fuer MVC,nachdem Spiel gestartet wurde.
 */
public class ChessController implements Observer {

    private static int counter = 0;

    private static final int COLUMN_ROW_COUNT = 8;

    private static ArrayList<Integer> rowlist = new ArrayList<>();

    private static ArrayList<Integer> columnlist = new ArrayList<>();

    private Stage stage;

    private model.ChessGame chessGame;

    private String zugrecht;

    private CreateChessBoard createChessBoard = new CreateChessBoard();

    public void menu() {
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
            zugrecht = "Turn: White";
        } else {
            zugrecht = "Turn: Black";
        }
    }

    /**
     * update Methode des Obeserveres - Aufruf bei Veraenderung in Model.
     *
     * @param observable Das zu observierende Objekt.
     * @param o          Das Objekt.
     */
    @Override
    public void update(final Observable observable, final Object o) {
        if (observable.equals(this.chessGame)) {
            visualize();
        }
    }

    private void insertChessPiece() {
        for (int row = 0; row < COLUMN_ROW_COUNT; row++) {
            for (int column = 0; column < COLUMN_ROW_COUNT; column++) {
                if (chessGame.chessBoard.hasChessPiece(row, column)) {
                    createChessBoard.insertPiece(row, column, model.ChessPiece.getBez(
                        chessGame.chessBoard.getChessPiece(row, column)));
                }
            }
        }
    }

    public void loadGame() {
        loadgame();
    }

    private void visualize() {
        if (chessGame.chessTurn.getwhiteturn()) {
            setzugrecht(true);
        } else {
            setzugrecht(false);
        }
        this.createChessBoard.createBoard();
        createChessBoard.visualize(this.stage, this, zugrecht);
        insertChessPiece();
    }

    /**
     * handles a mouseclick event on the chessboard.
     *
     * @param row    the row indicates the field on the board to find the piece
     * @param column the column indicates the field on the board to find the piece
     */
    public void mouseclick(int row, int column) {
        String chessPiece = ChessPiece.getBez(chessGame.chessBoard.getChessPiece(row, column));
        if (counter == 0) {
            if (chessGame.chessBoard.hasChessPiece(row, column) && (
                chessGame.chessTurn.getwhiteturn() && chessPiece.equals(chessPiece.toUpperCase()) ||
                !chessGame.chessTurn.getwhiteturn() && chessPiece.equals(chessPiece.toLowerCase()))) {
                createChessBoard.highlightfield(row, column);
                counter++;
                rowlist.add(row);
                columnlist.add(column);
            }
        } else {
            rowlist.add(row);
            columnlist.add(column);
            if (rowlist.get(0).equals(rowlist.get(1)) && columnlist.get(0).equals(columnlist.get(1))) {
                createChessBoard.removehighlight(row, column);
                rowlist.clear();
                columnlist.clear();
                counter = 0;
            } else if (chessGame.chessBoard.hasChessPiece(row, column) &&
                       (chessGame.chessTurn.getwhiteturn() && chessPiece.equals(chessPiece.toUpperCase()) ||
                        !chessGame.chessTurn.getwhiteturn() && chessPiece.equals(chessPiece.toLowerCase()))) {
                createChessBoard.removehighlight(rowlist.get(0), columnlist.get(0));
                rowlist.clear();
                columnlist.clear();
                rowlist.add(row);
                columnlist.add(column);
                createChessBoard.highlightfield(row, column);
            } else {
                createChessBoard.removehighlight(rowlist.get(0), columnlist.get(0));
                chessGame.guiturn(rowlist.get(0), columnlist.get(0), rowlist.get(1), columnlist.get(1),
                                  chessGame.chessBoard);
                rowlist.clear();
                columnlist.clear();
                counter = 0;
            }
        }
    }

    /**
     * Saves the current game in a .fen-file.
     */
    public void savegame() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Game");
        fileChooser.setInitialFileName("SavedChessGame.fen");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("sFEN Files", "*.fen"));
        File selectedFile = fileChooser.showSaveDialog(null);
        if (selectedFile != null) {
            saveGameAsText(chessGame.chessBoard.createCurrentChessBoard(), selectedFile);
        }
    }

    /**
     * Loads the chosen .fen-file to play a saved game.
     */
    public void loadgame() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Load Game");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("sFEN Files", "*.fen"));
        File selectedFile = fileChooser.showOpenDialog(null);
        String[] input = new String[1];
        if (selectedFile != null) {
            input[0] = readText(selectedFile);
            chessGame.changeGame(input);
        }
    }

    private void saveGameAsText(String gamesetting, File file) {
        try {
            PrintWriter writer = new PrintWriter(file, "UTF-8");
            writer.println(gamesetting);
            writer.close();
        } catch (IOException e) {
            System.out.println("Error: Game couldn't be saved.");
        }
    }

    private String readText(File file) {
        String input = "";
        try {
            Scanner scanner = new Scanner(file);
            input = scanner.nextLine();
        } catch (IOException e) {
            System.out.println("Error: File couldn't be loaded.");
        }
        return input;
    }
}
