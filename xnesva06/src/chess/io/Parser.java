package chess.io;

import chess.figures.Figure;
import chess.figures.FigureType;
import chess.game.Move;
import chess.game.Record;

public class Parser
{
    static int prevLineNum = 0;

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
            System.out.println("Parsing error: be number");
            return false;
        }
        else
        {
            int lineNum = Integer.parseInt(split[0].split("\\.")[0]);
            if (prevLineNum + 1 != lineNum)
            {
                System.out.println("Parsing error: numbers in first column should be ordered");
                return false;
            }
            prevLineNum = lineNum;
        }
        String whiteMove = split[1];
        //Long notation

        String blackMove = split[2];
        System.out.println(whiteMove + " " + isValidField(whiteMove));
        return true;
    }

    private Move.Tag[] parseSpecialSymbols(String move)
    {
        // d x # +
        return null;
    }
    private FigureType parseCaptureFigure(String fig)
    {
        switch(fig)
        {
            case "K":
                return FigureType.King;
            case "D":
                return FigureType.Queen;
            case "V":
                return FigureType.Rook;
            case "S":
                return FigureType.Bishop;
            case "J":
                return FigureType.Knight;
            case "p":
                return FigureType.Pawn;
            default:
                return FigureType.Pawn;
        }
    }

    private boolean isValidField(String field)
    {
        return field.charAt(0) >= 'a' && field.charAt(0) <= 'h' &&
                Integer.valueOf(Character.toString(field.charAt(1))) > 0  &&
                Integer.parseInt(Character.toString(field.charAt(1))) < 9;
    }
}
