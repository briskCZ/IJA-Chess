package chess.figures;

import chess.board.Field;
import chess.game.GameRecord;

public class Bishop extends Figure
{
    public Bishop(int row, int column, boolean isOnBoard, FigureColor figureColor)
    {
        super(row, column, isOnBoard, figureColor, FigureType.Bishop);
    }

    public Bishop(Bishop bishop) { super(bishop); }

    @Override
    public Field[] getPossibleMoveFields()
    {
        return new Field[0];
    }
}
