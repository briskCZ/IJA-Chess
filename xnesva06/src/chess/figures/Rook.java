package chess.figures;

import chess.board.ChessBoard;
import chess.board.Field;

import java.util.ArrayList;

public class Rook extends Figure
{
    public Rook(int row, int column, boolean isOnBoard, FigureColor figureColor)
    {
        super(row, column, isOnBoard, figureColor, FigureType.Rook);
    }

    public Rook(Rook rook)
    {
        super(rook);
    }

    @Override
    public ArrayList<Field> getPossibleMoveFields(ChessBoard board)
    {
        ArrayList<Field> PossibleMoveFields = new ArrayList<>();
        for (int i = row + 1; i < ChessBoard.CHESS_BOARD_SIZE ; i++)
        {
            if (checkMove(board, PossibleMoveFields, i, column)) break;
        }
        for (int i = row - 1; i >= 0; i--)
        {
            if (checkMove(board, PossibleMoveFields, i, column)) break;
        }
        for (int i = column + 1; i < ChessBoard.CHESS_BOARD_SIZE; i++)
        {
            if (checkMove(board, PossibleMoveFields, row, i)) break;
        }
        for (int i = column - 1; i >= 0; i--)
        {
            if (checkMove(board, PossibleMoveFields, row, i)) break;
        }
        return PossibleMoveFields;
    }

    private boolean checkMove(ChessBoard board, ArrayList<Field> possibleArrayMoves, int row, int column)
    {
        Field f = board.getField(row, column);
        if (f.isOccupiedWithEnemyFig(this))
        {
            possibleArrayMoves.add(f);
            return true;
        }
        if (f.isOccupied())
        {
            return true;
        }
        possibleArrayMoves.add(f);
        return false;
    }
}
