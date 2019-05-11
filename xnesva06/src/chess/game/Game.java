package chess.game;

import chess.board.ChessBoard;
import chess.board.Field;
import chess.figures.Figure;
import chess.figures.FigureColor;
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
    private FileHandler fileHandler;
    private int id;
    private FigureColor turnColor;

    public Game(int gameId)
    {
        System.out.println("Creating game: " + gameId);
        this.id = gameId;
        this.chessBoard = new ChessBoard();
        this.playerRecord = new Record();
        this.loadedRecord = new Record();
        this.replayHandler = new ReplayHandler(this.playerRecord, this.loadedRecord, this);
        this.fileHandler = new FileHandler();
        this.turnColor = FigureColor.White;
    }
    public boolean loadGame(File file)
    {
        boolean retval = fileHandler.loadRecord(file, loadedRecord);
        loadedRecord.resetIndex();
        return retval;
    }
    public boolean saveGame(File file)
    {
        return fileHandler.saveRecord(replayHandler.getCompleteRecord(), file);
    }
    public boolean isOnTurn(FigureColor color)
    {
        return color == turnColor;
    }
    public void move(Figure selectedFigure, Field destination, FigureType type)
    {
        Field figurePosition = chessBoard.getField(selectedFigure.getRow(), selectedFigure.getColumn());
        Move move = new Move(figurePosition, destination);
        ArrayList<Move.Tag> tags = new ArrayList<>();
        replayHandler.lockLoadedMovesIndex();
        playerRecord.addMove(move);
        if (destination.isOccupiedWithEnemyFig(selectedFigure))
        {
            tags.add(Move.Tag.Kick);
        }
        destination.setFigure(selectedFigure);
        figurePosition.removeFigure();
        move.executeMove(figurePosition, destination, tags.toArray(new Move.Tag[tags.size()]));
        changeTurn();

    }
    public ArrayList<Field> getPossibleMoves(Figure selectedFigure)
    {
        return selectedFigure.getPossibleMoveFields(chessBoard);
    }

    public void undoMove()
    {
        replayHandler.undoPlayerMove();
    }

    public void redoMove()
    {
        replayHandler.redoPlayerMove();
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
    protected void changeTurn()
    {
        if (turnColor == FigureColor.White)
        {
            turnColor = FigureColor.Black;
        }
        else if (turnColor == FigureColor.Black)
        {
            turnColor = FigureColor.White;
        }
    }
}
