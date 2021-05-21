/*
PCM - CARLOS PAREDES FERNÁNDEZ
Main class to launch the javafx project
GITHUB: https://github.com/carloparfe/FinalProjectPCMFX
 */

package fx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("PHYSIOTHERAPY CLINIC MANAGEMENT");
        primaryStage.setScene(new Scene(root, 1020, 400));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
