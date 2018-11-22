package ua.elips.objects;

import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class PushBackBatton {
    public Button back;

    public void backButton() throws IOException {
        //Закрытие окна
        Stage stage = (Stage) back.getScene().getWindow();
        stage.close();

    }

}
