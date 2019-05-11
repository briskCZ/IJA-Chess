package chess.figures;

import chess.board.ChessBoard;
import chess.board.Field;

import java.util.ArrayList;

public class Queen extends Figure
{
    public Queen(int row, int column, boolean isOnBoard, FigureColor figureColor)
    {
        super(row, column, isOnBoard, figureColor, FigureType.Queen);
    }

    public Queen(Queen queen)
    {
        super(queen);
    }

    @Override
    public ArrayList<Field> getPossibleMoveFields(ChessBoard board)
    {
        ArrayList<Field> possibleMoveFields = new ArrayList<>();

        boolean was_occupied_lu = false;
        boolean was_occupied_ld = false;
        boolean was_occupied_ru = false;
        boolean was_occupied_rd = false;
        boolean was_occupied_u = false;
        boolean was_occupied_d = false;
        boolean was_occupied_l = false;
        boolean was_occupied_r = false;

        for(int i = 1; i < ChessBoard.CHESS_BOARD_SIZE; i++){

            Field left_up = board.getField(row + i,column + i);
            Field left_down = board.getField(row - i,column + i);
            Field right_up = board.getField(row + i,column - i);
            Field right_down = board.getField(row - i,column - i);
            Field up = board.getField(row + i,column);
            Field down = board.getField(row - i,column);
            Field left = board.getField(row,column + i);
            Field right = board.getField(row,column - i);

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
            if(!was_occupied_u && up != null){
                was_occupied_u = checkMove(board,possibleMoveFields,up.getRow(),up.getColumn());
            }
            if(!was_occupied_d && down != null){
                was_occupied_d = checkMove(board,possibleMoveFields,down.getRow(),down.getColumn());
            }
            if(!was_occupied_l && left != null){
                was_occupied_l = checkMove(board,possibleMoveFields,left.getRow(),left.getColumn());
            }
            if(!was_occupied_r && right != null){
                was_occupied_r = checkMove(board,possibleMoveFields,right.getRow(),right.getColumn());
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
