package chess.game;

import chess.board.Field;
import chess.figures.Figure;

public class Move
{
    Field sourceField;
    Field destField;
    Figure sourceFigure;
    Figure destFigure;
    public Move(Field previousField, Field nextField)
    {
        this.sourceField = new Field(previousField);
        this.destField = new Field(nextField);
    }
}
