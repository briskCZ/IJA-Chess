package chess.board;

import chess.figures.Figure;

public class Field
{
    int row;
    int column;
    Figure figure;

    public Field(int row, int column)
    {

    }

    public Figure getFigure()
    {
        return figure;
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

    public boolean isOccupied()
    {
        return figure != null;
    }
}
