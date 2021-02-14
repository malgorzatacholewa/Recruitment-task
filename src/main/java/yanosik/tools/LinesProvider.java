package yanosik.tools;

import yanosik.model.Line;
import yanosik.model.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class LinesProvider {
    private final List<Line> listOfLines;

    public LinesProvider(Map<String, Line> mapOfLines, Map<String, List<Point>> mapOfPoints) {
        listOfLines = new ArrayList<>();

        addPointsToExistedLines(mapOfLines, mapOfPoints);
    }

    public List<Line> getListOfLines() {
        return listOfLines;
    }

    public List<Line> getOnlyLinesWithFlagTrue() {
        return listOfLines.stream().filter(Line::isSomeFlag).collect(Collectors.toList());
    }

    private void addPointsToExistedLines(Map<String, Line> mapOfLines, Map<String, List<Point>> mapOfPoints) {
        Set<Map.Entry<String, Line>> lineIDSet = mapOfLines.entrySet();
        for (Map.Entry<String, Line> id : lineIDSet) {
            Line line = id.getValue();
            List<Point> pointsList = mapOfPoints.remove(id.getKey());

            if (pointsList != null) {
                for (Point point : pointsList) {
                    line.addPoint(point);
                }
            }

            listOfLines.add(line);
        }

        handlePointsWithNotExistedLineId(mapOfPoints);
    }

    private void handlePointsWithNotExistedLineId(Map<String, List<Point>> mapOfPoints) {
        if (!mapOfPoints.isEmpty()) {
            System.out.println("\nNastępujące punkty wskazywały na nieistniejące linie:");

            Set<Map.Entry<String, List<Point>>> entrySet = mapOfPoints.entrySet();
            for (Map.Entry<String, List<Point>> entry : entrySet) {
                System.out.println("Id linii: " + entry.getKey() + " lista punktów: " + entry.getValue());
            }
        }
    }
}
