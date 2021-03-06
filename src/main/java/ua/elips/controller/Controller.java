package ua.elips.controller;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ua.elips.geoProblem.controllerGeo.ControllerGeo;
import ua.elips.interfaces.impls.CollectionGapTable;
import ua.elips.objects.*;

import java.awt.*;
import java.io.*;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

import static ua.elips.objects.Calculate.*;
import static ua.elips.objects.Clear.clear;

public class Controller extends Observable implements Initializable {

    public static CollectionGapTable gapTableImpl = new CollectionGapTable();
    private DialogManeger dm = new DialogManeger();
    public static Calculate calc = new Calculate();
    private ControllerGeo controllerGeo = new ControllerGeo();
    private OpenStage os = new OpenStage();
    private SaveToWorld stw = new SaveToWorld();

    private Stage mainStage;
    private static final String FXML_EDIT = "/view/edit.fxml";
    public static int count;

    @FXML
    public TextField x_Vp, y_Vp, h_Vp, x_cgr, y_cgr, a_cgr, d_cgr;
    @FXML
    public TextField _vd, _vb;
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
    @FXML
    public TableColumn<Gap, String> col_y;
    @FXML
    public TableColumn<Gap, String> col_d;
    @FXML
    public TableColumn<Gap, String> col_a;
    @FXML
    public TableColumn<Gap, String> col_dd;
    @FXML
    public TableColumn<Gap, String> col_db;

    private Parent fxmlEdit;
    private FXMLLoader fxmlLoader = new FXMLLoader();
    private EditDialogController editDialogController;
    private Stage editDialogStage;
    private ObservableList<Gap> backupList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        col_id.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        col_x.setCellValueFactory(cellData -> cellData.getValue().xProperty());
        col_y.setCellValueFactory(cellData -> cellData.getValue().yProperty());
        col_d.setCellValueFactory(cellData -> cellData.getValue().dProperty());
        col_a.setCellValueFactory(cellData -> cellData.getValue().aProperty());
        col_dd.setCellValueFactory(cellData -> cellData.getValue().dDProperty());
        col_db.setCellValueFactory(cellData -> cellData.getValue().dBProperty());

        initListeners();
        fillData();
        initLoader();
    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    private void fillData() {
        fillTable();
    }

    private void fillTable() {
        backupList = FXCollections.observableArrayList();
        backupList.addAll(gapTableImpl.getGapList());
        tableGap.setItems(gapTableImpl.getGapList());
    }

