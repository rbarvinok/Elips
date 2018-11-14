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
    public static double pow_dD, pow_dB;

    public void UpdateXYvp(Double xVp, Double yVp) {
        this.xVp = xVp;
        this.yVp = yVp;
    }

    public void GetCount(Integer count) {
        this.count = count;
    }

    public void GetCoordinateCgr(Double xCgr, Double yCgr) {
        this.xCgr = xCgr / count;
        this.yCgr = yCgr / count;
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
        if (count > 0) {
            return Double.toString(Math.rint(xCgr * 100) / 100).replace(".", ",");
        } else return "0";
    }

    public String calculateYcgr() {
        if (count > 0) {
            return Double.toString(Math.rint(yCgr * 100) / 100).replace(".", ",");
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

        dD = Math.cos(aGap - aCgr)  * dGap - dCgr;
        return Double.toString(Math.rint(dD * 100) / 100).replace(".", ",");
    }

    public String calculateDb() {
        dB = Math.sin(aGap - aCgr) / 9.5492 * dGap;
        return Double.toString(Math.rint(dB * 100) / 100).replace(".", ",");
    }

    public void GetPowDdDb(Double pow_dD, Double pow_dB) {
        this.pow_dD = pow_dD;
        this.pow_dB = pow_dB;
    }

    public String calculateVd() {
        if (count>1)
            vd = Math.rint(0.6745 * Math.sqrt(pow_dD / (count - 1))*100)/100;
        else vd = 0;
            return Double.toString(vd).replace(".", ",");
    }

    public String calculateVb() {
        if (count>1)
            vb = Math.rint(0.6745 * Math.sqrt(pow_dB / (count - 1))*100)/100;
        else vb = 0;
            return Double.toString(vb).replace(".", ",");
           }
}