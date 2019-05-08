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
        gameRecord = gameFileLoader.loadGame();

        return false;
    }
    public int getGameId()
    {
        return this.id;
    }
    public Field getBoardField(int row, int column)
    {
        return chessBoard.getField(row, column);
    }
    public void move(Figure selectedFigure, Field destination)
    {
        Field currentField = chessBoard.getField(selectedFigure.getRow(), selectedFigure.getColumn());
        destination.setFigure(selectedFigure);
        selectedFigure.setPosition(destination.getRow(), destination.getColumn());
        currentField.removeFigure();
        Move move = new Move(currentField, destination);
        gameRecord.addMove(move);


    }

    public boolean undoMove()
    {
        Move move = gameRecord.getPrevMove();
        if (move != null)
        {
            Field sourceField = new Field(move.sourceField);
            Field destField = new Field(move.destField);
            Figure sourceFigure = sourceField.getFigure();
            if (sourceFigure != null)
            {
                sourceFigure.setPosition(destField.getRow(), destField.getColumn());
            }
            Figure destFigure = destField.getFigure();
            if (destFigure != null)
            {
                destFigure.setPosition(sourceField.getRow(), sourceField.getColumn());
            }
            sourceField.setFigure(destFigure);
            destField.setFigure(sourceFigure);

            chessBoard.setField(sourceField);
            chessBoard.setField(destField);

            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean redoMove()
    {
        Move move = gameRecord.getNextMove();
        if (move != null)
        {
            chessBoard.setField(new Field(move.sourceField));
            chessBoard.setField(new Field(move.destField));

            return true;
        }
        else
        {
            return false;
        }
    }
    public void printGame()
    {
        System.out.println(this.chessBoard);
    }
}
