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

       // fillTestData();

    }



//    private void fillTestData(){
//        CollectionGapTable gapTable = new CollectionGapTable();
//        Gap gap = new Gap(1, "45000.9","96000.6","45.0","15.0","2.0","5.2");
//
//        Gap gap2 = new Gap(2, "45555.5","96888.3","450.0","15.5","2.5","5.8");
//        gapTable.add(gap);
//        gapTable.add(gap2);
//
//        gap.setX("111");
//
//        gapTable.delete(gap2);


    //}


    public static void main(String[] args) {
        launch(args);
    }
}
