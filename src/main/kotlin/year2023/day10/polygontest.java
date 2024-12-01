package day10;

import java.awt.*;

public class polygontest {
    public static void main(final String[] args) {

        final Polygon polygon = new Polygon();
        polygon.addPoint(-10, -10);
        polygon.addPoint(-10, 10);
        polygon.addPoint(10, 10);
        polygon.addPoint(10, -10);

        System.out.println(polygon.contains(0, 0));

    }
}
