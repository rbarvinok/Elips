package ua.elips.objects;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class PushButton {
    public Button back,dov;
    public String hd,ct,urlcss;
    //RadioMenuItem st, tt;
    public int tema;

   public void backButton() throws IOException {
       //Закрытие окна
       Stage stage=(Stage) back.getScene().getWindow();
       stage.close();
       //  Открытие нового окна
       FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/sample.fxml"));
       Parent root = (Parent) fxmlLoader.load();
       stage = new Stage();
       stage.setTitle("ОГЗ - ПГЗ");
       stage.getIcons().add(new Image(getClass().getResource("/images/america_ico.png").toExternalForm()));
       stage.setScene(new Scene(root));
       root.getStylesheets().add(getClass().getResource(change_css()).toExternalForm());
       stage.show();
   }

       public void dovButton(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        /*Stage stage = (Stage) alert.getDialogPane().getScen().getWindow();
        stage.getIcons().add(new Image(getClass().getResource("images/america_ico.png").toExternalForm()));*/
        alert.setTitle("Довідка");
        alert.setHeaderText(hd);
        alert.setContentText(ct);
        alert.showAndWait();
              }

    public void alert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);

        alert.setTitle("Помилка");
        alert.setHeaderText("Невірний формат даних");
        alert.setContentText("Поля для вводу \n - не можуть бути пустими; \n - повинні містити тільки цифри; \n - дирекційний кут не може бути більшим 60 кутових поділок");
        alert.showAndWait();
    }

    public String change_css() {
        if (tema==0)
           urlcss= "/styles/day.css";
        else if (tema==1)urlcss= "/styles/night.css";
        return urlcss;
    }

}
