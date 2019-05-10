package chess.game;

import chess.board.Field;

public class Move
{
    public enum Tag{
        Kick,
        Check,
        Mate,
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
}
