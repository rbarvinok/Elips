package ua.elips.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.BubbleChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import ua.elips.objects.PushBackBatton;
import java.io.IOException;

public class ChartController {
    PushBackBatton pb = new PushBackBatton();

    @FXML
    public Button backBtn;
    @FXML
    public BubbleChart bubbleChart;


    public void showChsrt() {
        NumberAxis x = new NumberAxis();
        NumberAxis y = new NumberAxis();
        BubbleChart<Number, Number> blc = new BubbleChart<Number, Number>(x, y);
        blc.setTitle("Series");
        x.setLabel("Y");
        y.setLabel("X");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("sin(x)");

//        series1.getData().add(new XYChart.Data(3, 35));
//        series1.getData().add(new XYChart.Data(12, 60));
//        series1.getData().add(new XYChart.Data(15, 15));
//        series1.getData().add(new XYChart.Data(22, 30));
//        series1.getData().add(new XYChart.Data(28, 20));
//        series1.getData().add(new XYChart.Data(35, 41));
//        series1.getData().add(new XYChart.Data(42, 17));
//        series1.getData().add(new XYChart.Data(49, 30));

        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Y");
//        series2.getData().add(new XYChart.Data(8, 15));
//        series2.getData().add(new XYChart.Data(13, 23));
//        series2.getData().add(new XYChart.Data(15, 45));
//        series2.getData().add(new XYChart.Data(24, 30));
//        series2.getData().add(new XYChart.Data(38, 78));
//        series2.getData().add(new XYChart.Data(40, 41));
//        series2.getData().add(new XYChart.Data(45, 57));
//        series2.getData().add(new XYChart.Data(47, 23));

        ObservableList<XYChart.Data> datas1 = FXCollections.observableArrayList();
        ObservableList<XYChart.Data> datas2 = FXCollections.observableArrayList();
        for (int i = 0; i < 20; i++) {
            datas1.add(new XYChart.Data(i, Math.sin(i)));
            datas2.add(new XYChart.Data(i, Math.cos(i)));
        }
//
        series1.setData(datas1);
        series2.setData(datas2);
        blc.getData().addAll(series1,series2);

    }

    public void OnClickBackButton(ActionEvent actionEvent) throws IOException {
        pb.back = backBtn;
        pb.backButton();
    }
}
