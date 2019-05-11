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

        boolean was_occupied_lu = false;
        boolean was_occupied_ld = false;
        boolean was_occupied_ru = false;
        boolean was_occupied_rd = false;

        for(int i = 1; i < ChessBoard.CHESS_BOARD_SIZE; i++){

            Field left_up = null;
            Field left_down = null;
            Field right_up = null;
            Field right_down = null;

            left_up = board.getField(row + i,column + i);
            left_down = board.getField(row - i,column + i);
            right_up = board.getField(row + i,column - i);
            right_down = board.getField(row - i,column - i);


            if(!was_occupied_lu && left_up != null){
                was_occupied_lu = checkMove(board,possibleMoveFields,left_up.getRow(),left_up.getColumn());
            }
            if(!was_occupied_ld && left_down != null){
                was_occupied_ld = checkMove(board,possibleMoveFields,left_down.getRow(),left_down.getColumn());
            }
            if(!was_occupied_ru && right_up != null){
                was_occupied_ru = checkMove(board,possibleMoveFields,right_up.getRow(),right_up.getColumn());
            }
            if(!was_occupied_rd && right_down != null){
                was_occupied_rd = checkMove(board,possibleMoveFields,right_down.getRow(),right_down.getColumn());
            }

        }
        return possibleMoveFields;
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
