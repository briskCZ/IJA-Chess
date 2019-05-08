package chess.figures;

import chess.board.ChessBoard;
import chess.board.Field;

import java.util.ArrayList;

public class Pawn extends Figure
{

    public Pawn(int row, int column, boolean isOnBoard, FigureColor figureColor)
    {
        super(row, column, isOnBoard, figureColor, FigureType.Pawn);
    }

    public Pawn(Pawn pawn)
    {
        super(pawn);
    }

    @Override
    public ArrayList<Field> getPossibleMoveFields(ChessBoard board)
    {
        final int STARTING_ROW_WHITE = 1;
        final int STARTING_ROW_BLACK = 6;

        ArrayList<Field> possibleMoveFields = new ArrayList<>();
        if (figureColor == FigureColor.White)
        {
            if (row == STARTING_ROW_WHITE)
            {
                Field startPlusOne = board.getField(STARTING_ROW_WHITE + 1, column);
                Field startPlusTwo = board.getField(STARTING_ROW_WHITE + 2, column);

                if (!startPlusOne.isOccupied())
                {
                    possibleMoveFields.add(startPlusOne);
                }
                if (!startPlusTwo.isOccupied())
                {
                    possibleMoveFields.add(startPlusTwo);
                }
            }
            else
            {
                Field upRight = board.getField(row + 1, column + 1);
                Field upLeft = board.getField(row + 1, column - 1);
                Field up = board.getField(row + 1, column);
                checkNextRow(possibleMoveFields, upRight, upLeft, up);
            }
        }
        else
        {
            if (row == STARTING_ROW_BLACK)
            {
                Field startPlusOne = board.getField(STARTING_ROW_BLACK - 1, column);
                Field startPlusTwo = board.getField(STARTING_ROW_BLACK - 2, column);

                if (!startPlusOne.isOccupied())
                {
                    possibleMoveFields.add(startPlusOne);
                }
                if (!startPlusTwo.isOccupied())
                {
                    possibleMoveFields.add(startPlusTwo);
                }
            }
            else
            {
                Field downRight = board.getField(row - 1, column + 1);
                Field downLeft = board.getField(row - 1, column - 1);
                Field down = board.getField(row - 1, column);
                checkNextRow(possibleMoveFields, downRight, downLeft, down);
            }
        }
        return possibleMoveFields;
    }

    private void checkNextRow(ArrayList<Field> possibleMoveFields, Field right, Field left, Field middle)
    {
        if (middle != null && !middle.isOccupied())
        {
            if (left != null && right != null){
                if (!left.isOccupiedWithEnemyFig(this) && !right.isOccupiedWithEnemyFig(this))
                {
                    possibleMoveFields.add(middle);
                }
            }
            else if (left == null && right != null && !right.isOccupiedWithEnemyFig(this))
            {
                possibleMoveFields.add(middle);
            }
            else if (right == null && left != null && !left.isOccupiedWithEnemyFig(this))
            {
                possibleMoveFields.add(middle);
            }
        }
        if (right != null && right.isOccupiedWithEnemyFig(this))
        {
            possibleMoveFields.add(right);
        }
        if (left != null && left.isOccupiedWithEnemyFig(this))
        {
            possibleMoveFields.add(left);
        }
    }
}
