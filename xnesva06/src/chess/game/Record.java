package chess.game;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Record
{
    private ArrayList<Move> moves;
    private int moveArrayIndex;

    public Record()
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
        if (moveArrayIndex-1 >= 0)
        {
            return moves.get(--moveArrayIndex);
        }
        else
        {
            return null;
        }
    }
    public ArrayList<Move> getMoves()
    {
        return this.moves;
    }

}
