package chess.figures;

import chess.board.Field;

public class Rook extends Figure
{
    public Rook(int row, int column, boolean isOnBoard, FigureColor figureColor)
    {
        super(row, column, isOnBoard, figureColor, FigureType.Rook);
    }

    public Rook(Rook rook){ super(rook); }

    @Override
    public Field[] getPossibleMoveFields()
    {
        return new Field[0];
    }
}
