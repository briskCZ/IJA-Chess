package chess.game;

import chess.board.ChessBoard;
import chess.board.Field;
import chess.figures.Figure;

import java.io.File;
import java.nio.file.Path;

public class Game
{
    private ChessBoard chessBoard;
    private GameRecord gameRecord;
    private int id;

    public Game(int gameId)
    {
        this.id = gameId;
        this.chessBoard = new ChessBoard();
        this.gameRecord = new GameRecord();
    }
    public boolean loadGame(File file)
    {
        GameFileLoader gameFileLoader = new GameFileLoader(file);
        this.gameRecord = gameFileLoader.loadGame();

        return false;
    }
    public int getGameId()
    {
        return this.id;
    }
    public Field getBoardField(int column, int row)
    {
        return chessBoard.getField(column, row);
    }
    public void move(Figure selectedFigure, Field destination)
    {
        selectedFigure.move(this.gameRecord, this.chessBoard.getField(selectedFigure.getColumn(), selectedFigure.getRow()), destination);
    }

    public void printGame()
    {
        System.out.println(this.chessBoard);
    }
}
