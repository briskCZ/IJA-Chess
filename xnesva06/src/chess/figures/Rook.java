package chess.figures;

import chess.board.Field;
import chess.game.GameRecord;

public class Rook extends Figure
{
    public Rook(int column, int row, boolean isOnBoard, FigureColor figureColor)
    {
        super(column, row, isOnBoard, figureColor, FigureType.Rook);
    }

    public Rook(Rook rook){ super(rook); }


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
