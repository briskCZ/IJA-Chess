package chess.game;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Record
{
    private ArrayList<Move> moves;
    private int moveArrayIndex;
    private int maxIndex;

    public Record()
    {
        this.moves = new ArrayList<>();
        this.moveArrayIndex = 0;
        this.maxIndex = -1;
    }
    public void addMove(Move move)
    {
        moves.add(moveArrayIndex++, move);
    }

    public Move getNextMove()
    {
        int size = moves.size();
        if (maxIndex != -1)
        {
            size = maxIndex;
        }
        if (moveArrayIndex < size)
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
    public Record getValidPart()
    {
        Record record = new Record();
        for (int i = 0; (maxIndex == -1) ? i < moves.size() : i < maxIndex; i++)
        {
            record.addMove(moves.get(i));
        }
        return record;
    }
    public int getIndex()
    {
        return this.moveArrayIndex;
    }
    public boolean setMaxIndex(int val)
    {
        if (val < moves.size())
        {
            this.maxIndex = val;
            return true;
        }
        else
        {
            return false;
        }
    }
    public void resetMaxIndex()
    {
        this.maxIndex = -1;
    }
    public void resetIndex()
    {
        this.moveArrayIndex = 0;
    }
    public int getSize()
    {
        return this.moves.size();
    }
    public static void append(Record record, Record append)
    {
        for (Move m : append.moves)
        {
            record.addMove(m);
        }
    }
    public String[] toStringArray()
    {
        String[] result = new String[moves.size() / 2];
        int arrIndex = 0;
        for (int i = 0; i + 1 < moves.size(); i += 2)
        {
            Move white = moves.get(i);
            Move black = moves.get(i+1);
            result[arrIndex] = (arrIndex+1) + ". " + white.toString() + " " + black.toString();
            arrIndex++;
        }
        return result;
    }
}
