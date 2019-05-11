package chess.figures;

import chess.board.ChessBoard;
import chess.board.Field;

import java.util.ArrayList;

public class Knight extends Figure
{
    public Knight(int row, int column, boolean isOnBoard, FigureColor figureColor)
    {
        super(row, column, isOnBoard, figureColor, FigureType.Knight);
    }

    public Knight(Knight knight)
    {
        super(knight);
    }

    @Override
    public ArrayList<Field> getPossibleMoveFields(ChessBoard board)
    {
        ArrayList<Field> possibleMoveFields = new ArrayList<>();

        checkMove(board,possibleMoveFields,row + 2, column + 1);
        checkMove(board,possibleMoveFields,row + 2, column - 1);
        checkMove(board,possibleMoveFields,row - 2, column + 1);
        checkMove(board,possibleMoveFields,row - 2, column - 1);
        checkMove(board,possibleMoveFields,row + 1, column + 2);
        checkMove(board,possibleMoveFields,row + 1, column - 2);
        checkMove(board,possibleMoveFields,row - 1, column + 2);
        checkMove(board,possibleMoveFields,row - 1, column - 2);

        return  possibleMoveFields;
    }
}