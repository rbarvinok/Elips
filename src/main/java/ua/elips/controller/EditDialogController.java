package ua.elips.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ua.elips.interfaces.impls.CollectionGapTable;
import ua.elips.objects.Calculate;
import ua.elips.objects.DialogManeger;
import ua.elips.objects.Gap;

import static ua.elips.objects.Calculate.xGap;
import static ua.elips.objects.Calculate.yGap;

public class EditDialogController {
    DialogManeger dm = new DialogManeger();
    private Calculate calc = new Calculate();

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
        GetXYgap();
        gap.setId(calc.calculateId());
        gap.setX(txtX.getText());
        gap.setY(txtY.getText());
        gap.setD(calc.calculateDGap());
        gap.setA(calc.calculateAGap());
        gap.setdD(calc.calculateDd());
        gap.setdB(calc.calculateDb());
        actionClose(actionEvent);
    }

    public void GetXYgap() {
        xGap = Double.parseDouble(txtX.getText());
        yGap = Double.parseDouble(txtY.getText());
    }

    private boolean checkValues() {
        if (txtX.getText().trim().length() == 0 || txtY.getText().trim().length() == 0) {
            dm.alert();
            return false;
        }
        return true;
    }

}

