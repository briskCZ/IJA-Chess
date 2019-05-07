package chess.game;

import chess.board.Field;

public class Move
{
    Field sourceField;
    Field destField;
    public Move(Field previousField, Field nextField)
    {
        this.sourceField = new Field(previousField);
        this.destField = new Field(nextField);
    }
}
