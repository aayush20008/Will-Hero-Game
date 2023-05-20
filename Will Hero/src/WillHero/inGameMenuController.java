package WillHero;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;


import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class inGameMenuController implements Initializable {

    @FXML
    private ImageView gamePause;

    @FXML
    private Button resume,Home;

    @FXML
    private Label currentScore,highScore;

    private User user;

    public inGameMenuController(User user){
        this.user=user;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FadeTransition pauseImg=new FadeTransition();
        pauseImg.setNode(gamePause);
        pauseImg.setDuration(Duration.millis(500));
        pauseImg.setCycleCount(TranslateTransition.INDEFINITE);
        pauseImg.setFromValue(0.5);
        pauseImg.setToValue(1);
        pauseImg.setAutoReverse(true);
        pauseImg.play();
    }

    public void setScores(){
        currentScore.setText("Current Score: "+user.getScore());
        highScore.setText("High Score: "+user.getHighScore());
    }

    public void resume(ActionEvent e) throws IOException {
//        DataBase.deserialize();
//        DataBase d =SavedGames.getLatestGame();
//        user.startGame(e);
//        user.getHero().resume(d);
        Stage stage=(Stage)gamePause.getScene().getWindow();
        stage.close();
    }
    public void back2Home(ActionEvent e) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("WelcomeScreen.fxml"));
        Stage stage=(Stage)((Node)e.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    public void saveGame(ActionEvent e)throws IOException{
        user.save();
    }

    public void restart(ActionEvent e)throws IOException{
        user.startGame(e);
    }

    public void resurrect(ActionEvent e)throws IOException{
        if(user.getScore()>10 && user.getResurrectionCount()<1) {
            System.out.println("RCount: "+user.getResurrectionCount());
            user.setScore(user.getScore() - 10);
            user.setResurrectionCount(user.getResurrectionCount()+1);
            Stage stage=(Stage) currentScore.getScene().getWindow();
            stage.close();

        }
        else{
            back2home(user.getStage());
        }

    }

    public void back2home(Stage stage) throws IOException {
        Alert noCoins=new Alert(Alert.AlertType.CONFIRMATION);
        noCoins.setTitle("InSufficient Coins!!");
        noCoins.setContentText("You don't have sufficient coins to resurrect");
        noCoins.setHeaderText("Do you want to go to HomeScreen??");
        if(noCoins.showAndWait().get()== ButtonType.OK){
            FXMLLoader loader=new FXMLLoader(getClass().getResource("WelcomeScreen.fxml"));
            Parent root=loader.load();
            Stage stage1= user.getStage();
            stage1.setScene(new Scene(root));
        }
    }

}
