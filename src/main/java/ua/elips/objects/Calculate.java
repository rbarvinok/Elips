package ua.elips.objects;

import ua.elips.geoProblem.OgzCK42;
import ua.elips.interfaces.impls.CollectionGapTable;

public class Calculate {

    private double dGap;
    private double aGap;
    private double dD;
    private double dB;
    private double xCgr, yCgr, dCgr, aCgr;
    private double vd, vb;
    public double xVp;
    public double yVp;
    private Gap gap;

   // private CollectionGapTable gapTableImpl = new CollectionGapTable();

    public void UpdateXYvp(Double xVp, Double yVp) {
        this.xVp = xVp;
        this.yVp = yVp;
    }
    public void UpdateXYgap(Double xGap, Double yGap) {
        //this.xGap = xGap;
        //this.yGap = yGap;
    }

    public String calculateDGap() {
        UpdateXYvp(xVp, yVp);
        this.gap = gap;
        //OgzCK42 ogzCK42 = new OgzCK42(xVp, yVp, Double.parseDouble(gap.getX()), Double.parseDouble(gap.getY()));
        OgzCK42 ogzCK42 = new OgzCK42(xVp, yVp, 45055.5, 96066.2);
        dGap = ogzCK42.getDistance();
//        dGap = xVp;
        return Double.toString(Math.rint(dGap * 100) / 100).replace(".", ",");
    }

    public String calculateAGap() {
        UpdateXYvp(xVp, yVp);
        OgzCK42 ogzCK42 = new OgzCK42(xVp, yVp, 45055.5, 96066.2);
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
