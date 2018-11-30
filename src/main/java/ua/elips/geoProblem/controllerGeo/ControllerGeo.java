package ua.elips.geoProblem.controllerGeo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import ua.elips.objects.OpenStage;

import java.io.IOException;

public class ControllerGeo {

    @FXML
    public TextArea ta;
    @FXML
    public Button btno, btnp, btno84, btnp84, btnCKtoWGS, btnWGStoCK, back;
    OpenStage os = new OpenStage();

    public void ck42PGZ() throws IOException {
        os.viewURL = "/viewGeo/samplePgzCK42.fxml";
        os.title = "Пряма геодезична задача";
        os.openStage();
    }

    public void ck42OGZ() throws IOException {
        os.viewURL = "/viewGeo/sampleOgzCK42.fxml";
        os.title = "Обернена геодезична задача";
        os.openStage();
    }

    public void WGS84PGZ() throws IOException {
        os.viewURL = "/viewGeo/samplePgzWGS84.fxml";
        os.title = "Пряма геодезична задача";
        os.openStage();
    }

    public void WGS84OGZ() throws IOException {
        os.viewURL = "/viewGeo/sampleOgzWGS84.fxml";
        os.title = "Обернена геодезична задача";
        os.openStage();
    }

    public void konvertCK42toWGS84() throws IOException {
        os.viewURL = "/viewGeo/sampleConvertCK42toWGS84.fxml";
        os.title = "Конвертор координат";
        os.openStage();
    }

    public void konvertWGS84toCK42() throws IOException {
        os.viewURL = "/viewGeo/sampleConvertWGS84toCK42.fxml";
        os.title = "Конвертор координат";
        os.openStage();


    }

    public void onClick_btnp(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) btnp.getScene().getWindow();
        stage.close();
        ck42PGZ();
    }

    public void onClick_btno(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) btno.getScene().getWindow();
        stage.close();
        ck42OGZ();
    }

    public void onClick_btno84(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) btno84.getScene().getWindow();
        stage.close();
        WGS84OGZ();
    }

    public void onClick_btnp84(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) btnp84.getScene().getWindow();
        stage.close();
        WGS84PGZ();
    }

    public void onClick_btnCKtoWGS(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) btnCKtoWGS.getScene().getWindow();
        stage.close();
        konvertCK42toWGS84();
    }

    public void onClick_WGStoCK(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) btnWGStoCK.getScene().getWindow();
        stage.close();
        konvertWGS84toCK42();
    }

    public void OnClickExit(ActionEvent actionEvent) throws IOException {

        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

}