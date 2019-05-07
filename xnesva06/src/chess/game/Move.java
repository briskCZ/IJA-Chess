package chess.game;

import chess.figures.Figure;

public class Move
{
    int currCol;
    int currRow;
    int targetCol;
    int targetRow;
    Figure figure;
    public Move(int currCol, int currRow, int targetCol, int targetRow, Figure figure)
    {
        this.currCol = currCol;
        this.currRow = currRow;
        this.targetCol = targetCol;
        this.targetRow = targetRow;
        this.figure = figure;
    }
}
