package WillHero;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Hero implements Initializable{

    @FXML
    private AnchorPane staticFrame ,  movingPlatform;

    @FXML
    private Button pauseButton;

    private ImageView hero;
    private ArrayList<ImageView> orcs;
    private TranslateTransition heroJump,OrcJump;
    private boolean gameOver,gamewon;
    private User user;
    private Game game;
    private Helmet helmet;
    private MovingProp m_prop;
    private ArrayList<Orcs> collidingOrc;

    private Label onScreen_Score;

    @FXML
    private Label steps;


    Hero(User user, Game game){
        this.user=user;
        this.game=game;
        helmet=new Helmet("Fox");
        collidingOrc=new ArrayList<>();
        gameOver=false;
        m_prop=new MovingProp(0,2,0);
    }

    private Timeline gameloop;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        user.addIsland(movingPlatform);
        user.addObjects(movingPlatform);
        System.out.println("Hiii Loaded game");

        onScreen_Score=new Label();
        onScreen_Score.setAlignment(Pos.BOTTOM_RIGHT);
        onScreen_Score.setText("00");
        onScreen_Score.setFont(new Font("Times New Roman",30));
        staticFrame.getChildren().add(onScreen_Score);

        hero=new ImageView("Assets/Hero.png");
        hero.setX(0);
        hero.setY(0);
        hero.setFitWidth(40);
        hero.setFitHeight(40);
        movingPlatform.getChildren().add(hero);
    }

    public void game_loop(){
        System.out.println("HERO: "+hero.getBoundsInParent());
        gameloop=new Timeline(
                new KeyFrame(Duration.millis(10),
                        e -> {
                            if(gameOver) {
                                try {
                                    user.killHero();
                                    gameOver=false;
                                    hero.setTranslateX(hero.getTranslateX()-60);
                                    movingPlatform.setTranslateX(movingPlatform.getTranslateX()+60);
                                } catch (IOException ioException) {
                                    ioException.printStackTrace();
                                }
                            }
                            orcJump();
                            if(hero.getBoundsInParent().getMinY()>100)
                                killHero();

                            if(m_prop.getDuration() >0){
                                hero.setTranslateX(hero.getTranslateX() + m_prop.getX_vel());
                                movingPlatform.setTranslateX(movingPlatform.getTranslateX() - m_prop.getX_vel());
                                m_prop.setDuration(m_prop.getDuration()-1);
                            }
                            else{
                                jump();
                            }
                            helmet.move_weapon(movingPlatform);

                            for(Orcs o: collidingOrc){
                                if(o.getDuration()>0){
                                    o.getObject().setTranslateX(o.getObject().getTranslateX()+o.getX_vel());
                                    o.setDuration(o.getDuration()-1);
                                }
                            }
                            game.if_collision(this);

                        })
        );
        gameloop.setCycleCount(Timeline.INDEFINITE);
        gameloop.play();
    }


    public void resume(DataBase d){
        System.out.println(d.getHero_ypos()+", "+d.getHero_xpos()+", "+d.getFrame_x());
        hero.setTranslateX(hero.getTranslateX()+d.getHero_xpos());
        hero.setTranslateX(d.getHero_ypos());
        movingPlatform.setTranslateX(movingPlatform.getTranslateX()-d.getFrame_x());
        setScore(d.getScore());

    }

    public Timeline getGameloop(){
        return gameloop;
    }

    ArrayList<Weapons> getWeapons(){
        return helmet.getWeapon();
    }

    public ArrayList<Weapons> getCloneWeapons(){
        return helmet.getCloneWeapons();
    }

    public void jump(){
        if(game.platformCollision(hero)==1){
            m_prop.setY_vel(2);
        }
        else if(hero.getBoundsInParent().getMaxY() < -100) {
            m_prop.setY_vel(-2);
        }
        else{}
        hero.setTranslateY(hero.getTranslateY()-m_prop.getY_vel());
    }



    public void orcJump(){
        int i=0;
        for(Orcs o: game.getOrcs()){
            if(game.platformCollision(o.getObject())==1){
                o.sety_vel(1);
            }
            if(o.getObject().getBoundsInParent().getMaxY()<-100)
                o.sety_vel(-1);

            o.getObject().setTranslateY(o.getObject().getTranslateY()-o.gety_vel());
        }
    }

    public void addWeapon(Weapons weapon){
        helmet.store_Weapon(weapon);
        staticFrame.getChildren().add(weapon.getWeapon());
    }
    public void displaceOrc(Orcs orc){
        collidingOrc.add(orc);
        System.out.println(collidingOrc);
    }
    public int getScore(){
        return Integer.parseInt(onScreen_Score.getText());
    }

    public void setScore(int n){
        user.setScore(n);
        onScreen_Score.setText(n+"");
    }

    public ImageView getHero(){
        return hero;
    }

    public double get_FrameX(){
        return movingPlatform.getLayoutX();
    }

    public void killHero(){
        gameOver=true;
    }

    public void gameWon() throws IOException {
        gamewon=true;
        gameloop.stop();
        FXMLLoader loader=new FXMLLoader(getClass().getResource("Win.fxml"));
        inGameMenuController controller=new inGameMenuController(user);
        loader.setController(controller);
        Parent root=loader.load();
        Stage stage=(Stage)(staticFrame.getScene().getWindow());
        stage.setScene(new Scene(root));
    }

    private int count;
    public void move(){
        m_prop.setX_vel(1);
        m_prop.setDuration(30);
        helmet.throw_weapon(this,movingPlatform);
        try{
            steps.setText((Integer.parseInt(steps.getText())+1)+"");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public Stage getStage(){
        return (Stage)(staticFrame.getScene().getWindow());
    }

    public void pause(ActionEvent e) throws IOException {
        gameloop.pause();
        FXMLLoader loader= new FXMLLoader(getClass().getResource("In-Game Menu.fxml"));
        inGameMenuController controller=new inGameMenuController(this.user);
        loader.setController(controller);
        Parent root=loader.load();
        Stage primary=(Stage)staticFrame.getScene().getWindow();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        controller.setScores();
        stage.showAndWait();
        gameloop.play();
    }
}