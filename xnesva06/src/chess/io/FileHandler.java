package chess.io;

import chess.game.Record;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.*;

public class FileHandler
{
    public static Record loadRecord(File file)
    {
        Record loadedRecord = new Record();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));

            String line;
            Parser parser = new Parser();
            while ((line = br.readLine()) != null)
            {
                if (parser.parseLine(loadedRecord, line) == false)
                {
                    return null;
                }
            }
        }
        catch (IOException e){
            System.out.println(e);
        }
        return loadedRecord;
    }



    public static boolean saveRecord(Record record, File file)
    {
        throw new NotImplementedException();
    }
}
