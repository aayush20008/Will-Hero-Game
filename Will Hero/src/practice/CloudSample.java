package practice;


import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.WritableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.io.IOException;



public class CloudSample extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void initialize() {
        DoubleProperty xPosition = new SimpleDoubleProperty(0);
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(xPosition, 0)),
                new KeyFrame(Duration.seconds(200), new KeyValue(xPosition, -15000))
        );
        timeline.play();
    }

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Sample.fxml"));
        Scene scene=new Scene(root,600,400);
        stage.setScene(scene);
        stage.setTitle("Will Hero");
        stage.setResizable(false);

        Image icon=new Image("Assets/icon.jpg");
        stage.getIcons().add(icon);
        stage.show();
    }
}