    private void initListeners() {
        // слушает изменения координат ВП
        // слушает изменения в коллекции для обновления надписи "Кол-во записей"
        gapTableImpl.getGapList().addListener(new ListChangeListener<Gap>() {
            @Override
            public void onChanged(Change<? extends Gap> c) {
                updateCount();
            }
        });

        // слушает двойное нажатие для редактирования записи
        tableGap.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                editDialogController.setGap((Gap) tableGap.getSelectionModel().getSelectedItem());
                showDialog();
            }
        });
    }

    public void updateCount() {
        count = gapTableImpl.getGapList().size();
        gapCount.setText(Integer.toString(count));

        calc.GetCount(count);
        gapTableImpl.CoordCgr();
    }

    private void initLoader() {
        try {
            fxmlLoader.setLocation(getClass().getResource(FXML_EDIT));
            fxmlEdit = fxmlLoader.load();
            editDialogController = fxmlLoader.getController();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void actionButtonPressed(ActionEvent actionEvent) {

        Object source = actionEvent.getSource();

        // если нажата не кнопка - выходим из метода
        if (!(source instanceof Button)) {
            return;
        }

        Gap selectedGap = (Gap) tableGap.getSelectionModel().getSelectedItem();


        Button clickedButton = (Button) source;

        switch (clickedButton.getId()) {
            case "btnAdd":
                textFieldUpdate();
                editDialogController.setGap(new Gap());
                showDialog();
                gapTableImpl.add(editDialogController.getGap());
                textFieldUpdate();
                updateTable();
                break;
            case "tAdd":
                textFieldUpdate();
                editDialogController.setGap(new Gap());
                showDialog();
                gapTableImpl.add(editDialogController.getGap());
                textFieldUpdate();
                break;

            case "btnEdit":
                textFieldUpdate();
                if (!gapIsSelected(selectedGap)) {
                    return;
                }
                editDialogController.setGap(selectedGap);
                showDialog();
                textFieldUpdate();
                break;
            case "tEdit":
                if (!gapIsSelected(selectedGap)) {
                    return;
                }
                editDialogController.setGap(selectedGap);
                showDialog();
                textFieldUpdate();
                break;

            case "btnDelete":
                if (!gapIsSelected(selectedGap)) {
                    return;
                }
                gapTableImpl.delete(selectedGap);
                textFieldUpdate();

                break;
            case "tDelete":
                if (!gapIsSelected(selectedGap)) {
                    return;
                }
                gapTableImpl.delete(selectedGap);
                textFieldUpdate();
                break;
        }

    }

    private boolean gapIsSelected(Gap selectedGap) {
        if (selectedGap == null) {
            dm.showInfoDialog("Помилка", "Не вибрано елемент для редагування чи видалення");
            return false;
        }
        return true;
    }

    private void showDialog() {

        if (editDialogStage == null) {
            editDialogStage = new Stage();
            editDialogStage.setTitle("Редагування даних");
            editDialogStage.getIcons().add(new Image(getClass().getResource("/images/znakukr.png").toExternalForm()));
            editDialogStage.setMinHeight(150);
            editDialogStage.setMinWidth(300);
            editDialogStage.setResizable(false);
            editDialogStage.setScene(new Scene(fxmlEdit));
            editDialogStage.initModality(Modality.WINDOW_MODAL);
            editDialogStage.initOwner(mainStage);
        }

        editDialogStage.showAndWait(); // для ожидания закрытия окна

    }

    /////////////////////////////////////////////////////////////////////////////////////
    public void textFieldUpdate() {
        try {
            updateCoordinateVP();
        } catch (NumberFormatException e) {
            dm.alert();
            return;
        }
        gapTableImpl.CalculatePowDdDb();
        x_cgr.setText(calc.calculateXcgr().replace(".", ","));
        y_cgr.setText(calc.calculateYcgr().replace(".", ","));
        a_cgr.setText(calc.calculateAcgr().replace(".", ","));
        d_cgr.setText(calc.calculateDcgr().replace(".", ","));
        _vd.setText(calc.calculateVd().replace(".", ","));
        _vb.setText(calc.calculateVb().replace(".", ","));
        updateTable();
    }

    public void onClick_OK(ActionEvent actionEvent) {
        textFieldUpdate();
        updateTable();
    }

    public void updateTable() {
        tableGap.refresh();
//        _vd.setText(calc.calculateVd().replace(".", ","));
//        _vb.setText(calc.calculateVb().replace(".", ","));
//        tableGap.getColumns().get(5).setVisible(false);
//        tableGap.getColumns().get(5).setVisible(true);
    }

    public void updateCoordinateVP() {
        Double xVp = Double.parseDouble(x_Vp.getText().replace(",", "."));
        Double yVp = Double.parseDouble(y_Vp.getText().replace(",", "."));
        calc.UpdateXYvp(xVp, yVp);
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

    public void onClickGeoButton(ActionEvent actionEvent) throws IOException {
        os.viewURL = "/viewGeo/sampleGeo.fxml";
        os.title = "Геодезичні задачі";
        os.openStage();
    }

    public void onClick_menuPGZ(ActionEvent actionEvent) throws IOException {
        controllerGeo.ck42PGZ();
    }

    public void onClick_menuOGZ(ActionEvent actionEvent) throws IOException {
        controllerGeo.ck42OGZ();
    }

    public void onClick_menuOGZ84(ActionEvent actionEvent) throws IOException {
        controllerGeo.WGS84OGZ();
    }

    public void onClick_menuPGZ84(ActionEvent actionEvent) throws IOException {
        controllerGeo.WGS84PGZ();
    }

    public void onClick_menuCKtoWGS(ActionEvent actionEvent) throws IOException {
        controllerGeo.konvertCK42toWGS84();
    }

    public void onClick_menuWGStoCK(ActionEvent actionEvent) throws IOException {
        controllerGeo.konvertWGS84toCK42();
    }

    public void OnClickNew(ActionEvent actionEvent) {
        clear(x_Vp, y_Vp, h_Vp);
        gapTableImpl.getGapList().clear();
    }

    public void OnClickSave(ActionEvent actionEvent) throws IOException {
        //saveFromFile();
        stw.toWord();
    }

    private void saveFromFile() throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Еліпс. Збереження файлу");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Текст", "*.txt"),
                new FileChooser.ExtensionFilter("Microsoft Word", "*.doc"));
        File outFile = fileChooser.showSaveDialog(new Stage());

        if (outFile != null) {
            try {
                try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(outFile)))) {
                    writer.println("Координати вогневої позиції: \n" +
                            " X = " + Double.parseDouble(x_Vp.getText()) + ", Y = " + yVp +
                            "\nКількість пострілів - " + count + "\n\nКоординати розривіів:");
                    int i = 0;
                    for (Gap gap : gapTableImpl.getGapList()) {
                        i++;
                        writer.println(i + ") X = " + gap.getX() + "; Y = " + gap.getY() + "; Д вп-р = " + gap.getD() + "; А вп-р = " + gap.getA());
                    }
                    writer.println();
                    writer.println("Центр групи розривів: \n\nКоординати: \nX = " + x_cgr.getText() + ", Y = " + y_cgr.getText());
                    writer.println("Середня лінія стрільби: \nД =  " + d_cgr.getText() + ",   Кут = " + a_cgr.getText());
                    writer.println();
                    writer.println("Відхилення: \n Вд = " + vd + " \n Вб = " + vb);
                    writer.flush();
                    writer.close();
                }
            } catch (IOException ex) {
                dm.hd = "Пустий файл";
                dm.ct = "Неможливо зберегти пустий файл";
                dm.alert();
                ex.printStackTrace();

            }
        }
    }

    public void onClickOpenFile(ActionEvent actionEvent) throws IOException {
        Desktop desktop = Desktop.getDesktop();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Еліпс. Відкриття файлу");
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("*.*", "*.*"),
                new FileChooser.ExtensionFilter(".txt", "*.txt"),
                new FileChooser.ExtensionFilter("*.doc", "*.doc"));
        File selectedFile = fileChooser.showOpenDialog(new Stage());
        if (selectedFile != null) {
            desktop.open(selectedFile);
        }
    }

    public void onClickChartsButton(ActionEvent actionEvent) throws IOException {
        os.viewURL = "/view/scchart.fxml";
        os.title = "Графік розривів";
        os.openStage();
    }
}
