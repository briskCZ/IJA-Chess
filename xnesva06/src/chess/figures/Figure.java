package chess.figures;

import chess.board.Field;
import chess.game.GameRecord;
import chess.game.Move;

public abstract class Figure
{
    private int column;
    private int row;
    private boolean isOnBoard;
    private FigureColor figureColor;
    private FigureType type;

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
                return new King((King)fig);
            case Pawn:
                return new Pawn((Pawn)fig);
            case Bishop:
                return new Bishop((Bishop)fig);
            case Knight:
                return new Knight((Knight)fig);
            case Rook:
                return new Rook((Rook)fig);
            case Queen:
                return new Queen((Queen)fig);
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
        }
        else
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
    public FigureType getType(){
        return this.type;
    }
    public FigureColor getColor(){
        return this.figureColor;
    }
    public abstract boolean move(GameRecord gameRecord, Field currentField, Field destination);
    public abstract Field[] getPossibleMoveFields();

}
