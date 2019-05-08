package chess.game;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.*;

public class FileHandler
{
    public static Record loadRecord(File file)
    {
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));

            String line;
            while ((line = br.readLine()) != null)
                System.out.println(line);
        }
        catch (IOException e){
            System.out.println(e);
        }
        return new Record();
    }

    public static boolean saveRecord(Record record, File file)
    {
        throw new NotImplementedException();
    }
}
