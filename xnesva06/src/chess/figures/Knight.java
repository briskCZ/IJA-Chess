package chess.figures;

import chess.board.Field;
import chess.game.GameRecord;

public class Knight extends Figure
{
    public Knight(int column, int row, boolean isOnBoard, FigureColor figureColor)
    {
        super(column, row, isOnBoard, figureColor, FigureType.Knight);
    }

    public Knight(Knight knight){ super(knight); }

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