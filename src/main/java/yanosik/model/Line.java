package yanosik.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Line implements Serializable {
    private final List<Point> points;
    private final boolean someFlag;

    public Line(boolean someFlag) {
        this.points = new ArrayList<>();
        this.someFlag = someFlag;
    }

    public static void printListOfLines(List<Line> listOfLines) {
        for (Line line : listOfLines) {
            System.out.println(line);
        }
    }

    public void addPoint(Point point) {
        this.points.add(point);
    }

    public boolean isSomeFlag() {
        return someFlag;
    }

    @Override
    public String toString() {
        return points.toString() + "  " + someFlag;
    }
}
