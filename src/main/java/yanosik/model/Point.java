package yanosik.model;

import java.io.Serializable;

public class Point implements Serializable {
    private final float x;
    private final float y;

    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point {x = " + x + ", y = " + y + "}";
    }
}

