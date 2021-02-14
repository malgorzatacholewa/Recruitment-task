package yanosik.readers;

import yanosik.model.Line;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

public class BinaryLineFileReader {
    public List<Line> readLinesFromFile(String filePath) throws IOException, ClassNotFoundException {
        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(filePath));
        List<Line> listOfLines = (List<Line>) inputStream.readObject();
        inputStream.close();

        return listOfLines;
    }
}
