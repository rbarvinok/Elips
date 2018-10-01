package ua.elips.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ua.elips.interfaces.impls.CollectionGapTable;
import ua.elips.objects.Gap;

import java.io.IOException;

public class Controller {

    @FXML
    public TextField Tx1, Ty1, Tx_cgr, Ty_cgr, a_cgr, d_cgr;
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

    CollectionGapTable gapTableImpl = new CollectionGapTable();

    private void initialize() {
//        col_id.setCellValueFactory(new PropertyValueFactory<Gap, Integer>("id"));
//        col_x.setCellValueFactory(new PropertyValueFactory<Gap, String>("x"));

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

    public void onClick_btnPlas(ActionEvent actionEvent) {
    }

    public void onClick_OK(ActionEvent actionEvent) {
    }


}
