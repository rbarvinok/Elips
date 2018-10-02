package ua.elips;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import ua.elips.interfaces.impls.CollectionGapTable;
import ua.elips.objects.Gap;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/sample.fxml"));
        primaryStage.getIcons().add(new Image(getClass().getResource("/images/znakukr.png").toExternalForm()));

        primaryStage.setTitle("Еліпс");
        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(550);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();



    }






    public static void main(String[] args) {
        launch(args);
    }
}
