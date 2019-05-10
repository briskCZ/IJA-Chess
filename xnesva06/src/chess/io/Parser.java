package chess.io;

import chess.game.Record;

public class Parser
{
    public boolean parseLine(Record loadedRecord, String line)
    {
        String[] split = line.split("\\s+");
        if (split.length != 3)
        {
            System.out.println("Parsing error: white and black move expected");
            return false;
        }
        if (!split[0].matches("\\d+\\."))
        {
            System.out.println("Parsing error: number of line expected");
            return false;
        }
        String whiteMove = split[1];
        String blackMove = split[2];
        System.out.println(whiteMove + " " + isValidField(whiteMove));
        return true;
    }

    private boolean isValidField(String field)
    {
        return field.charAt(0) >= 'a' && field.charAt(0) <= 'h' &&
                Integer.valueOf(Character.toString(field.charAt(1))) > 0  && Integer.parseInt(Character.toString(field.charAt(1))) < 9;
    }
}
