package ua.elips.geoProblem.controllerGeo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import ua.elips.geoProblem.OgzWGS84;
import ua.elips.objects.Clear;
import ua.elips.objects.DialogManeger;
import ua.elips.objects.PushBackBatton;

import java.io.IOException;

public class ControllerOgzWGS84 {
    @FXML
    public TextField Tx1d, Tx1m, Tx1s;
    @FXML
    public TextField Ty1d, Ty1m, Ty1s;
    @FXML
    public TextField Tx2d, Tx2m, Tx2s;
    @FXML
    public TextField Ty2d, Ty2m, Ty2s;
    @FXML
    public TextField a, d;
    @FXML
    public Button btnok, btnx1, btnx2, back, dov;

    PushBackBatton pb = new PushBackBatton();
    DialogManeger dm = new DialogManeger();

    public void onClick_btnx1(ActionEvent actionEvent) {
        Clear.clear(Tx1d, Tx1m, Tx1s, Ty1d, Ty1m, Ty1s);
    }

    public void onClick_btnx2(ActionEvent actionEvent) {
        Clear.clear(Tx2d, Tx2m, Tx2s, Ty2d, Ty2m, Ty2s);
    }

    public void onClick_btnOk(ActionEvent actionEvent) {

        try {
            Double b1d = Double.parseDouble(Tx1d.getText().replace(",", "."));
            Double b1m = Double.parseDouble(Tx1m.getText().replace(",", "."));
            Double b1s = Double.parseDouble(Tx1s.getText().replace(",", "."));

            Double l1d = Double.parseDouble(Ty1d.getText().replace(",", "."));
            Double l1m = Double.parseDouble(Ty1m.getText().replace(",", "."));
            Double l1s = Double.parseDouble(Ty1s.getText().replace(",", "."));

            Double b2d = Double.parseDouble(Tx2d.getText().replace(",", "."));
            Double b2m = Double.parseDouble(Tx2m.getText().replace(",", "."));
            Double b2s = Double.parseDouble(Tx2s.getText().replace(",", "."));

            Double l2d = Double.parseDouble(Ty2d.getText().replace(",", "."));
            Double l2m = Double.parseDouble(Ty2m.getText().replace(",", "."));
            Double l2s = Double.parseDouble(Ty2s.getText().replace(",", "."));

            Double latitude1 = b1d + b1m / 60 + b1s / 3600;
            Double longitude1 = l1d + l1m / 60 + l1s / 3600;
            Double latitude2 = b2d + b2m / 60 + b2s / 3600;
            Double longitude2 = l2d + l2m / 60 + l2s / 3600;

            OgzWGS84 ogz_wgs84 = new OgzWGS84(latitude1, longitude1, latitude2, longitude2);

            d.setText(Double.toString(ogz_wgs84.getDistance()).replace(".", ","));
            a.setText(Double.toString(ogz_wgs84.getAngle()).replace(".", ","));

        } catch (NumberFormatException e) {
            e.printStackTrace();
            dm.alert();
        }

    }

    public void onClick_back(ActionEvent actionEvent) throws IOException {
        pb.back = back;
        pb.backButton();
    }

    public void onClick_dov(ActionEvent actionEvent) {
        dm.hd = "Обернена геодезична задача";
        dm.ct = "По відомим координатам двох точок розраховується довжина лінії між цими точками та дирекційний кут цієї лінії";
        dm.dov = dov;
        dm.dovButton();
    }
}