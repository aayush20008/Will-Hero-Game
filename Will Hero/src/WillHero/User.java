package WillHero;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {

    @FXML
    private Label greeting,chosenUser;

    @FXML
    private TextField name;

    private String _name;

    @FXML
    private Button inputName,startGame,backButton;

    @FXML
    private AnchorPane newGamePane;

    @FXML
    private ListView userHistory;

    private Label onScreen_score;

    private int score;
    private Game game;
    private Hero hero;

    private int resurrectionCount;

    public User(Game game){
        this.game=game;
        score=0;
        _name="Guest";
        resurrectionCount=0;
    }
    public void save(){
        game.saveGame();
    }
    public void load(){
        try{
            DataBase.deserialize();
            displayUsers();
            selectUsers();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String get_name() {
        return _name;
    }

    public int getScore() {
        return score;
    }

    public int getHighScore(){return game.getHighscore();}

    public double get_Herox(){
        return hero.getHero().getX();
    }

    public double get_HeroY(){
        return hero.getHero().getY();
    }

    public ArrayList<Weapons> get_HeroWeapons(){
        return hero.getWeapons();
    }

    public double get_FrameX(){
        return hero.get_FrameX();
    }

    public void setScore(int score){
        if(score>game.getHighscore())
            game.setHighscore(score);
        this.score=score;
    }

    public void addIsland(AnchorPane movingPane){
        ArrayList<ImageView> islands=game.getPlatform();
        for(ImageView isl:islands) {
            System.out.println(isl.getBoundsInParent());
            movingPane.getChildren().add(isl);
        }
    }
    public void addObjects(AnchorPane movingPane){

        try {
            int i = 0;
            while (game.getObj(i) != null) {
                movingPane.getChildren().add(game.getObj(i++));
            }
        }
        catch (IllegalArgumentException e){
            System.out.println("Error");
        }
    }

    public void back(ActionEvent e) throws IOException {
        Parent root= FXMLLoader.load(getClass().getResource("WelcomeScreen.fxml"));
        Stage stage=(Stage)((Node)e.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
    }

    public void inputName(ActionEvent e)throws IOException{
        greeting.setText("Hii "+name.getText()+"!! Now press Start to start the game");
        _name=greeting.getText();
    }


    public int getResurrectionCount() {
        return resurrectionCount;
    }

    public void setResurrectionCount(int resurrectionCount) {
        this.resurrectionCount = resurrectionCount;
    }

    public void startGame(ActionEvent e)throws IOException{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("GameScene.fxml"));
        hero=new Hero(this,game);
        loader.setController(hero);
        Parent root=loader.load();
        Scene scene=new Scene(root,653,653);
        game.setCurrentScene(scene);
        tap(loader,e);
    }

    public void startLoadedGame(ActionEvent e)throws IOException{
//        FXMLLoader loader=new FXMLLoader(getClass().getResource("GameScene.fxml"));
//        hero=new Hero(this,game);
//        loader.setController(hero);
//        DataBase d=SavedGames.getGame(_name);
//        if(d==null){
//            System.out.println("User Cannot be loaded");
//            return;
//        }
//        Parent root=loader.load();
//        Scene scene=new Scene(root,653,653);
//        game.setCurrentScene(scene);
//        tap(loader,e);
        //hero.resume(d);
        startGame(e);

    }

    public void tap(FXMLLoader loader,ActionEvent e){
        Scene scene=game.getCurrentScene();
        Hero controller=loader.getController();
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
        stage.setMaximized(true);
    }


    public void displayUsers()throws IOException{
        int i=0;
        for(DataBase d: SavedGames.display()){
            System.out.println((i)+"-->"+d.getName());
            userHistory.getItems().add((i++)+"-->"+d.getName());
        }
    }

    public Hero getHero(){
        return hero;
    }

    public void killHero() throws IOException {
        hero.getGameloop().pause();
        game.saveGame();
        inGameMenuController controller=new inGameMenuController(this);
        FXMLLoader loader=new FXMLLoader(getClass().getResource("Killed.fxml"));
        loader.setController(controller);
        Parent root=loader.load();
        Stage primary=hero.getStage();
        stage=primary;
        Stage stage1=new Stage();
        stage1.setScene(new Scene(root));
        stage1.setOnHidden(e->hero.getGameloop().play());
        stage1.show();
        controller.setScores();

    }

    private Stage stage;
    public Stage getStage(){
        return stage;
    }

    public void selectUsers()throws IOException{
        userHistory.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                String s=(String)t1;
                chosenUser.setText(s.substring(s.indexOf('>')+1));
                _name=chosenUser.getText();
            }
        });
    }




}
