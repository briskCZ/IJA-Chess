package chess.game;

import java.io.*;

public class GameFileLoader
{
    private File file;

    public GameFileLoader(File file)
        {
            this.file = file;

            BufferedReader br = null;
            try{
                br = new BufferedReader(new FileReader(this.file));

                String line;
                while ((line = br.readLine()) != null)
                    //vypisuje soubor po radcich
                    System.out.println(line);
            }
            catch (Exception e){
                System.out.println(e);
            }

    }

    public GameRecord loadGame()
    {
        return new GameRecord();
    }
}
