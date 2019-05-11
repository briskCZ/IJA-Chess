package chess.board;

import chess.figures.Figure;

public class Field
{
    private int row;
    private int column;
    private Figure figure;

    public Field(int row, int column)
    {
        this.row = row;
        this.column = column;
        this.figure = null;
    }
    public Field(Field field)
    {
        this.row = field.row;
        this.column = field.column;
        this.figure = Figure.copyFigure(field.figure);
    }
    public Figure getFigure()
    {
        return this.figure;
    }
    public void setFigure(Figure figure)
    {
        this.figure = figure;
        if (figure != null)
        {
            figure.setPosition(row, column);
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
    public boolean isOccupiedWithEnemyFig(Figure fig)
    {
        return isOccupied() ? getFigure().getColor() != fig.getColor() : false;
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
        return convertRowToChar() + column + 1;
    }
    private String convertRowToChar()
    {
        return Integer.toString(this.row + 'a');
    }
}
