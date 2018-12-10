package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

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
                    this.createChessBoard.insertChessPiece(row, column,
                                                           model.ChessPiece.getBez(chessGame.chessBoard.getChessPiece(row,
                                                                                                                      column)));
                }
            }
        }
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

    public void mouseclick(int row, int column) {
        String chessPiece = ChessPiece.getBez(chessGame.chessBoard.getChessPiece(row, column));
        if (counter == 0) {
            if (chessGame.chessBoard.hasChessPiece(row, column) && (
                chessGame.chessTurn.getwhiteturn() && chessPiece.equals(chessPiece.toUpperCase()) ||
                !chessGame.chessTurn.getwhiteturn() && chessPiece.equals(chessPiece.toLowerCase()))) {
                counter++;
                rowlist.add(row);
                columnlist.add(column);
            }
        } else {
            rowlist.add(row);
            columnlist.add(column);
            if (rowlist.get(0).equals(rowlist.get(1)) && columnlist.get(0).equals(columnlist.get(1))) {
                rowlist.clear();
                columnlist.clear();
                counter = 0;
            } else {
                chessGame.guiturn(rowlist.get(0), columnlist.get(0), rowlist.get(1), columnlist.get(1), chessGame.chessBoard);
                rowlist.clear();
                columnlist.clear();
                counter = 0;
            }
        }


    }
}
