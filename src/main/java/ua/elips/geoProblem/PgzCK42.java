package ua.elips.geoProblem;

import static java.lang.StrictMath.*;

public class PgzCK42 {
    private Double x2;
    private Double y2;

    public PgzCK42(Double x1, Double y1, Double dist, Double ang) {
        calculateX2( x1, y1, dist, ang);
        calculateY2(x1, y1, dist, ang);
    }

    private void calculateX2(Double x1, Double y1, Double dist, Double ang) {

        x2 = rint(dist * cos(toRadians(ang * 6)) + x1);
    }

    private void calculateY2(Double x1, Double y1, Double dist, Double ang) {

        y2 = rint(dist * sin(toRadians(ang * 6)) + y1);
    }


    public double getX2() {
        return x2;
    }

    public double getY2() {
        return y2;
    }
}
