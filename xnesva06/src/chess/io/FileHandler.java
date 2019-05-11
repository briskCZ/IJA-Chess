package chess.io;

import chess.game.Record;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.*;

public class FileHandler
{
    public boolean loadRecord(File file, Record loadedRecord)
    {
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));

            String line;
            Parser parser = new Parser();
            while ((line = br.readLine()) != null)
            {
                if (parser.parseLine(loadedRecord, line) == false)
                {
                    return false;
                }
            }
        }
        catch (IOException e){
            System.out.println(e);
        }
        return true;
    }



    public boolean saveRecord(Record record, File file)
    {
        throw new NotImplementedException();
    }
}
