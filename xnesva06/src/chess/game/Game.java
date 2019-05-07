package chess.game;

import chess.board.ChessBoard;

import java.nio.file.Path;

public class Game
{
    ChessBoard board;
    GameRecord gameRecord;
    int id;

    public Game(int gameId)
    {
        this.id = gameId;
    }

    private boolean loadGame(Path path)
    {
        GameFileLoader gameFileLoader = new GameFileLoader(path);
        this.gameRecord = gameFileLoader.loadGame();

        return false;
    }
}
