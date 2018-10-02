package ua.elips.geoProblem;

import static java.lang.StrictMath.*;

public class OgzCK42 {
    private double dist;
    private double ang;

    public OgzCK42(Double x1, Double y1, Double x2, Double y2) {
        culculateDist(x1, y1, x2, y2);
        culculateAngle(x1, y1, x2, y2);
    }

    private void culculateDist(Double x1, Double y1, Double x2, Double y2) {
        this.dist = rint(sqrt(pow((x2 - x1), 2) + pow((y2 - y1), 2)) * 100) / 100;
    }

    private void culculateAngle(Double x1, Double y1, Double x2, Double y2) {
        if ((y2 - y1) > 0) {
            ang = rint(acos((x2 - x1) / this.dist) * 9.5492 * 100) / 100;
        } else
            ang = rint((60 - acos((x2 - x1) / this.dist) * 9.5492) * 100) / 100;
    }


    public double getDistance() {
        return dist;

    }

    public double getAngle() {
        return ang;
    }
}
