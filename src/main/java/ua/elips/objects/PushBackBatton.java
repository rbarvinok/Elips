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
        //  Открытие нового окна
      /* FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/sample/sample.fxml"));
       Parent root1=(Parent) fxmlLoader.load();
       stage = new Stage();
       stage.setTitle("ОГЗ - ПГЗ");
       stage.getIcons().add(new Image(getClass().getResource("/images/sample/znakukr.png").toExternalForm()));
       stage.setScene(new Scene(root1));
       root1.getStylesheets().add(getClass().getResource(change_css()).toExternalForm());
       stage.show();*/
    }

}
