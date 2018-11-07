package ua.elips.objects;

import ua.elips.geoProblem.OgzCK42;

public class Calculate {

    public static int id, count;
    public static double dGap;
    public static double aGap;
    public static double dD;
    public static double dB;
    public static double xCgr, yCgr;
    public static double dCgr, aCgr;
    public static double vd, vb;
    public static double xVp, yVp;
    public static double xGap, yGap;

    public void UpdateXYvp(Double xVp, Double yVp) {
        this.xVp = xVp;
        this.yVp = yVp;
    }

    public Integer GetCount(Integer count) {
        return count;
    }

    public void GetCoordinateCgr(Double xCgr, Double yCgr) {
        this.xCgr = xCgr;
        this.yCgr = yCgr;
    }

    public Integer calculateId() {
        id = 1 + count++;
        return id;
    }

    public String calculateDGap() {
        OgzCK42 ogzCK42 = new OgzCK42(xVp, yVp, xGap, yGap);
        dGap = ogzCK42.getDistance();
        return Double.toString(Math.rint(dGap * 100) / 100).replace(".", ",");
    }

    public String calculateAGap() {
        OgzCK42 ogzCK42 = new OgzCK42(xVp, yVp, xGap, yGap);
        aGap = ogzCK42.getAngle();
        return Double.toString(Math.rint(aGap * 100) / 100).replace(".", ",");
    }

    public String calculateXcgr() {
        // xCgr = 45254.2;
        //System.out.println(Double.toString(xCgr));
        if (count > 0) {
            System.out.println(Double.toString(xCgr/count));
            return Double.toString(Math.rint(xCgr / count * 100) / 100).replace(".", ",");
        } else return "0";
    }



    public String calculateYcgr() {
        //yCgr = 96201.0;
       // System.out.println(Double.toString(yCgr));
        if (count > 0) {
            System.out.println(Double.toString(yCgr / count));
            return Double.toString(Math.rint(yCgr / count * 100) / 100).replace(".", ",");
        } else return "0";
    }

    public String calculateDcgr() {
        if (xCgr > 0 & yCgr > 0) {
            OgzCK42 ogzCK42 = new OgzCK42(xVp, yVp, xCgr, yCgr);
            dCgr = ogzCK42.getDistance();
            return Double.toString(Math.rint(dCgr * 100) / 100).replace(".", ",");
        } else return "0";
    }

    public String calculateAcgr() {
        if (xCgr > 0 & yCgr > 0) {
            OgzCK42 ogzCK42 = new OgzCK42(xVp, yVp, xCgr, yCgr);
            aCgr = ogzCK42.getAngle();
            return Double.toString(Math.rint(aCgr * 100) / 100).replace(".", ",");
        } else return "0";
    }

    public String calculateDd() {
        dD = Math.cos(aGap - aCgr) * dGap - dCgr;
        return Double.toString(Math.rint(dD * 100) / 100).replace(".", ",");
    }

    public String calculateDb() {
        dB = Math.sin(aGap - aCgr) * dGap;
        return Double.toString(Math.rint(dB * 100) / 100).replace(".", ",");
    }

    public String calculateVd() {
        vd = 58;

        return Double.toString(Math.rint(vd * 100) / 100).replace(".", ",");
    }

    public String calculateVb() {
        vb = 65.2;
        return Double.toString(Math.rint(vb * 100) / 100).replace(".", ",");
    }
}