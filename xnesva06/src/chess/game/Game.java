package chess.game;

import chess.board.ChessBoard;
import chess.board.Field;
import chess.figures.Figure;

import java.io.File;
import java.util.ArrayList;

public class Game
{
    private ChessBoard chessBoard;
    private Record playerRecord;
    private Record loadedRecord;
    private ReplayHandler replayHandler;
    private int id;

    public Game(int gameId)
    {
        this.id = gameId;
        this.chessBoard = new ChessBoard();
        this.playerRecord = new Record();
        this.loadedRecord = new Record();
        this.replayHandler = new ReplayHandler(this.playerRecord, this.loadedRecord);
    }
    public boolean loadGame(File file)
    {
        loadedRecord = FileHandler.loadRecord(file);
        return false;
    }
    public boolean saveGame(File file)
    {
        return FileHandler.saveRecord(replayHandler.getCompleteRecord(), file);
    }

    public void move(Figure selectedFigure, Field destination)
    {
        Field currentField = chessBoard.getField(selectedFigure.getRow(), selectedFigure.getColumn());
        Move move = new Move(currentField, destination);
        playerRecord.addMove(move);
        destination.setFigure(selectedFigure);
        currentField.removeFigure();
    }
    public ArrayList<Field> getPossibleMoves(Figure selectedFigure)
    {
        return selectedFigure.getPossibleMoveFields(chessBoard);
    }

    public boolean undoMove()
    {
        Move move = playerRecord.getPrevMove();
        if (move != null)
        {
            Field sourceField = new Field(move.sourceField);
            Field destField = new Field(move.destField);
            Figure sourceFigure = sourceField.getFigure();
            Figure destFigure = destField.getFigure();
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
        Move move = playerRecord.getNextMove();
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
    public int getGameId()
    {
        return this.id;
    }
    public Field getBoardField(int row, int column)
    {
        return chessBoard.getField(row, column);
    }
    public ReplayHandler getReplayHandler()
    {
        return replayHandler;
    }
}
