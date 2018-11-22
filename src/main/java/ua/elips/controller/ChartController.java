package ua.elips.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import ua.elips.objects.PushBackBatton;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChartController implements Initializable {
    PushBackBatton pb = new PushBackBatton();

    @FXML
    public Button backBtn;
    @FXML
    public ScatterChart scch;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        NumberAxis x = new NumberAxis();
        NumberAxis y = new NumberAxis();
        ScatterChart<Number, Number> scatterChart = new ScatterChart<Number, Number>(x, y);
        scatterChart.setTitle("Series");
        x.setLabel("Y");
        y.setLabel("X");

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("ffghggggg");

        XYChart.Series series2 = new XYChart.Series();
        series2.setName("ytdrtdrtrdrdrrf");

        ObservableList<XYChart.Data> datas1 = FXCollections.observableArrayList();
        ObservableList<XYChart.Data> datas2 = FXCollections.observableArrayList();

        for (int i = 0; i < 20; i++) {
            datas1.add(new XYChart.Data(i, Math.sin(i)));
            datas2.add(new XYChart.Data(i, Math.cos(i)));
        }
        series1.setData(datas1);
        series2.setData(datas2);

        scatterChart.getData().addAll(series1, series2);

    }

    public void OnClickBackButton(ActionEvent actionEvent) throws IOException {
        pb.back = backBtn;
        pb.backButton();
    }
}
