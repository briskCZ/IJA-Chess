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
        ArrayList<Field> possibleMoveFields = new ArrayList<>();

        checkMove(board,possibleMoveFields,row + 1, column);
        checkMove(board,possibleMoveFields,row - 1, column);
        checkMove(board,possibleMoveFields,row , column + 1);
        checkMove(board,possibleMoveFields,row , column - 1);
        checkMove(board,possibleMoveFields,row + 1, column + 1);
        checkMove(board,possibleMoveFields,row - 1, column - 1);
        checkMove(board,possibleMoveFields,row - 1, column + 1);
        checkMove(board,possibleMoveFields,row + 1, column - 1);


        return possibleMoveFields;
    }
}
