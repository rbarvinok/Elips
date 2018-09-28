package ua.elips.objects;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;

public class DialogManeger {
    public String hd, ct;
    public Button dov;

    public void dovButton() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Довідка");
        alert.setHeaderText(hd);
        alert.setContentText(ct);
        alert.showAndWait();
    }

    public void alert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setTitle("Помилка");
        alert.setHeaderText("Невірний формат даних");
        alert.setContentText("Поля для вводу \n - не можуть бути пустими; \n - повинні містити тільки цифри");
        alert.showAndWait();
    }

}
