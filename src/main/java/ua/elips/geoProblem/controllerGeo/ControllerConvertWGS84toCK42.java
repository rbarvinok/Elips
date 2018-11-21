package ua.elips.geoProblem.controllerGeo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import ua.elips.geoProblem.ConverterCoordinateSystem;
import ua.elips.geoProblem.ConverterCoordinates;
import ua.elips.objects.DialogManeger;
import ua.elips.objects.PushBackBatton;
import ua.elips.objects.Clear;
import java.io.IOException;

public class ControllerConvertWGS84toCK42 {

    @FXML
    public TextField Tx, Ty, ThCK42, Tn;
    @FXML
    public TextField Txd, Txm, Txs;
    @FXML
    public TextField Tyd, Tym, Tys;
    @FXML
    public TextField ThWGS84;
    @FXML
    public Button btnok, btnx1, back, dov;

    PushBackBatton pb = new PushBackBatton();
    DialogManeger dm = new DialogManeger();

    public void onClick_btnx1(ActionEvent actionEvent) {
        Clear.clear(Txd, Txm, Txs, Tyd, Tym, Tys, ThWGS84);
    }

    public void onClick_btnOk(ActionEvent actionEvent) {

        try {
            Double b1d = Double.parseDouble(Txd.getText().replace(",", "."));
            Double b1m = Double.parseDouble(Txm.getText().replace(",", "."));
            Double b1s = Double.parseDouble(Txs.getText().replace(",", "."));

            Double l1d = Double.parseDouble(Tyd.getText().replace(",", "."));
            Double l1m = Double.parseDouble(Tym.getText().replace(",", "."));
            Double l1s = Double.parseDouble(Tys.getText().replace(",", "."));

            Double latitude = Math.toRadians(b1d + b1m / 60 + b1s / 3600);
            Double longitude = Math.toRadians(l1d + l1m / 60 + l1s / 3600);
            Double altitude = Double.parseDouble(ThWGS84.getText().replace(",", "."));

            ConverterCoordinates wgs84toCk42 = new ConverterCoordinates();
            wgs84toCk42.Wgs84ToCk42Converter(latitude, longitude, altitude);

            ConverterCoordinateSystem blHtoXYH = new ConverterCoordinateSystem();
            blHtoXYH.BLHtoGK(wgs84toCk42.getLatitude42(), wgs84toCk42.getLongitude42(), wgs84toCk42.getAltitude42());
            Tx.setText(Double.toString(blHtoXYH.getGK_x()));
            Ty.setText(Double.toString(blHtoXYH.getGK_y()));
            ThCK42.setText(Double.toString(wgs84toCk42.getAltitude42()));
            //Tn.setText(Double.toString(Math.rint(blHtoXYH.getN())));
            Tn.setText(String.valueOf((int) (Math.rint(blHtoXYH.getN()))));

        } catch (NumberFormatException e) {
            e.printStackTrace();
            dm.alert();
        }
    }

    public void onClick_dov(ActionEvent actionEvent) {
        dm.hd = "Перетворення координат  WGS-84 в СК-42";
        dm.ct = "Геодезичні координати WGS-8 записуються в форматі - B L H. \n" +
                "Координати СК-42 (проєкція Гауса-Крюгера) виводяться в форматі в форматі X Y H. ";
        dm.dov = dov;
        dm.dovButton();
    }

    public void onClick_back(ActionEvent actionEvent) throws IOException {
        pb.back = back;
        pb.backButton();
    }
}
