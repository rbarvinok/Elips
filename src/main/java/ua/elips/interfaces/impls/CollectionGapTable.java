package ua.elips.interfaces.impls;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import ua.elips.interfaces.GapTable;
import ua.elips.objects.Gap;

// класс реализовывает интерфейс с помощью коллекции
public class CollectionGapTable implements GapTable {

    private ObservableList<Gap> gapList = FXCollections.observableArrayList();

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

    public void print(){
        int number = 0;
        System.out.println();
        for(Gap gap : gapList){
            number++;
            System.out.println(number+") X = "+gap.getX()+"; Y = "+gap.getY());
        }
    }

    public void fillTestData(){
        gapList.add(new Gap(1, "45000.9","96000.6","45.0","15.0","2.0","5.2"));
        gapList.add(new Gap(2, "45555.5","96888.3","450.0","15.5","2.5","5.8"));
        gapList.add(new Gap(3, "45877.2","96850.2","420.0","14.5","3.5","5.4"));

    }



}

