package chess.figures;

import chess.board.ChessBoard;
import chess.board.Field;

import java.util.ArrayList;

public abstract class Figure
{
    protected int column;
    protected int row;
    protected boolean isOnBoard;
    protected FigureColor figureColor;
    protected FigureType type;

    public Figure(int row, int column, boolean isOnBoard, FigureColor figureColor, FigureType type)
    {
        this.row = row;
        this.column = column;
        this.isOnBoard = isOnBoard;
        this.figureColor = figureColor;
        this.type = type;
    }

    public Figure(Figure fig)
    {
        this.row = fig.row;
        this.column = fig.column;
        this.isOnBoard = fig.isOnBoard;
        this.figureColor = fig.figureColor;
        this.type = fig.type;
    }

    public static Figure copyFigure(Figure fig)
    {
        if (fig == null) return null;
        switch (fig.type)
        {
            case King:
                return new King((King) fig);
            case Pawn:
                return new Pawn((Pawn) fig);
            case Bishop:
                return new Bishop((Bishop) fig);
            case Knight:
                return new Knight((Knight) fig);
            case Rook:
                return new Rook((Rook) fig);
            case Queen:
                return new Queen((Queen) fig);
            default:
                return null;
        }
    }

    public boolean isOnBoard()
    {
        return isOnBoard;
    }

    public int getRow()
    {
        return this.row;
    }

    public int getColumn()
    {
        return this.column;
    }

    public void setPosition(int row, int column)
    {
        this.row = row;
        this.column = column;
    }

    public String toString()
    {
        if (this.figureColor == FigureColor.White)
        {
            switch (this.type)
            {
                case King:
                    return "♔";
                case Queen:
                    return "♕";
                case Rook:
                    return "♖";
                case Bishop:
                    return "♗";
                case Knight:
                    return "♘";
                case Pawn:
                    return "♙";
                default:
                    return "";
            }
        } else
        {
            switch (this.type)
            {
                case King:
                    return "♚";
                case Queen:
                    return "♛";
                case Rook:
                    return "♜";
                case Bishop:
                    return "♝";
                case Knight:
                    return "♞";
                case Pawn:
                    return "♟";
                default:
                    return "";
            }
        }
    }

    public FigureType getType()
    {
        return this.type;
    }

    public FigureColor getColor()
    {
        return this.figureColor;
    }

    public abstract ArrayList<Field> getPossibleMoveFields(ChessBoard board);

    protected boolean checkMove(ChessBoard board, ArrayList<Field> possibleArrayMoves, int row, int column)
    {
        Field f = board.getField(row, column);
        if(f != null){
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
        return false;
    }
}
