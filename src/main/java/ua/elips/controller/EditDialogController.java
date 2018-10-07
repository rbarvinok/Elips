package ua.elips.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ua.elips.objects.DialogManeger;
import ua.elips.objects.Gap;

import java.net.URL;

import java.util.ResourceBundle;

public class EditDialogController {
    DialogManeger dm = new DialogManeger();

    @FXML
    private TextField txtX;
    @FXML
    private TextField txtY;

    private Gap gap;

    public void setGap(Gap gap) {
        if (gap == null) {
            return;
        }
        this.gap = gap;
        txtX.setText(gap.getX());
        txtY.setText(gap.getY());
    }

    public Gap getGap() {
        return gap;
    }

    public void actionClose(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.hide();
    }

    public void actionSave(ActionEvent actionEvent) {
        if (!checkValues()) {
            return;
        }

        try {
            Double.parseDouble(txtX.getText());
            Double.parseDouble(txtY.getText());
        } catch (NumberFormatException e) {
            dm.alert();
            return;
        }

        gap.setX(txtX.getText());
        gap.setY(txtY.getText());
        actionClose(actionEvent);
    }

    private boolean checkValues() {
        if (txtX.getText().trim().length() == 0 || txtY.getText().trim().length() == 0) {
            dm.alert();
            return false;
        }
        return true;
    }

}

