package chess.figures;

import chess.board.Field;
import chess.game.GameRecord;

public class Bishop extends Figure
{
    public Bishop(int column, int row, boolean isOnBoard, FigureColor figureColor)
    {
        super(column, row, isOnBoard, figureColor, FigureType.Bishop);
    }

    public Bishop(Bishop bishop) { super(bishop); }

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
