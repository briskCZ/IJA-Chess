package chess.figures;

import chess.board.Field;
import chess.game.GameRecord;

public class King extends Figure
{

    public King(int row, int column, boolean isOnBoard, FigureColor figureColor)
    {
        super(row, column, isOnBoard, figureColor, FigureType.King);
    }
    public King(King king)
    {
        super(king);
    }

    @Override
    public Field[] getPossibleMoveFields()
    {
        return new Field[0];
    }
}
