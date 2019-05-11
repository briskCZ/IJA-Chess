package chess.game;

import chess.board.ChessBoard;
import chess.io.FileHandler;

public class ReplayHandler
{
    private Record playerMoves;
    private Record loadedMoves;
    private ChessBoard board;

    public ReplayHandler(Record playerMoves, Record loadedMoves, ChessBoard board)
    {
        this.playerMoves = playerMoves;
        this.loadedMoves = loadedMoves;
        this.board = board;
        }
    protected boolean undoUserMove()
    {
        Move move = playerMoves.getPrevMove();
        if (move != null)
        {
            board.setField(move.sourceField);
            board.setField(move.destField);
            return true;
        }
        else
        {
            return false;
        }
    }
    protected boolean redoUserMove()
    {
        Move move = playerMoves.getNextMove();
        if (move != null)
        {
            board.setField(move.sourceFieldAfter);
            board.setField(move.destFieldAfter);
            return true;
        }
        else
        {
            return false;
        }
    }
    public void playAutomatically(int delay)
    {

    }
    public void stopAutomatically()
    {

    }
    public void loaded()
    {

    }
    //TODO combination with user moves
    public boolean playNextMove()
    {
        Move move = loadedMoves.getNextMove();
        if (move != null)
        {
            board.setField(move.sourceField);
            board.setField(move.destField);
            return true;
        }
        else
        {
            return false;
        }
    }
    public boolean playPreviousMove()
    {
        Move move = loadedMoves.getPrevMove();
        if (move != null)
        {
            board.setField(move.sourceFieldAfter);
            board.setField(move.destFieldAfter);
            return true;
        }
        else
        {
            return false;
        }
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
