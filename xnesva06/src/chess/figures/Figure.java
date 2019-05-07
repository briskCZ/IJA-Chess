package chess.figures;

import chess.board.Field;

public abstract class Figure
{
    private int column;
    private int row;
    private boolean isOnBoard;
    private Color color;
    private FigureType type;

    public Figure(int column, int row, boolean isOnBoard, Color color, FigureType type)
    {
        this.row = row;
        this.column = column;
        this.isOnBoard = isOnBoard;
        this.color = color;
        this.type = type;
    }
    public Figure(Figure fig)
    {
        this.row = fig.row;
        this.column = fig.column;
        this.isOnBoard = fig.isOnBoard;
        this.color = fig.color;
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
    public int getCol()
    {
        return this.column;
    }
    public void setPosition(int column, int row)
    {
        this.column = column;
        this.row = row;
    }
    abstract boolean move(int targetCol, int targetRow);
    abstract Field[] possibleMoveFields();
}
