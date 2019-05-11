package chess.game;

import chess.board.ChessBoard;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ReplayHandler
{
    private Record playerRecord;
    private Record loadedRecord;
    private ChessBoard board;
    private boolean playerMoved;

    public ReplayHandler(Record playerRecord, Record loadedRecord, ChessBoard board)
    {
        this.playerRecord = playerRecord;
        this.loadedRecord = loadedRecord;
        this.board = board;
        this.playerMoved = false;
    }
    protected boolean undoPlayerMove()
    {
        if (playerRecord.getSize() == 0)
        {
            loadedRecord.resetMaxIndex();
            playerMoved = false;
        }
        Move move = playerRecord.getPrevMove();
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
    protected void lockLoadedMovesIndex()
    {
        playerMoved = true;
        loadedRecord.setMaxIndex(loadedRecord.getIndex());
    }
    protected boolean redoPlayerMove()
    {
        Move move = playerRecord.getNextMove();
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
    //TODO combination with user moves
    public boolean playNextMove()
    {
        Move move = loadedRecord.getNextMove();
        if (move != null)
        {
            board.setField(move.sourceFieldAfter);
            board.setField(move.destFieldAfter);
            return true;
        }
        else if (playerMoved)
        {
            move = playerRecord.getNextMove();
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
        else
        {
            return false;
        }
    }
    public boolean playPreviousMove()
    {
        Move move = playerRecord.getPrevMove();
        if (move != null && playerMoved)
        {
            board.setField(move.sourceField);
            board.setField(move.destField);
            return true;
        }
        else
        {
            move = loadedRecord.getPrevMove();
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
    }
    public void restartPlayer()
    {

    }
    public void movePlayerTo(int index)
    {

    }
    public Record getCompleteRecord()
    {
        Record validLoadedRecord = loadedRecord.getValidPart();
        if (playerMoved)
        {
            Record.append(validLoadedRecord, playerRecord);
        }
        System.out.println("Returning: " + Arrays.toString(validLoadedRecord.toStringArray()));
        return validLoadedRecord;
    }


}
