package ua.elips.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ua.elips.geoProblem.OgzCK42;
import ua.elips.interfaces.impls.CollectionGapTable;
import ua.elips.objects.Calculate;
import ua.elips.objects.DialogManeger;
import ua.elips.objects.Gap;

import java.io.IOException;

public class Controller {

    @FXML
    public TextField x_Vp, y_Vp, x_cgr, y_cgr, a_cgr, d_cgr;
    @FXML
    public TextField vd, vb;
    @FXML
    public Button btnOk;
    @FXML
    public TextField gapCount;
    @FXML
    public MenuItem mOgzCK42, mPgzCK42, mPgzWGS, mOgzWGS;
    @FXML
    public TableView<Gap> tableGap;
    @FXML
    public TableColumn<Gap, Integer> col_id;
    @FXML
    public TableColumn<Gap, String> col_x;
    public TableColumn<Gap, String> col_y;
    public TableColumn<Gap, String> col_d;
    public TableColumn<Gap, String> col_a;
    public TableColumn<Gap, String> col_dd;
    public TableColumn<Gap, String> col_db;

    DialogManeger dm = new DialogManeger();
    CollectionGapTable gapTableImpl = new CollectionGapTable();
    Calculate calc = new Calculate();


    public void initialize() {
//        col_id.setCellValueFactory(new PropertyValueFactory<Gap, Integer>("id"));
//        col_x.setCellValueFactory(new PropertyValueFactory<Gap, String>("x"));
//        col_y.setCellValueFactory(new PropertyValueFactory<Gap, String>("y"));
//        col_d.setCellValueFactory(new PropertyValueFactory<Gap, String>("d"));
//        col_a.setCellValueFactory(new PropertyValueFactory<Gap, String>("a"));
//        col_dd.setCellValueFactory(new PropertyValueFactory<Gap, String>("dd"));
//        col_db.setCellValueFactory(new PropertyValueFactory<Gap, String>("db"));
        col_id.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        col_x.setCellValueFactory(cellData -> cellData.getValue().xProperty());
        col_y.setCellValueFactory(cellData -> cellData.getValue().yProperty());
        col_d.setCellValueFactory(cellData -> cellData.getValue().dProperty());
        col_a.setCellValueFactory(cellData -> cellData.getValue().aProperty());
        col_dd.setCellValueFactory(cellData -> cellData.getValue().ddProperty());
        col_db.setCellValueFactory(cellData -> cellData.getValue().dbProperty());

        gapTableImpl.print();
        gapTableImpl.fillTestData();
        tableGap.setItems(gapTableImpl.getGapList());
        updateCount();
    }

    private void updateCount() {
        gapCount.setText(Integer.toString(gapTableImpl.getGapList().size()));
    }

    //Gap selectedGap = (Gap) tableGap.getSelectionModel().getSelectedItem();


    public void onClick_OK(ActionEvent actionEvent) {

        try {
            Double xVp = Double.parseDouble(x_Vp.getText().replace(",", "."));
            Double yVp = Double.parseDouble(y_Vp.getText().replace(",", "."));
        } catch (NumberFormatException e) {
            dm.alert();
        }

        x_cgr.setText(calc.calculateXcgr());
        y_cgr.setText(calc.calculateYcgr());
        a_cgr.setText(calc.calculateAcgr());
        d_cgr.setText(calc.calculateDcgr());
        vd.setText(calc.calculateVd());
        vb.setText(calc.calculateVb());


    }

    public void onClick_btnPlas(ActionEvent actionEvent) {
    }


    public void onClick_menuExit(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void onClick_menuAbaout(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/about.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void onClick_menuPGZ(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/viewGeo/samplePgzCK42.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        //stage = new Stage();
        stage.setTitle("Пряма геодезична задача");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/znakukr.png")));
        stage.setScene(new Scene(root1));
        stage.show();
    }

    public void onClick_menuOGZ(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/viewGeo/sampleOgzCK42.fxml"));
        Parent root2 = (Parent) fxmlLoader.load();
        //stage = new Stage();
        stage.setTitle("Обернена геодезична задача");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/znakukr.png")));
        stage.setScene(new Scene(root2));
        stage.show();
    }

    public void onClick_menuOGZ84(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/viewGeo/sampleOgzWGS84.fxml"));
        Parent root2 = (Parent) fxmlLoader.load();
        //stage = new Stage();
        stage.setTitle("Обернена геодезична задача");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/znakukr.png")));
        stage.setScene(new Scene(root2));
        stage.show();
    }

    public void onClick_menuPGZ84(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/viewGeo/samplePgzWGS84.fxml"));
        Parent root2 = (Parent) fxmlLoader.load();
        //stage = new Stage();
        stage.setTitle("Пряма геодезична задача");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/znakukr.png")));
        stage.setScene(new Scene(root2));
        stage.show();
    }


}
