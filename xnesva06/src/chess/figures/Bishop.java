package chess.figures;

import chess.board.ChessBoard;
import chess.board.Field;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Bishop extends Figure
{
    public Bishop(int row, int column, boolean isOnBoard, FigureColor figureColor)
    {
        super(row, column, isOnBoard, figureColor, FigureType.Bishop);
    }

    public Bishop(Bishop bishop)
    {
        super(bishop);
    }

    @Override
    public ArrayList<Field> getPossibleMoveFields(ChessBoard board)
    {
        ArrayList<Field> possibleMoveFields = new ArrayList<>();

        return possibleMoveFields;
    }
}
