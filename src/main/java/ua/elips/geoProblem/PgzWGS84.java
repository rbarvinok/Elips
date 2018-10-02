package ua.elips.geoProblem;

import ua.elips.objects.RadToDMS;

import static java.lang.StrictMath.*;

public class PgzWGS84 {

    private double bd, bm, bs;
    private double ld, lm, ls;

    public PgzWGS84(double latitude1, double longitude1, double s, double angle) {

        double lat1 = toRadians(latitude1);
        double long1 = toRadians(longitude1);
        double a1 = toRadians(angle);

        double a = 6378137; //Радиус на экваторе WGS84
        double f = 1 / 298.257223563; //Сжатие WGS84
        double b = (1 - f) * a; //Радиус на полюсах

        double sina1 = sin(a1);
        double cosa1 = cos(a1);

        double tanU1 = (1 - f) * tan(lat1);
        double cosU1 = 1 / sqrt((1 + tanU1 * tanU1));
        double sinU1 = tanU1 * cosU1;
        double q1 = atan(tanU1 / cosa1);
        double sina = cosU1 * sina1;
        double cos2a = 1 - pow(sina, 2);
        double u2 = cos2a * (a * a - b * b) / (b * b);
        double A = 1 + u2 / 16384 * (4096 + u2 * (-768 + u2 * (320 - 175 * u2)));
        double B = u2 / 1024 * (256 + u2 * (-128 + u2 * (74 - 47 * u2)));

        double q = s / (b * A);
        double q_;
        double sinq, cosq, cos2qM;
        do {
            cos2qM = cos(2 * q1 + q);
            sinq = sin(q);
            cosq = cos(q);
            double deltaq = B * sinq * (cos2qM + B / 4 * (cosq * (-1 + 2 * cos2qM * cos2qM) - B / 6 * cos2qM * (-3 + 4 * sinq * sinq) * (-3 + 4 * cos2qM * cos2qM)));
            q_ = q;
            q = s / (b * A) + deltaq;
        } while (abs(q - q_) > 1e-12);

        double tmp = sinU1 * sinq - cosU1 * cosq * cosa1;

        double latid2 = atan2(sinU1 * cosq + cosU1 * sinq * cosa1, (1 - f) * sqrt(sina * sina + tmp * tmp));

        double y = atan2(sinq * sina1, cosU1 * cosq - sinU1 * sinq * cosa1);
        double C = f / 16 * cos2a * (4 + f * (4 - 3 * cos2a));
        double L = y - (1 - C) * f * sina * (q + C * sinq * (cos2qM + C * cosq * (-1 + 2 * cos2qM * cos2qM)));

        double longit2 = (long1 + L + 3 * PI) % (2 * PI) - PI;  // normalise to -180...+180

        double a2 = atan2(sina, -tmp); // обратный угол

        RadToDMS latToDMS = new RadToDMS(latid2);
        bd = latToDMS.getDegrees();
        bm = latToDMS.getMinut();
        bs =latToDMS.getSecond();

        RadToDMS longToDMS = new RadToDMS(longit2);
        ld = longToDMS.getDegrees();
        lm = longToDMS.getMinut();
        ls = longToDMS.getSecond();

    }

    public double getLatitude2d() {
        return bd;
    }

    public double getLatitude2m() {
        return bm;
    }

    public double getLatitude2s() {
        return bs;
    }

    public double getLongitude2d() {
        return ld;
    }

    public double getLongitude2m() {
        return lm;
    }

    public double getLongitude2s() {
        return ls;
    }
}





