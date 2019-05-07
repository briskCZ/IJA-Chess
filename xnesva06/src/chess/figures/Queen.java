package chess.figures;

import chess.board.Field;
import chess.game.GameRecord;

public class Queen extends Figure
{
    public Queen(int column, int row, boolean isOnBoard, FigureColor figureColor)
    {
        super(column, row, isOnBoard, figureColor, FigureType.Queen);
    }

    public Queen(Queen queen) { super(queen);}


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
