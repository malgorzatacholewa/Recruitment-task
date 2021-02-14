package yanosik.readers;

import yanosik.model.Line;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CsvLinesReader {
    public Map<String, Line> readLinesFile(String filePathToLines) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filePathToLines));
        Map<String, Line> mapOfLines = new HashMap<>();
        int lineCounter = 0;

        String fileRow;
        while ((fileRow = bufferedReader.readLine()) != null) {
            lineCounter++;
            processReadRow(fileRow, mapOfLines, lineCounter);
        }

        bufferedReader.close();

        return mapOfLines;
    }

    private void processReadRow(String row, Map<String, Line> mapOfLines, int lineCounter) {
        String[] dividedLine = row.split(";");

        if (dividedLine.length == 2) {
            Boolean rowFlag = getBooleanValueFromString(dividedLine[1]);

            if (rowFlag != null) {
                Line line = mapOfLines.get(dividedLine[0]);

                if (line == null) {
                    mapOfLines.put(dividedLine[0], new Line(rowFlag));
                } else if (line.isSomeFlag() != rowFlag) {
                    System.out.println("Ostrzeżenie: linia nr " + lineCounter + " ma takie samo id jak wcześniej zaczytania linia, ale mają różne flagi. " +
                            "Linia została pominięta w trakcie wczytywania.");
                }
            } else {
                System.out.println("Ostrzeżenie: linia nr " + lineCounter + " ma niepoprawną wartość pola flaga (wymagana wartość true lub false). " +
                        "Linia została pominięta w trakcie wczytywania.");
            }
        } else {
            System.out.println("Ostrzeżenie: linia nr " + lineCounter + " ma niepoprawny format: liczba wartości różna od 2. " +
                    "Linia została pominięta w trakcie wczytywania.");
        }
    }

    private Boolean getBooleanValueFromString(String string) {
        if (string.toLowerCase().equals("true")) {
            return true;
        } else if (string.toLowerCase().equals("false")) {
            return false;
        } else {
            return null;
        }
    }
}