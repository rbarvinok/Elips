package ua.elips.interfaces.impls;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ua.elips.interfaces.GapTableInterface;
import ua.elips.objects.Calculate;
import ua.elips.objects.Gap;

// класс реализовывает интерфейс с помощью коллекции
public class CollectionGapTable implements GapTableInterface {

    public ObservableList<Gap> gapList = FXCollections.observableArrayList();
    Calculate calc = new Calculate();
    public double x, y;

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

    public void print() {
        int i = 0;
        System.out.println();
        for (Gap gap : gapList) {
            i++;
            System.out.println(i + ") X = " + gap.getX() + "; Y = " + gap.getY());
        }
    }

    public void CoordCgr() {
        int nam = 0;

        for (Gap gap : gapList) {
            nam++;
            if (nam < 2){
            x = Double.parseDouble(gap.getX());
            y = Double.parseDouble(gap.getY());
          }
            else {
                //System.out.println(nam);
                x = (x + Double.parseDouble(gap.getX()));
                y = (y + Double.parseDouble(gap.getY()));
            }
        }
        calc.GetCoordinateCgr(x, y);
    }

    public void fillTestData() {
//        int namber = 1;
//        gapList.add(new Gap(namber++, "45000.9", "96000.6", calc.calculateDGap(), calc.calculateAGap(), calc.calculateDd(), calc.calculateDb()));
//        gapList.add(new Gap(namber++, "45555.5", "96888.3", calc.calculateDGap(), calc.calculateAGap(), calc.calculateDd(), calc.calculateDb()));
//        gapList.add(new Gap(namber++, "45877.2", "96850.2", calc.calculateDGap(), calc.calculateAGap(), calc.calculateDd(), calc.calculateDb()));
//        gapList.add(new Gap(namber++, "47777.2", "32550.2", calc.calculateDGap(), calc.calculateAGap(), calc.calculateDd(), calc.calculateDb()));
    }
}

