package chess.figures;

import chess.board.Field;
import chess.game.GameRecord;

public class Knight extends Figure
{
    public Knight(int row, int column, boolean isOnBoard, FigureColor figureColor)
    {
        super(row, column, isOnBoard, figureColor, FigureType.Knight);
    }

    public Knight(Knight knight){ super(knight); }

    @Override
    public Field[] getPossibleMoveFields()
    {
        return new Field[0];
    }
}