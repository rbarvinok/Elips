package ua.elips.objects;

import static java.lang.Math.floor;
import static java.lang.StrictMath.toDegrees;

public class RadToDMS {
    private double degrees;
    private double minut;
    private double second;

    public RadToDMS(Double degreesToConvert) {
        double validDegrees = toDegrees(degreesToConvert);
        degrees = floor(validDegrees);
        minut = floor((validDegrees - degrees) * 60);
        second = Math.rint(((validDegrees - degrees) * 60 - minut) * 60 * 100000) / 100000;

    }



    public double getDegrees() {
        return degrees;
    }

    public double getMinut() {
        return minut;
    }

    public double getSecond() {
        return second;
    }
}
