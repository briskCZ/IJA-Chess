package chess.figures;

import chess.board.Field;

public class Pawn extends Figure {

    public Pawn(int row, int column, boolean isOnBoard, FigureColor figureColor)
    {
        super(row, column, isOnBoard, figureColor, FigureType.Pawn);
    }

    public Pawn(Pawn pawn){ super(pawn); }

    @Override
    public Field[] getPossibleMoveFields()
    {
        return new Field[0];
    }
}
