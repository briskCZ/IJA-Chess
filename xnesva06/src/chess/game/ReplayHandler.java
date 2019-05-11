package chess.game;

import chess.board.ChessBoard;
import chess.io.FileHandler;

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
    protected boolean undoUserMove(ChessBoard board)
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
    protected boolean redoUserMove(ChessBoard board)
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
