package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Application.setUserAgentStylesheet(Application.STYLESHEET_CASPIAN);
        //Application.setUserAgentStylesheet(Application.STYLESHEET_MODENA);
        Parent root = FXMLLoader.load(getClass().getResource("views/escribirCorreo.fxml"));

        Scene scene = new Scene(root,800,800);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void setStage(Stage stage) {
    }

    public static void main(String[] args) {
        launch(args);
    }
}
