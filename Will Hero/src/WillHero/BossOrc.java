package WillHero;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

public class BossOrc extends Orcs{
    BossOrc(){
        object=new ImageView(new Image("Assets/BossOrc.png"));
        object.setLayoutX(3550);
        object.setFitWidth(67);
        object.setFitHeight(57);
    }

    @Override
    public void killOrc(Hero hero){
        try {
            disappear();
            hero.gameWon();
        } catch (IOException e) {
            e.printStackTrace();
        }
    };
}
