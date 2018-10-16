package ua.elips.objects;

import ua.elips.geoProblem.OgzCK42;

public class Calculate {

    private int id, count;
    private double dGap;
    private double aGap;
    private double dD;
    private double dB;
    private double xCgr, yCgr, dCgr, aCgr;
    private double vd, vb;
    public static double xVp, yVp;
    public static double xGap, yGap;

    public void UpdateXYvp(Double xVp, Double yVp) {
        this.xVp = xVp;
        this.yVp = yVp;
    }

    public String calculateDGap() {
        OgzCK42 ogzCK42 = new OgzCK42(xVp, yVp, xGap, yGap);
        dGap = ogzCK42.getDistance();
        return Double.toString(Math.rint(dGap * 100) / 100).replace(".", ",");
    }

    public Integer GetCount(Integer count) {
        return count;
    }

    public Integer calculateId() {
        id = 1 + count++;
        return id;
    }

    public String calculateAGap() {
        OgzCK42 ogzCK42 = new OgzCK42(xVp, yVp, xGap, yGap);
        aGap = ogzCK42.getAngle();
        return Double.toString(Math.rint(aGap * 100) / 100).replace(".", ",");
    }

    public String calculateDd() {
        dD = Math.cos(aGap - aCgr) * dGap - dCgr;
        return Double.toString(Math.rint(dD * 100) / 100).replace(".", ",");
    }

    public String calculateDb() {
        dB = Math.sin(aGap - aCgr) * dGap;
        return Double.toString(Math.rint(dB * 100) / 100).replace(".", ",");
    }

    public String calculateXcgr() {
        xCgr = 45254.2;
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
