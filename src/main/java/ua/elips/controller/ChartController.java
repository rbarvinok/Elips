package ua.elips.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.BubbleChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import ua.elips.interfaces.impls.CollectionGapTable;
import ua.elips.objects.Calculate;
import ua.elips.objects.Gap;
import ua.elips.objects.PushBackBatton;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static ua.elips.objects.Calculate.xCgr;
import static ua.elips.objects.Calculate.yCgr;

public class ChartController implements Initializable {
    PushBackBatton pb = new PushBackBatton();
    CollectionGapTable gapTableImpl = new CollectionGapTable();
    Calculate calc = new Calculate();

    @FXML
    public Button backBtn;
    @FXML
    public BubbleChart bubbleChart;
    private Gap gap;
    //public double xCgr, yCgr;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        NumberAxis x = new NumberAxis();
        NumberAxis y = new NumberAxis();
        BubbleChart<Number, Number> blc = new BubbleChart<Number, Number>(x, y);
        blc.setTitle("Series");
        x.setLabel("Y");
        y.setLabel("X");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Координати розривів");

        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Центр групи розривів");

        ObservableList<XYChart.Data> datas1 = FXCollections.observableArrayList();

        int i = 0;
        for (Gap gap : gapTableImpl.getGapList()) {
            i++;
            System.out.println(getGapX());
            //datas1.add(new XYChart.Data(Double.parseDouble(gap.getY()), Double.parseDouble(gap.getX()), 1));
            datas1.add(new XYChart.Data(getGapX(), gap.getX(), 1));

        }


//        datas1.add(new XYChart.Data(10, 30, 1));
//        datas1.add(new XYChart.Data(25, 15, 1));
//        datas1.add(new XYChart.Data(40, 50, 1));
//        datas1.add(new XYChart.Data(55, 60, 1));
//        datas1.add(new XYChart.Data(70, 70, 1));
//        datas1.add(new XYChart.Data(85, 80, 1));


        ObservableList<XYChart.Data> datas2 = FXCollections.observableArrayList();

        datas2.add(new XYChart.Data(getXcgr(), getYcgr(), 2));
        //datas2.add(new XYChart.Data(i, Math.cos(i),0.1));

        series1.setData(datas1);
        series2.setData(datas2);

        bubbleChart.getData().addAll(series1, series2);
    }

    public double getXcgr() {
        return xCgr;
    }

    public double getYcgr() {
        return yCgr;
    }

    public double getGapX() {
        gapTableImpl.getGapList();
        return Double.parseDouble(gap.getY());
    }

    public void OnClickBackButton(ActionEvent actionEvent) throws IOException {
        pb.back = backBtn;
        pb.backButton();
    }
}
