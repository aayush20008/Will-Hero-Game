package WillHero;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Random;

public class Weapon_Chest extends Game_Objects {
   private int zy;
    Weapon_Chest(int x ) {
        object = new ImageView(new Image("Assets/ChestClosed.png"));
        object.setLayoutX(x);
        object.setFitWidth(55);
        object.setFitHeight(40);
        zy = (int) object.getLayoutX();
    }
    public void give_item(Hero hero){
        int ID=new Random().nextInt(2);
        if(ID==1){
            hero.addWeapon(new Shurikens());
        }
        else
            hero.addWeapon(new Swords());
    }

    @Override
    public void onCollision(Hero hero){
        object.setImage(new Image("Assets/ChestOpen.png"));
        object.setLayoutX(zy);
        object.setFitWidth(55);
        object.setFitHeight(40);
        give_item(hero);
    }

    public void disappear(){}
}
