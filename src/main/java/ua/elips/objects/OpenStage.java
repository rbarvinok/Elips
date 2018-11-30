package ua.elips.objects;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class OpenStage {
    public String viewURL;
    public String imageUR = "/images/znakukr.png";
    public String title;

    public void openStage() throws IOException {

        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(viewURL));
        Parent root1 = (Parent) fxmlLoader.load();
        stage.setTitle(title);
        stage.getIcons().add(new Image(getClass().getResourceAsStream(imageUR)));
        stage.setScene(new Scene(root1));
        stage.show();
    }
}
