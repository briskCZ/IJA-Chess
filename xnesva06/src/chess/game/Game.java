package chess.game;

import chess.board.ChessBoard;
import chess.board.Field;
import chess.figures.Figure;
import chess.figures.FigureType;
import chess.io.FileHandler;

import java.io.File;
import java.util.ArrayList;

public class Game
{
    protected ChessBoard chessBoard;
    private Record playerRecord;
    private Record loadedRecord;
    private ReplayHandler replayHandler;
    private int id;

    public Game(int gameId)
    {
        System.out.println("Creating game: " + gameId);
        this.id = gameId;
        this.chessBoard = new ChessBoard();
        this.playerRecord = new Record();
        this.loadedRecord = new Record();
        this.replayHandler = new ReplayHandler(this.playerRecord, this.loadedRecord);
    }
    public boolean loadGame(File file)
    {
        loadedRecord = FileHandler.loadRecord(file);
        if (loadedRecord == null)
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    public boolean saveGame(File file)
    {
        return FileHandler.saveRecord(replayHandler.getCompleteRecord(), file);
    }

    public void move(Figure selectedFigure, Field destination, FigureType type)
    {
        Field figurePosition = chessBoard.getField(selectedFigure.getRow(), selectedFigure.getColumn());
        Move move = new Move(figurePosition, destination);
        ArrayList<Move.Tag> tags = new ArrayList<>();
        playerRecord.addMove(move);
        if (destination.isOccupiedWithEnemyFig(selectedFigure))
        {
            tags.add(Move.Tag.Kick);
        }
        destination.setFigure(selectedFigure);
        figurePosition.removeFigure();
        move.executeMove(figurePosition, destination, tags.toArray(new Move.Tag[tags.size()]));
    }
    public ArrayList<Field> getPossibleMoves(Figure selectedFigure)
    {
        return selectedFigure.getPossibleMoveFields(chessBoard);
    }

    public boolean undoMove()
    {
        return replayHandler.undoUserMove(chessBoard);
    }

    public boolean redoMove()
    {
        return replayHandler.redoUserMove(chessBoard);
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
