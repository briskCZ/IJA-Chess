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
                if (row + 1 < ChessBoard.CHESS_BOARD_SIZE && column - 1 > 0 && column + 1 < ChessBoard.CHESS_BOARD_SIZE)
                {
                    if (!board.getField(row + 1, column).isOccupied())
                    {
                        possibleMoveFields.add(board.getField(row + 1, column));
                    }

                    Field fieldUpRight = board.getField(row + 1, column + 1);
                    if (fieldUpRight.isOccupiedWithEnemyFig(this))
                    {
                        possibleMoveFields.add(fieldUpRight);
                    }

                    Field fieldUpLeft = board.getField(row + 1, column - 1);
                    if (fieldUpLeft.isOccupiedWithEnemyFig(this))
                    {
                        possibleMoveFields.add(fieldUpLeft);
                    }
                }

            }
        }
        else
        {
            if (row == STARTING_ROW_BLACK)
            {
                Field startPlusOne = board.getField(STARTING_ROW_WHITE - 1, column);
                Field startPlusTwo = board.getField(STARTING_ROW_WHITE - 2, column);

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
                if (row - 1 > 0 && column - 1 > 0 && column + 1 < ChessBoard.CHESS_BOARD_SIZE)
                {
                    if (!board.getField(row - 1, column).isOccupied())
                    {
                        possibleMoveFields.add(board.getField(row - 1, column));
                    }

                    Field fieldUpRight = board.getField(row - 1, column + 1);
                    if (fieldUpRight.isOccupiedWithEnemyFig(this))
                    {
                        possibleMoveFields.add(fieldUpRight);
                    }

                    Field fieldUpLeft = board.getField(row - 1, column - 1);
                    if (fieldUpLeft.isOccupiedWithEnemyFig(this))
                    {
                        possibleMoveFields.add(fieldUpLeft);
                    }
                }

            }
        }
        return possibleMoveFields;
    }
}
