package chess.figures;

import chess.board.Field;
import chess.game.GameRecord;
import chess.game.Move;

public class Pawn extends Figure {

    public Pawn(int column, int row, boolean isOnBoard, FigureColor figureColor)
    {
        super(column, row, isOnBoard, figureColor, FigureType.Pawn);
    }

    public Pawn(Pawn pawn){ super(pawn); }

    @Override
    public boolean move(GameRecord gameRecord, Field currentField, Field destinationField)
    {
        Move move = new Move(this.getColumn(), this.getRow(), destinationField.getColumn(), destinationField.getRow(), this);
        gameRecord.addMove(move);
        currentField.removeFigure();
        destinationField.setFigure(this);
        this.setPosition(destinationField.getColumn(), destinationField.getRow());
        return true;
    }

    @Override
    public Field[] getPossibleMoveFields()
    {
        return new Field[0];
    }
}
