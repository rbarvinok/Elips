package ua.elips.geoProblem.controllerGeo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import ua.elips.geoProblem.OgzCK42;
import ua.elips.objects.PushButton;

import java.io.IOException;

public class ControllerOgzCK42 {
    @FXML
    public TextField Tx1, Ty1, Tx2, Ty2, a, d;
    @FXML
    public Button btnok, btnx1, btnx2, back, dov;


    PushButton pb = new PushButton();

    public void onClick_btnx1(ActionEvent actionEvent) {
        Tx1.setText("");
        Ty1.setText("");
    }

    public void onClick_btnx2(ActionEvent actionEvent) {
        Tx2.setText("");
        Ty2.setText("");
    }

    public void onClick_btnOk(ActionEvent actionEvent) {

        try {
            Double x1 = Double.parseDouble(Tx1.getText().replaceAll(",", "."));
            Double y1 = Double.parseDouble(Ty1.getText().replace(",", "."));
            Double x2 = Double.parseDouble(Tx2.getText().replace(",", "."));
            Double y2 = Double.parseDouble(Ty2.getText().replace(",", "."));

            OgzCK42 ogz = new OgzCK42(x1, y1, x2, y2);

            d.setText(Double.toString(ogz.getDistance()).replace(".", ","));
            a.setText(Double.toString(ogz.getAngle()).replace(".", ","));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            pb.alert();
        }

    }

    public void onClick_back(ActionEvent actionEvent) throws IOException {
        pb.back = back;
        pb.backButton();
    }

    public void onClick_dov(ActionEvent actionEvent) {
        pb.hd = "Обернена геодезична задача";
        pb.ct = "По відомим координатам двох точок розраховується довжина лінії між цими точками та дирекційний кут цієї лінії";
        pb.dov = dov;
        pb.dovButton();
    }
}