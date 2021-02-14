package yanosik.writers;

import yanosik.model.Line;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class BinaryLineFileWriter {
    public void writeLinesToFile(String destinationFilePath, List<Line> listOfLines) throws IOException {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(destinationFilePath));
            outputStream.writeObject(listOfLines);
            outputStream.flush();
            outputStream.close();
            System.out.println("Lista została pomyślnie zapisana w pliku " + destinationFilePath);
    }
}
