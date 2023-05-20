package WillHero;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Swords extends Weapons{
    Swords(){
        super("Swords");
        weapon=new ImageView(new Image("Assets/sword.png"));
        weapon.setFitWidth(20);
        weapon.setFitHeight(20);
    }
}