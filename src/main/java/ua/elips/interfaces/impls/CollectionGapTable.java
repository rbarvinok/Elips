package ua.elips.interfaces.impls;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import ua.elips.interfaces.GapTableInterface;
import ua.elips.objects.Calculate;
import ua.elips.objects.Gap;

import java.io.*;
import java.nio.Buffer;

import static ua.elips.objects.Calculate.vb;
import static ua.elips.objects.Calculate.vd;
import static ua.elips.objects.Calculate.yVp;

// класс реализовывает интерфейс с помощью коллекции
public class CollectionGapTable implements GapTableInterface {

    public ObservableList<Gap> gapList = FXCollections.observableArrayList();
    Calculate calc = new Calculate();
    public double x, y;
    public double pow_dD, pow_dB;

    @Override
    public void add(Gap gap) {
        gapList.add(gap);
    }

    @Override
    // для коллекции не используется, но пригодится для случая, когда данные хранятся в БД и пр.
    public void update(Gap gap) {
        // т.к. коллекция и является хранилищем - то ничего обновлять не нужно
        // если бы данные хранились в БД или файле - в этом методе нужно было бы обновить соотв. запись
    }

    @Override
    public void delete(Gap gap) {
        gapList.remove(gap);
    }

    public ObservableList<Gap> getGapList() {
        return gapList;
    }

    public void CoordCgr() {
        int nam = 0;
        for (Gap gap : gapList) {
            nam++;
            if (nam == 1) {
                x = Double.parseDouble(gap.getX());
                y = Double.parseDouble(gap.getY());
            } else {
                x = x + Double.parseDouble(gap.getX());
                y = y + Double.parseDouble(gap.getY());
            }
        }
        calc.GetCoordinateCgr(x, y);
    }

    public void CalculatePowDdDb() {
        int n = 0;

        for (Gap gap : gapList) {
            n++;
            if (n == 1) {
                pow_dD = Math.pow(Double.parseDouble(gap.getDD().replace(",", ".")), 2);
                pow_dB = Math.pow(Double.parseDouble(gap.getDB().replace(",", ".")), 2);

            } else {
                pow_dD = pow_dD + Math.pow(Double.parseDouble(gap.getDD().replace(",", ".")), 2);
                pow_dB = pow_dB + Math.pow(Double.parseDouble(gap.getDB().replace(",", ".")), 2);
            }
        }
        calc.GetPowDdDb(pow_dD, pow_dB);
    }

    public void print() {
        int i = 0;
        //System.out.println();
        for (Gap gap : gapList) {
            i++;
            System.out.println(i + ") X = " + gap.getX() + "; Y = " + gap.getY() + "; Д вп-р = " + gap.getD() + "; А вп-р = " + gap.getA());
        }
    }

    public void fillTestData() {
//        int namber = 1;
//        gapList.add(new Gap(namber++, "45000.9", "96000.6", calc.calculateDGap(), calc.calculateAGap(), calc.calculateDd(), calc.calculateDb()));
//        gapList.add(new Gap(namber++, "45555.5", "96888.3", calc.calculateDGap(), calc.calculateAGap(), calc.calculateDd(), calc.calculateDb()));
//        gapList.add(new Gap(namber++, "45877.2", "96850.2", calc.calculateDGap(), calc.calculateAGap(), calc.calculateDd(), calc.calculateDb()));
//        gapList.add(new Gap(namber++, "47777.2", "32550.2", calc.calculateDGap(), calc.calculateAGap(), calc.calculateDd(), calc.calculateDb()));
    }
}

