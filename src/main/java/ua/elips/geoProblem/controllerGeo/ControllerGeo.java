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
import java.io.IOException;

public class ControllerGeo {

    @FXML
    public TextArea ta;
    @FXML
    public Button btno, btnp, btno84, btnp84, btnCKtoWGS, btnWGStoCK, back;

    public void ck42PGZ() throws IOException {
        Stage stage = (Stage) btnp.getScene().getWindow();
        stage.close();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/viewGeo/samplePgzCK42.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        //stage = new Stage();
        stage.setTitle("Пряма геодезична задача");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/znakukr.png")));
        stage.setScene(new Scene(root1));
        stage.show();

    }

    public void ck42OGZ() throws IOException {
        Stage stage = (Stage) btno.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/viewGeo/sampleOgzCK42.fxml"));
        Parent root2 = (Parent) fxmlLoader.load();
        //stage = new Stage();
        stage.setTitle("Обернена геодезична задача");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/znakukr.png")));
        stage.setScene(new Scene(root2));
        stage.show();
    }

    public void WGS84PGZ() throws IOException {
        Stage stage = (Stage) btnp84.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/viewGeo/samplePgzWGS84.fxml"));
        Parent root2 = (Parent) fxmlLoader.load();
        //stage = new Stage();
        stage.setTitle("Пряма геодезична задача");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/znakukr.png")));
        stage.setScene(new Scene(root2));
        stage.show();
    }

    public void WGS84OGZ() throws IOException {
        Stage stage = (Stage) btno84.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/viewGeo/sampleOgzWGS84.fxml"));
        Parent root2 = (Parent) fxmlLoader.load();
        //stage = new Stage();
        stage.setTitle("Обернена геодезична задача");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/znakukr.png")));
        stage.setScene(new Scene(root2));
        stage.show();
    }

    public void konvertCK42toWGS84() throws IOException {
        Stage stage = (Stage) btnCKtoWGS.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/viewGeo/sampleConvertCK42toWGS84.fxml"));
        Parent root2 = (Parent) fxmlLoader.load();
        //stage = new Stage();
        stage.setTitle("Конвертор координат");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/znakukr.png")));
        stage.setScene(new Scene(root2));
        stage.show();
    }

    public void konvertWGS84toCK42() throws IOException {
        Stage stage = (Stage) btnWGStoCK.getScene().getWindow();
        stage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/viewGeo/sampleConvertWGS84toCK42.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        //stage = new Stage();
        stage.setTitle("Конвертор координат");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/znakukr.png")));
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void onClick_btnp(ActionEvent actionEvent) throws IOException {
        ck42PGZ();
    }

    public void onClick_btno(ActionEvent actionEvent) throws IOException {
        ck42OGZ();
    }

    public void onClick_btno84(ActionEvent actionEvent) throws IOException {
        WGS84OGZ();
    }

    public void onClick_btnp84(ActionEvent actionEvent) throws IOException {
        WGS84PGZ();
    }

    public void onClick_btnCKtoWGS(ActionEvent actionEvent) throws IOException {
        konvertCK42toWGS84();
    }

    public void onClick_WGStoCK(ActionEvent actionEvent) throws IOException {
        konvertWGS84toCK42();
    }

    public void OnClickExit(ActionEvent actionEvent) throws IOException {

        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

}