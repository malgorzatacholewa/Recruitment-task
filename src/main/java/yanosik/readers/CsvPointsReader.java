package yanosik.readers;

import yanosik.model.Point;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CsvPointsReader {
    public Map<String, List<Point>> readPointsFile(String filePathToPoints) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filePathToPoints));
        Map<String, List<Point>> mapOfPoints = new HashMap<>();
        int lineCounter = 0;

        String fileRow;
        while ((fileRow = bufferedReader.readLine()) != null) {
            lineCounter++;
            processReadRow(fileRow, mapOfPoints, lineCounter);
        }

        bufferedReader.close();

        return mapOfPoints;
    }

    private void processReadRow(String row, Map<String, List<Point>> mapOfPoints, int lineCounter) {
        String[] dividedLine = row.split(";");

        if (dividedLine.length == 3) {
            try {
                float pointX = Float.parseFloat(dividedLine[0]);
                float pointY = Float.parseFloat(dividedLine[1]);
                Point point = new Point(pointX, pointY);

                String rowID = dividedLine[2];

                List<Point> listOfPoints = mapOfPoints.get(rowID);
                if (listOfPoints == null) {
                    listOfPoints = new ArrayList<>();
                    listOfPoints.add(point);

                    mapOfPoints.put(rowID, listOfPoints);
                } else {
                    listOfPoints.add(point);
                }
            } catch (NumberFormatException e) {
                System.out.println("Ostrzeżenie: linia nr " + lineCounter + " ma niepoprawny format współrzędnych punktów. " +
                        "Linia została pominięta w trakcie wczytywania.");
            }
        } else {
            System.out.println("Ostrzeżenie: linia nr " + lineCounter + " ma niepoprawny format: liczba wartości rózna od 3. " +
                    "Linia została pominięta w trakcie wczytywania.");
        }
    }
}
