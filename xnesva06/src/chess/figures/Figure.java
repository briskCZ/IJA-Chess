package chess.figures;

import chess.board.Field;
import chess.game.GameRecord;

public abstract class Figure
{
    private int column;
    private int row;
    private boolean isOnBoard;
    private FigureColor figureColor;
    private FigureType type;

    public Figure(int column, int row, boolean isOnBoard, FigureColor figureColor, FigureType type)
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
    public void setPosition(int column, int row)
    {
        this.column = column;
        this.row = row;
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
