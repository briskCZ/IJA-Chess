package chess.board;

import chess.figures.Figure;

public class Field
{
    private int row;
    private int column;
    private Figure figure;

    public Field(int column, int row)
    {
        this.row = row;
        this.column = column;
        this.figure = null;
    }

    public Figure getFigure()
    {
        return this.figure;
    }
    public boolean setFigure(Figure figure)
    {
        if (isOccupied())
        {
            return false;
        }
        else
        {
            this.figure = figure;
            return true;
        }
    }
    public void removeFigure()
    {
        this.figure = null;
    }

    public boolean isOccupied()
    {
        return figure != null;
    }

    public int getColumn()
    {
        return column;
    }

    public int getRow()
    {
        return row;
    }

    public String toString()
    {
        return "[" + this.column + ":" + this.row + "]" + (this.figure == null ? "" : this.figure.toString());
    }
}
