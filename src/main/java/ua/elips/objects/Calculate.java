package ua.elips.objects;

import ua.elips.geoProblem.OgzCK42;

public class Calculate {

    private double dGap;
    private double aGap;
    private double dd;
    private double db;
    private double xCgr, yCgr, dCgr, aCgr;
    private double vd, vb;

//    double dist;
//    double ang;

    private void DA(Double x1, Double y1, Double x2, Double y2) {
        Dist(x1, y1, x2, y2);
        Ang(x1, y1, x2, y2);
    }


    public void Dist(Double x1, Double y1, Double x2, Double y2) {
        OgzCK42 ogzCK42 = new OgzCK42(x1, y1, x2, y2);
        double dist = ogzCK42.getDistance();
    }

    private void Ang(Double x1, Double y1, Double x2, Double y2) {
        OgzCK42 ogzCK42 = new OgzCK42(x1, y1, x2, y2);
        double ang = ogzCK42.getAngle();
    }

    public String culculateDGap() {
        dGap = 45.5;
        return Double.toString(Math.rint(dGap * 100) / 100);
    }

    public String culculateAGap() {
        aGap = 78.02;
        return Double.toString(Math.rint(aGap * 100) / 100);
    }

    public String calculateDd() {
        dd = Math.cos(aGap - aCgr) * dGap - dCgr;
        return Double.toString(Math.rint(dd * 100) / 100);
    }

    public String calculateDb() {
        db = Math.sin(aGap - aCgr) * dGap;
        return Double.toString(Math.rint(db * 100) / 100);
    }

    public String calculateXcgr() {
        xCgr = 54254.2;
        return Double.toString(Math.rint(xCgr * 100) / 100);
    }

    public String calculateYcgr() {
        yCgr = 6050.0;
        return Double.toString(Math.rint(yCgr * 100) / 100);
    }

    public String calculateVd() {
        vd = 58;
        return Double.toString(Math.rint(vd * 100) / 100);
    }

    public String calculateVb() {
        vb = 658.2;
        return Double.toString(Math.rint(vb * 100) / 100);
    }

    public String calculateDcgr() {
        dCgr = 45;
        return Double.toString(Math.rint(dCgr * 100) / 100);
    }

    public String calculateAcgr() {
        aCgr = 85.3;
        return Double.toString(Math.rint(aCgr * 100) / 100);
    }
}
