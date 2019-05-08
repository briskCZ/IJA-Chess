package chess.game;

import chess.board.Field;
import chess.figures.Figure;

public class Move
{
    Field sourceField;
    Field destField;

    Field sourceFieldAfter;
    Field destFieldAfter;

    public Move(Field sourceField, Field destField)
    {
        this.sourceField = new Field(sourceField);
        this.destField = new Field(destField);
    }

    public void executeMove(Field sourceFieldAfter, Field destFieldAfter)
    {
        this.sourceFieldAfter = new Field(sourceFieldAfter);
        this.destFieldAfter = new Field(destFieldAfter);
    }
}
