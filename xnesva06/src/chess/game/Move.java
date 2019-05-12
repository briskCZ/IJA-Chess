package chess.game;

import chess.board.Field;

/**
 * @author Marek Nesvadba, Zdeněk Doležal (xnesva06, xdolez82)
 * <p>Abstraction of one half move (move of one figure color)
 */

public class Move
{
    public enum Tag
    {
        Kick,
        Check,
        CheckMate,
        Promotion
    }

    Field sourceField;
    Field destField;

    Field sourceFieldAfter;
    Field destFieldAfter;
    Tag[] tags;

    public Move(Field sourceField, Field destField)
    {
        this.sourceField = new Field(sourceField);
        this.destField = new Field(destField);
    }

    public void executeMove(Field sourceFieldAfter, Field destFieldAfter, Tag[] tags)
    {
        this.sourceFieldAfter = new Field(sourceFieldAfter);
        this.destFieldAfter = new Field(destFieldAfter);
        this.tags = tags;
    }

    public String toString()
    {
        String kick = "";
        String specialTags = "";
        for (int i = 0; i < tags.length; i++)
        {
            if (tags[i] == Tag.Kick)
            {
                kick = "x";
            }
            else
            {
                specialTags += specialTagsToString(tags[i]);
            }
        }
        return sourceField.getFigure().toString() + sourceField.toString() + kick + destField.toString() + specialTags;
    }

    private String specialTagsToString(Tag tag)
    {
        switch (tag)
        {
            case Promotion:
                return destFieldAfter.getFigure().toString();
            case Check:
                return "+";
            case CheckMate:
                return "#";
            default:
                return "";
        }
    }
}
