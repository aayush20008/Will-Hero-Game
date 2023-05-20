package WillHero;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Shurikens extends Weapons{
    Shurikens(){
        super("Shurikens");
        weapon=new ImageView(new Image("Assets/shuriken.png"));
        weapon.setFitWidth(20);
        weapon.setFitHeight(20);


    }
}
