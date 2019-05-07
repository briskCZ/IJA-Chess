package chess.game;

import java.io.File;

public class GameFileLoader
{
    private File file;

    public GameFileLoader(File file)
    {
        this.file = file;
    }

    public GameRecord loadGame()
    {
        return new GameRecord();
    }
}
