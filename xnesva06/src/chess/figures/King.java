package chess.figures;

import chess.board.ChessBoard;
import chess.board.Field;

import java.util.ArrayList;

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
    public ArrayList<Field> getPossibleMoveFields(ChessBoard board)
    {
        return new ArrayList<>();
    }
}
