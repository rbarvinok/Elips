package ua.elips.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import ua.elips.interfaces.impls.CollectionGapTable;
import ua.elips.objects.Gap;
import ua.elips.objects.PushBackBatton;

import java.awt.geom.RectangularShape;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static ua.elips.objects.Calculate.xCgr;
import static ua.elips.objects.Calculate.yCgr;

public class ChartController implements Initializable {
    PushBackBatton pb = new PushBackBatton();
    private CollectionGapTable gapTableImpl = new CollectionGapTable();

    @FXML
    public Button backBtn;
    @FXML
    public ScatterChart scatterChart;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        NumberAxis x = new NumberAxis();
        NumberAxis y = new NumberAxis();
        ScatterChart<Number, Number> scc = new ScatterChart<Number, Number>(x, y);
        scc.setTitle("");
        x.setLabel("Y");
        y.setLabel("X");


        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Координати розривів");

        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Центр групи розривів");

        ObservableList<XYChart.Data> datas1 = FXCollections.observableArrayList();
        int nam = 0;
        for (Gap gap : gapTableImpl.getGapList()) {
            nam++;
            datas1.add(new XYChart.Data(Double.parseDouble(gap.getY()), Double.parseDouble(gap.getX())));
            System.out.println(gap.getY());
            for (int ii = 1; ii < 10; ii++) {
                datas1.add(new XYChart.Data(ii, Math.sin(ii)));
            }
        }

/*

*/
        ObservableList<XYChart.Data> datas2 = FXCollections.observableArrayList();
        datas2.add(new XYChart.Data(getYcgr(), getXcgr()));

        series1.setData(datas1);
        series2.setData(datas2);

        scatterChart.getData().addAll(series1, series2);
    }


    public double getXcgr() {
        return xCgr;
    }

    public double getYcgr() {
        return yCgr;
    }

    public void OnClickBackButton(ActionEvent actionEvent) throws IOException {
        pb.back = backBtn;
        pb.backButton();
    }
}