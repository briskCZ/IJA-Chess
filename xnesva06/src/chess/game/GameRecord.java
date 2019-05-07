package chess.game;

import java.util.ArrayList;

public class GameRecord
{
    private ArrayList<Move> moves;
    private int moveArrayIndex;

    public GameRecord()
    {
        this.moves = new ArrayList<>();
        this.moveArrayIndex = 0;
    }
    public void addMove(Move move)
    {
        moves.add(moveArrayIndex++, move);
    }

    public Move getNextMove()
    {
        if (moveArrayIndex < moves.size())
        {
            return moves.get(moveArrayIndex++);
        }
        else
        {
            return null;
        }
    }
    public Move getPrevMove()
    {
        if (--moveArrayIndex >= 0)
        {
            return moves.get(moveArrayIndex);
        }
        else
        {
            return null;
        }
    }

}
