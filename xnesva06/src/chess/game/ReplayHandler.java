package chess.game;

import chess.board.Field;
import chess.figures.Figure;

public class ReplayHandler
{
    private Record playerMoves;
    private Record loadedMoves;
    private FileHandler fileHandler;

    public ReplayHandler(Record playerMoves, Record loadedMoves)
    {
        this.playerMoves = playerMoves;
        this.loadedMoves = loadedMoves;
        fileHandler = new FileHandler();
    }

    public void addUserMove()
    {

    }
    public void undoUserMove()
    {

    }
    public void redoUserMove()
    {

    }
    public void playAutomatically(int delay)
    {

    }
    public void loaded()
    {

    }
    public void playNextMove()
    {

    }
    public void playPreviousMove()
    {

    }
    public void restartPlayer()
    {

    }
    public void movePlayerTo()
    {

    }
    public Record getCompleteRecord()
    {
        return new Record();
    }
}
