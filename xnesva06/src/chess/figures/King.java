package chess.figures;

import chess.board.Field;
import chess.game.GameRecord;

public class King extends Figure
{

    public King(int column, int row, boolean isOnBoard, FigureColor figureColor)
    {
        super(column, row, isOnBoard, figureColor, FigureType.King);
    }
    public King(King king)
    {
        super(king);
    }

    @Override
    public boolean move(GameRecord gameRecord, Field currentField, Field destination)
    {
        return false;
    }

    @Override
    public Field[] getPossibleMoveFields()
    {
        return new Field[0];
    }
}
