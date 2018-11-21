package ua.elips.geoProblem.controllerGeo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import ua.elips.geoProblem.ConverterCoordinateSystem;
import ua.elips.geoProblem.ConverterCoordinates;
import ua.elips.objects.Clear;
import ua.elips.objects.DialogManeger;
import ua.elips.objects.PushBackBatton;
import ua.elips.objects.RadToDMS;

import java.io.IOException;

public class ControllerConvertCK42toWGS84 {

    @FXML
    public TextField Tx, Ty, ThCK42;
    @FXML
    public TextField Tx2d, Tx2m, Tx2s;
    @FXML
    public TextField Ty2d, Ty2m, Ty2s;
    @FXML
    public TextField ThWGS84;
    @FXML
    public Button btnok, btnx1, back, dov;

    PushBackBatton pb = new PushBackBatton();
    DialogManeger dm = new DialogManeger();

    public void onClick_btnx1(ActionEvent actionEvent) {
        Clear.clear(Tx, Ty, ThCK42);
    }

    public void onClick_btnOk(ActionEvent actionEvent) {

        try {
            Double x1 = Double.parseDouble(Tx.getText().replace(",", "."));
            Double y1 = Double.parseDouble(Ty.getText().replace(",", "."));
            Double h1 = Double.parseDouble(ThCK42.getText().replace(",", "."));

            ConverterCoordinateSystem coordinateSystem = new ConverterCoordinateSystem();
            coordinateSystem.GKtoBLh(x1, y1, h1);
            ConverterCoordinates ck42towgs84 = new ConverterCoordinates();
            ck42towgs84.Ck42ToWgs84Converter(coordinateSystem.getLatitude42(), coordinateSystem.getLongitude42(), coordinateSystem.getAltitude42());
            RadToDMS radToDMSLat = new RadToDMS(ck42towgs84.getLatitude84());
            RadToDMS radToDMSLon = new RadToDMS(ck42towgs84.getLongitude84());

            Tx2d.setText(String.valueOf((int) (radToDMSLat.getDegrees())));
            Tx2m.setText(String.valueOf((int) (radToDMSLat.getMinut())));
            Tx2s.setText(Double.toString(radToDMSLat.getSecond()).replace(".", ","));
            Ty2d.setText(String.valueOf((int) (radToDMSLon.getDegrees())));
            Ty2m.setText(String.valueOf((int) (radToDMSLon.getMinut())));
            Ty2s.setText(Double.toString(radToDMSLon.getSecond()).replace(".", ","));
            ThWGS84.setText(Double.toString(ck42towgs84.getAltitude84()));

        } catch (NumberFormatException e) {
            //e.printStackTrace();
            dm.alert();
        }
    }

    public void onClick_dov(ActionEvent actionEvent) {
        dm.hd = "Перетворення координат СК-42 в WGS-84";
        dm.ct = "Координати СК-42 (проєкція Гауса-Крюгера) записуються в форматі в форматі X Y H. \n" +
                "Геодезичні координати WGS-8 виводяться в форматі - B L H.";
        dm.dov = dov;
        dm.dovButton();
    }

    public void onClick_back(ActionEvent actionEvent) throws IOException {
        pb.back = back;
        pb.backButton();
    }
}
