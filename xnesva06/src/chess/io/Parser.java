package chess.io;

import chess.board.ChessBoard;
import chess.board.Field;
import chess.figures.Figure;
import chess.figures.FigureType;
import chess.game.Move;
import chess.game.Record;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Marek Nesvadba, Zdeněk Doležal (xnesva06, xdolez82)
 * <p>Class responsible for parsing the loaded file and converting it to individual moves.
 */

public class Parser {
    private int prevLineNum;
    private ChessBoard board;

    public Parser() {
        this.prevLineNum = 0;
        this.board = new ChessBoard();
    }

    public boolean parseLine(Record loadedRecord, String line) {
        String[] split = line.split("\\s+");
        if (split.length != 3) {
            System.out.println("Parsing error: white and black move expected");
            return false;
        }
        if (!split[0].matches("\\d+\\.")) {
            System.out.println("Parsing error: be number");
            return false;
        } else {
            int lineNum = Integer.parseInt(split[0].split("\\.")[0]);
            if (prevLineNum + 1 != lineNum) {
                System.out.println("Parsing error: numbers in first column should be ordered");
                return false;
            }
            prevLineNum = lineNum;
        }
        Move whiteMove = parseMove(split[1]);
        if (whiteMove == null) {
            return false;
        } else {
            loadedRecord.addMove(whiteMove);
        }
        Move blackMove = parseMove(split[2]);
        if (blackMove == null) {
            return false;
        } else {
            loadedRecord.addMove(blackMove);
        }
        return true;
    }

    private Move.Tag parseSpecialSymbols(String tag) {
        if (tag == null) return null;
        switch(tag)
        {
            case "+":
                return Move.Tag.Check;
            case "#":
                return Move.Tag.CheckMate;
            default:
                    return null;
        }
    }

    private Move parseMove(String moveString) {
        Pattern moveRegex = Pattern.compile("^(?<fig>[KDVSJp])?(?<s1>[a-h])?(?<s2>[1-8])?(?<kick>x)?(?<d1>[a-h])(?<d2>[1-8])(?<prom>[DVSJ])?(?<pf>[+#])?$");
        Matcher matcher = moveRegex.matcher(moveString);
        ArrayList<String> matches = new ArrayList<>();
        while (matcher.find()) {
            FigureType figureType = parseFigureName(matcher.group("fig"));
            String sourceColumn = matcher.group("s1");
            String sourceRow = matcher.group("s2");
            boolean kick = matcher.group("kick") != null;
            String destColumn = matcher.group("d1");
            String destRow = matcher.group("d2");
            boolean prom = matcher.group("prom") != null;
            FigureType promFigureType = parseFigureName(matcher.group("prom"));
            String pf = matcher.group("pf");
            Field sourceField;
            Field destField;
            Figure figureToMove;
            if (sourceColumn == null || sourceRow == null) {
                // Short notation
                sourceField = null;
                destField = null;
                figureToMove = null;
            } else {
                // Long notation
                if (sourceColumn == null || sourceRow == null || destColumn == null || destRow == null) {
                    System.out.println("Parsing error: wrong move format");
                    return null;
                }
                int sColumn = stringColumnNotationToInt(sourceColumn);
                int sRow = stringRowNotationToInt(sourceRow);
                int dColumn = stringColumnNotationToInt(destColumn);
                int dRow = stringRowNotationToInt(destRow);
                sourceField = board.getField(sRow, sColumn);
                figureToMove = sourceField.getFigure();
                destField = board.getField(dRow, dColumn);
            }
            if (figureToMove == null) {
                System.out.println("Parsing error: cannot move nonexisting figure");
                return null;
            } else {
                // Perform move and save it to record
                ArrayList<Move.Tag> tags = new ArrayList<>();
                ArrayList<Field> possibleFields = figureToMove.getPossibleMoveFields(board);
                if (!possibleFields.contains(destField)) {
                    System.out.println("Parsing error: cannot perform invalid move");
                    return null;
                }
                if (parseSpecialSymbols(pf) == Move.Tag.CheckMate)
                {
                    tags.add(Move.Tag.CheckMate);
                }
                if (parseSpecialSymbols(pf) == Move.Tag.Check)
                {
                    tags.add(Move.Tag.Check);
                }
                if (kick) {
                    if (!destField.isOccupiedWithEnemyFig(figureToMove)) {
                        System.out.println("Parsing error: cannot jump nonexisting figure");
                        return null;
                    } else {
                        tags.add(Move.Tag.Kick);
                    }
                }
                if (prom) {
                    System.out.println("Promotion");
                    tags.add(Move.Tag.Promotion);
                    Move move = new Move(sourceField, destField);
                    destField.setFigure(Figure.promotePawn(destField.getRow(), destField.getColumn(), true, sourceField.getFigure().getColor(), promFigureType));
                    sourceField.removeFigure();
                    move.executeMove(sourceField, destField, tags.toArray(new Move.Tag[0]));
                    return move;

                } else {
                    Move move = new Move(sourceField, destField);
                    destField.setFigure(figureToMove);
                    sourceField.removeFigure();
                    move.executeMove(sourceField, destField, tags.toArray(new Move.Tag[0]));
                    return move;
                }

            }

        }
        return null;
    }

    private FigureType parseFigureName(String fig) {
        if (fig == null) {
            return FigureType.Pawn;
        }
        switch (fig) {
            case "K":
                return FigureType.King;
            case "D":
                return FigureType.Queen;
            case "V":
                return FigureType.Rook;
            case "S":
                return FigureType.Bishop;
            case "J":
                return FigureType.Knight;
            case "p":
                return FigureType.Pawn;
            default:
                return FigureType.Pawn;
        }
    }

    private int stringColumnNotationToInt(String col) {
        return col.charAt(0) - 'a';
    }

    private int stringRowNotationToInt(String row) {
        return Integer.parseInt(row) - 1;
    }
}

