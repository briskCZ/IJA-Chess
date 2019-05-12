package chess.game;

import chess.board.ChessBoard;
import chess.board.Field;
import chess.figures.Figure;
import chess.figures.FigureColor;
import chess.figures.FigureType;
import chess.figures.King;
import chess.io.FileHandler;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;

public class Game
{
    protected ChessBoard chessBoard;
    private Record playerRecord;
    private Record loadedRecord;
    private ReplayHandler replayHandler;
    private FileHandler fileHandler;
    private int id;
    private FigureColor turnColor;
    private boolean is_white_check = false;
    private boolean is_black_check = false;
    private boolean is_checkmate = false;

    public Game(int gameId)
    {
        System.out.println("Creating game: " + gameId);
        this.id = gameId;
        this.chessBoard = new ChessBoard();
        this.playerRecord = new Record();
        this.loadedRecord = new Record();
        this.replayHandler = new ReplayHandler(this.playerRecord, this.loadedRecord, this);
        this.fileHandler = new FileHandler();
        this.turnColor = FigureColor.White;
    }
    public boolean loadGame(File file)
    {
        boolean retval = fileHandler.loadRecord(file, loadedRecord);
        loadedRecord.resetIndex();
        return retval;
    }
    public boolean saveGame(File file)
    {
        return fileHandler.saveRecord(replayHandler.getCompleteRecord(), file);
    }
    public boolean isOnTurn(FigureColor color)
    {
        return color == turnColor;
    }
    public void move(Figure selectedFigure, Field destination, FigureType type)
    {
        Field figurePosition = chessBoard.getField(selectedFigure.getRow(), selectedFigure.getColumn());
        Move move = new Move(figurePosition, destination);
        ArrayList<Move.Tag> tags = new ArrayList<>();
        replayHandler.lockLoadedMovesIndex();
        playerRecord.addMove(move);
        if (destination.isOccupiedWithEnemyFig(selectedFigure))
        {
            tags.add(Move.Tag.Kick);
        }
        destination.setFigure(selectedFigure);
        figurePosition.removeFigure();
        move.executeMove(figurePosition, destination, tags.toArray(new Move.Tag[tags.size()]));
        changeTurn();

    }
    public ArrayList<Field> getPossibleMoves(Figure selectedFigure)
    {
        return selectedFigure.getPossibleMoveFields(chessBoard);
    }

    public void undoMove()
    {
        replayHandler.undoPlayerMove();
    }

    public void redoMove()
    {
        replayHandler.redoPlayerMove();
    }
    public void printGame()
    {
        System.out.println(this.chessBoard);
    }
    public int getGameId()
    {
        return this.id;
    }
    public Field getBoardField(int row, int column)
    {
        return chessBoard.getField(row, column);
    }
    public ReplayHandler getReplayHandler()
    {
        return replayHandler;
    }
    protected void changeTurn()
    {
        if (turnColor == FigureColor.White)
        {
            turnColor = FigureColor.Black;
        }
        else if (turnColor == FigureColor.Black)
        {
            turnColor = FigureColor.White;
        }
    }


    public boolean getCheck(FigureColor color){
        if(color == FigureColor.Black){
            return is_black_check;
        }else{
            return is_white_check;
        }
    }

    public void setCheck(FigureColor color,boolean check){
        if(color == FigureColor.Black){
            this.is_black_check = check;
        }else{
            this.is_white_check = check;
        }
    }

    public boolean getCheckmate(){
        return this.is_checkmate;
    }

    public void setCheckmate(boolean checkmate){
        this.is_checkmate = checkmate;
    }

    public void checkCheck(){
        Field king_black_field = null;
        Field king_white_field = null;

        Figure figure;
        for (int x = 0; x<8;x++){
            for (int y = 0; y< 8; y++){
                figure = chessBoard.getField(x,y).getFigure();
                if(figure != null && figure.getType() == FigureType.King){
                    if(figure.getColor() == FigureColor.Black){
                        king_black_field = chessBoard.getField(figure.getRow(),figure.getColumn());
                    }else{
                        king_white_field = chessBoard.getField(figure.getRow(),figure.getColumn());
                    }
                }
            }
        }

        HashSet<Field> possibleMovesOfOthersBlack = getPossibleMovesOfOthers(chessBoard,king_black_field.getFigure());
        HashSet<Field> possibleMovesOfOthersWhite = getPossibleMovesOfOthers(chessBoard,king_white_field.getFigure());

        is_black_check = isInDanger(possibleMovesOfOthersBlack,king_black_field);
        is_white_check = isInDanger(possibleMovesOfOthersWhite,king_white_field);

    }

    private boolean isInDanger(HashSet<Field> possibleMoves, Field king_field){
        for(Field field : possibleMoves){
            if(field == king_field){
                return true;
            }
        }
        return false;
    }

    private FigureColor getOpositeColor(FigureColor color) {
        if(color == FigureColor.White){
            return FigureColor.Black;
        }else{
            return FigureColor.White;
        }
    }

    private HashSet<Field> getPossibleMovesOfOthers(ChessBoard board, Figure king) {
        HashSet<Field> possibleMovesOfOthers = new HashSet<Field>();
        Figure figure;
        ArrayList<Field> possibleMoves =  new ArrayList<Field>();
        for (int x = 0; x<8;x++){
            for(int y = 0; y< 8; y++){
                figure = board.getField(x,y).getFigure();
                if(figure != null && figure.getColor() != king.getColor()){
                    if(figure.getType() == FigureType.King){
                        King fig = (King) figure;
                        possibleMoves.addAll(fig.getBasePossibleMoveFields(board));
                        possibleMovesOfOthers.addAll(possibleMoves);
                        break;
                    }
                    possibleMoves = figure.getPossibleMoveFields(board);
                    possibleMovesOfOthers.addAll(possibleMoves);
                }
            }
        }
        return possibleMovesOfOthers;
    }

}
