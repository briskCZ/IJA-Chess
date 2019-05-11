package chess.game;

import chess.board.ChessBoard;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ReplayHandler
{
    private Record playerRecord;
    private Record loadedRecord;
    private Game game;
    private boolean playerMoved;
    private boolean wasUndo;
    private boolean playerControlEnabled;
    private int playerMoveIndex;

    public ReplayHandler(Record playerRecord, Record loadedRecord, Game game)
    {
        this.playerRecord = playerRecord;
        this.loadedRecord = loadedRecord;
        this.game = game;
        this.playerMoveIndex = 0;
        this.playerMoved = false;
        this.wasUndo = false;
        this.playerControlEnabled = false;
    }
    protected void undoPlayerMove()
    {
        Move move = playerRecord.getPrevMove();
        if (move != null)
        {
            game.chessBoard.setField(move.sourceField);
            game.chessBoard.setField(move.destField);
            wasUndo = true;
            game.changeTurn();
        }
        if (playerRecord.getIndex() == 0 && loadedRecord.getSize() > 0)
        {
            // Player moves undoed can use record
            loadedRecord.resetMaxIndex();
            playerMoved = false;
        }
    }
    protected void lockLoadedMovesIndex()
    {
        playerMoved = true;
        playerMoveIndex = loadedRecord.getIndex();
        playerControlEnabled = true;
        loadedRecord.setMaxIndex(loadedRecord.getIndex());
        if (wasUndo)
        {
            playerRecord.setMaxIndex(playerRecord.getIndex());
            wasUndo = false;
        }
    }
    protected void redoPlayerMove()
    {
        Move move = playerRecord.getNextMove();
        if (move != null)
        {
            if (wasUndo)
            {
                loadedRecord.setMaxIndex(loadedRecord.getIndex());
            }
            game.chessBoard.setField(move.sourceFieldAfter);
            game.chessBoard.setField(move.destFieldAfter);
            wasUndo = false;
            game.changeTurn();
        }
    }
    public void playAutomatically(int delay)
    {

    }
    public void stopAutomatically()
    {

    }
    public boolean playNextHalfMove()
    {
        if (playerMoveIndex == loadedRecord.getIndex())
        {
            playerControlEnabled = true;
        }
        else
        {
            playerControlEnabled = false;
        }
        Move move = loadedRecord.getNextMove();
        if (move != null)
        {
            game.chessBoard.setField(move.sourceFieldAfter);
            game.chessBoard.setField(move.destFieldAfter);
            playerRecord.resetMaxIndex();
            playerMoveIndex = loadedRecord.getIndex();
            game.changeTurn();
            return true;
        }
        else if (playerMoved)
        {
            move = playerRecord.getNextMove();
            if (move != null)
            {
                game.chessBoard.setField(move.sourceFieldAfter);
                game.chessBoard.setField(move.destFieldAfter);
                game.changeTurn();
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
    public boolean playPreviousHalfMove()
    {
        if (playerMoveIndex == loadedRecord.getIndex())
        {
            playerControlEnabled = true;
        }
        else
        {
            playerControlEnabled = false;
        }
        Move move = playerRecord.getPrevMove();
        if (move != null && playerMoved)
        {
            game.chessBoard.setField(move.sourceField);
            game.chessBoard.setField(move.destField);
            game.changeTurn();
            return true;
        }
        else
        {
            move = loadedRecord.getPrevMove();
            if (move != null)
            {
                game.chessBoard.setField(move.sourceField);
                game.chessBoard.setField(move.destField);
                game.changeTurn();
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
    public int getCompleteRecordIndex()
    {
        int index = (loadedRecord.getIndex() + playerRecord.getIndex())/2 - 1;
        int size = (loadedRecord.getSize() + playerRecord.getSize());
        if (size % 2 == 1)
        {
            index++;
        }
        return index + 1;
    }
    public boolean isUserControlEnabled()
    {
        return playerControlEnabled;
    }
    public Record getCompleteRecord()
    {
        Record validLoadedRecord = loadedRecord.getValidPart();
        if (playerMoved)
        {
            Record.append(validLoadedRecord, playerRecord);
        }
        return validLoadedRecord;
    }


}
