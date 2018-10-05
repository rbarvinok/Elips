package ua.elips.objects;

import ua.elips.geoProblem.OgzCK42;

import static ua.elips.controller.Controller.xVp;
import static ua.elips.controller.Controller.yVp;


public class Calculate {

    private double dGap;
    private double aGap;
    private double dd;
    private double db;
    private double xCgr, yCgr, dCgr, aCgr;
    private double vd, vb;
//    public double xVp;
//    public double yVp;


    public String culculateDGap() {
        OgzCK42 ogzCK42 = new OgzCK42(xVp, yVp, 45000.9, 96000.6);
        dGap = ogzCK42.getDistance();
        return Double.toString(Math.rint(dGap * 100) / 100).replace(".", ",");
    }

    public String culculateAGap() {
        OgzCK42 ogzCK42 = new OgzCK42(xVp, yVp, 45000.9, 96000.6);
        aGap = ogzCK42.getAngle();
        return Double.toString(Math.rint(aGap * 100) / 100).replace(".", ",");
    }

    public String calculateDd() {
        dd = Math.cos(aGap - aCgr) * dGap - dCgr;
        return Double.toString(Math.rint(dd * 100) / 100).replace(".", ",");
    }

    public String calculateDb() {
        db = Math.sin(aGap - aCgr) * dGap;
        return Double.toString(Math.rint(db * 100) / 100).replace(".", ",");
    }

    public String calculateXcgr() {
        xCgr = 55254.2;
//        int i = 0;
//
//        for (Gap gap : gapList) {
//            i++;
//            System.out.println(i + ") X = " + gap.getX() + "; Y = " + gap.getY());
//            xCgr = Double.parseDouble(gap.getX());
//        }
        return Double.toString(Math.rint(xCgr * 100) / 100).replace(".", ",");
    }

    public String calculateYcgr() {
        yCgr = 96201.0;
        return Double.toString(Math.rint(yCgr * 100) / 100).replace(".", ",");
    }

    public String calculateVd() {
        vd = 58;
        return Double.toString(Math.rint(vd * 100) / 100).replace(".", ",");
    }

    public String calculateVb() {
        vb = 65.2;
        return Double.toString(Math.rint(vb * 100) / 100).replace(".", ",");
    }

    public String calculateDcgr() {
        OgzCK42 ogzCK42 = new OgzCK42(xVp, yVp, xCgr, yCgr);
        dCgr = ogzCK42.getDistance();
        return Double.toString(Math.rint(dCgr * 100) / 100).replace(".", ",");
    }

    public String calculateAcgr() {
        OgzCK42 ogzCK42 = new OgzCK42(xVp, yVp, xCgr, yCgr);
        aCgr = ogzCK42.getAngle();
        return Double.toString(Math.rint(aCgr * 100) / 100).replace(".", ",");
    }
}
