package chess.game;

/**
 * @author Marek Nesvadba, Zdeněk Doležal (xnesva06, xdolez82)
 * <p>TODO;
 */

public class ReplayHandler
{
    private Record playerRecord;
    private Record loadedRecord;
    private Game game;
    private boolean playerMoved;
    private boolean wasUndo;
    private boolean returnedToLoaded;

    public ReplayHandler(Record playerRecord, Record loadedRecord, Game game)
    {
        this.playerRecord = playerRecord;
        this.loadedRecord = loadedRecord;
        this.game = game;
        this.playerMoved = false;
        this.wasUndo = false;
        this.returnedToLoaded = true;
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
            loadedRecord.resetMaxIndex();
            returnedToLoaded = true;
        }
    }

    protected void lockLoadedMovesIndex()
    {
        returnedToLoaded = false;
        playerMoved = true;
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
        Move move = loadedRecord.getNextMove();
        if (move != null)
        {
            game.chessBoard.setField(move.sourceFieldAfter);
            game.chessBoard.setField(move.destFieldAfter);
            playerRecord.resetMaxIndex();
            game.changeTurn();
            resetPlayerMoves();
            return true;
        } else if (playerMoved)
        {
            move = playerRecord.getNextMove();
            if (move != null)
            {
                game.chessBoard.setField(move.sourceFieldAfter);
                game.chessBoard.setField(move.destFieldAfter);
                game.changeTurn();
                resetPlayerMoves();
                return true;
            } else
            {
                return false;
            }
        } else
        {
            return false;
        }
    }

    public boolean playPreviousHalfMove()
    {
        Move move = playerRecord.getPrevMove();
        if (move != null && playerMoved)
        {
            game.chessBoard.setField(move.sourceField);
            game.chessBoard.setField(move.destField);
            playerRecord.resetMaxIndex();
            game.changeTurn();
            resetPlayerMoves();
            return true;
        } else
        {
            move = loadedRecord.getPrevMove();
            if (move != null)
            {
                game.chessBoard.setField(move.sourceField);
                game.chessBoard.setField(move.destField);
                game.changeTurn();
                resetPlayerMoves();
                return true;
            } else
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
        index++;
        while(getCompleteRecordIndex() != index)
        {
            int i = getCompleteRecordIndex();
            if (i < index)
            {
                playNextHalfMove();
                playNextHalfMove();
            }
            if (i > index)
            {
                playPreviousHalfMove();
                playPreviousHalfMove();
            }
        }
    }

    public int getCompleteRecordIndex()
    {
        return (int)Math.ceil(((double)loadedRecord.getIndex() + playerRecord.getIndex()) / 2.0);
    }

    public Record getCompleteRecord()
    {
        Record validLoadedRecord = loadedRecord.getValidPart();
        if (playerMoved && !returnedToLoaded)
        {
            Record.append(validLoadedRecord, playerRecord);
        }
        return validLoadedRecord;
    }

    private void resetPlayerMoves()
    {
        if (returnedToLoaded)
        {
            playerRecord.clear();
        }
    }

}
