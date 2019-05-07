package chess.figures;

import chess.board.Field;

public class Queen extends Figure
{
    public Queen(int row, int column, boolean isOnBoard, FigureColor figureColor)
    {
        super(row, column, isOnBoard, figureColor, FigureType.Queen);
    }

    public Queen(Queen queen) { super(queen);}

    @Override
    public Field[] getPossibleMoveFields()
    {
        return new Field[0];
    }
}
