package WillHero;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {

    @FXML
    private Button newGame,loadGame,viewHighScore,inputName,exitGame,startGame,backButton;

    @FXML
    private Label greeting;

    @FXML
    private TextField name;

    @FXML
    private AnchorPane WelcomePane;

    @FXML
    private AnchorPane newGamePane;

    public void newGame(ActionEvent e) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("newGame.fxml"));
        Stage stage=(Stage)((Node)e.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    public void loadGame(ActionEvent e) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("loadGame.fxml"));
        Stage stage=(Stage)((Node)e.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }
    public void startGame(ActionEvent e)throws IOException{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("GameScene.fxml"));
        Parent root=loader.load();
        Hero controller=loader.getController();
        Scene scene=new Scene(root,653,653);
        controller.game_loop();
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if(keyEvent.getCode()== KeyCode.D)
                {
                    controller.move();
                }

            }
        });
        Stage stage=(Stage)((Node)e.getSource()).getScene().getWindow();
        stage.setScene(scene);
    }

    public void back(ActionEvent e) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("WelcomeScreen.fxml"));
        Stage stage=(Stage)((Node)e.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }
    public void inputName(ActionEvent e)throws IOException{
        greeting.setText("Hii "+name.getText()+"!! Now press Start to start the game");
    }

    public void exitGame(ActionEvent e){
        Alert exitAlert=new Alert(Alert.AlertType.CONFIRMATION);
        exitAlert.setTitle("Exit");
        exitAlert.setContentText("You're about to Exit !!");
        exitAlert.setHeaderText("Are you sure you want to Exit?");
        if(exitAlert.showAndWait().get()== ButtonType.OK){
            Stage stage=(Stage)WelcomePane.getScene().getWindow();
            System.out.println("You successfully exited");
            stage.close();
        }
    }
}
