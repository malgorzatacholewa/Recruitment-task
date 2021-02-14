package yanosik;

import yanosik.model.Line;
import yanosik.model.Point;
import yanosik.readers.BinaryLineFileReader;
import yanosik.readers.CsvLinesReader;
import yanosik.readers.CsvPointsReader;
import yanosik.tools.LinesProvider;
import yanosik.writers.BinaryLineFileWriter;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String filePathToLines;
        String filePathToPoints;
        String destinationFilePath;

        if (args.length == 3) {
            filePathToLines = args[0];
            filePathToPoints = args[1];
            destinationFilePath = args[2];
        } else {
            filePathToLines = "src/main/resources/testLine.csv";
            filePathToPoints = "src/main/resources/testPoint.csv";
            destinationFilePath = "src/main/resources/trueLines.dat";
        }

        try {
            System.out.println("\nTrwa odczyt linii z pliku " + filePathToLines + " ...");
            CsvLinesReader csvLinesReader = new CsvLinesReader();
            Map<String, Line> mapOfLines = csvLinesReader.readLinesFile(filePathToLines);

            System.out.println("\nTrwa odczyt punktów z pliku " + filePathToPoints + " ...");
            CsvPointsReader csvPointsReader = new CsvPointsReader();
            Map<String, List<Point>> mapOfPoints = csvPointsReader.readPointsFile(filePathToPoints);

            LinesProvider linesProvider = new LinesProvider(mapOfLines, mapOfPoints);
            List<Line> linesList = linesProvider.getListOfLines();
            System.out.println("\nWczytano następujące linie:");
            Line.printListOfLines(linesList);

            System.out.println("\nLista linii z flagą równą true:");
            List<Line> listOfTrueLines = linesProvider.getOnlyLinesWithFlagTrue();
            Line.printListOfLines(listOfTrueLines);

            System.out.println("\nTrwa zapisywanie listy z liniami do pliku " + destinationFilePath + " ...");
            BinaryLineFileWriter binaryLineFileWriter = new BinaryLineFileWriter();
            binaryLineFileWriter.writeLinesToFile(destinationFilePath, listOfTrueLines);

            System.out.println("\nTrwa odczytywanie listy z liniami z pliku " + destinationFilePath + " ... ");
            BinaryLineFileReader binaryLineFileReader = new BinaryLineFileReader();
            List<Line> readiedList = binaryLineFileReader.readLinesFromFile(destinationFilePath);
            System.out.println("Odczytano następujące obiekty:");
            Line.printListOfLines(readiedList);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
