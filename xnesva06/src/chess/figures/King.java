package chess.figures;

import chess.board.Field;

public class King extends Figure
{

    public King(int column, int row, boolean isOnBoard, Color color)
    {
        super(column, row, isOnBoard, color, FigureType.King);
    }
    public King(King king)
    {
        super(king);
    }

    @Override
    boolean move(int targetCol, int targetRow) {
        return false;
    }

    @Override
    Field[] possibleMoveFields() {
        return new Field[0];
    }
}
