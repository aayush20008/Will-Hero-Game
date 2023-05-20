

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller {

    @FXML
    private Button newGame,loadGame,viewHighScore,inputName,exitGame,startGame;

    @FXML
    private Label greeting;

    @FXML
    private TextField name;

    @FXML
    private AnchorPane WelcomePane;

    final private Popup newGameScreen=new Popup();

    private Stage stage;

    public void newGame(ActionEvent e) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("newGame.fxml"));
        //newGameScreen=new Popup();
        newGameScreen.getContent().add(root);
        newGameScreen.setX(370);
        newGameScreen.setY(100);
        stage=(Stage)((Node)e.getSource()).getScene().getWindow();
        ColorAdjust adj = new ColorAdjust(0, -0.9, -0.5, 0);
        GaussianBlur blur = new GaussianBlur(55); // 55 is just to show edge effect more clearly.
        adj.setInput(blur);


        newGameScreen.setAutoHide(true);
        if (!newGameScreen.isShowing()) {
            newGameScreen.show(stage);
            WelcomePane.setEffect(new GaussianBlur());
        }

    }

    public void startGame(ActionEvent e)throws IOException{

        Parent root=FXMLLoader.load(getClass().getResource("GameScene.fxml"));
        stage=(Stage)((Node)e.getSource()).getScene().getWindow();
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
