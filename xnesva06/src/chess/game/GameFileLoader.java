package chess.game;

import java.nio.file.Path;

public class GameFileLoader
{
    Path filePath;

    public GameFileLoader(Path path)
    {
        this.filePath = path;
    }

    public GameRecord loadGame()
    {
        return new GameRecord();
    }
}
