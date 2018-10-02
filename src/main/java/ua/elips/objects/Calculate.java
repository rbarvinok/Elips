package ua.elips.objects;

public class Calculate {

    private double d;
    private double a;
    private double dd;
    private double db;
    private double xCgr, yCgr, dCgr, aCgr;
    private double vd, vb;

    public String calculateD() {
        d = 625.25;
        return Double.toString(d);
    }

    public String calculateA() {
        a = 12.0;
        return Double.toString(a);
    }


    public String calculateDd() {
         //dd = Math.cos(aVp-aCgr)*d-dCgr;
        return Double.toString(dd);
    }

    public String calculateDb() {
        //db = Math.sin(aVp-aCgr)*d;
        return Double.toString(db);

    }


}
