package WillHero;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TNT extends Game_Objects{
    private int cx;

    TNT(int x){
        object=new ImageView(new Image("Assets/TNT.png"));
        object.setLayoutX(x);
        object.setFitWidth(33);
        object.setFitHeight(40);
        cx= (int) object.getLayoutX();
    }
    @Override
    public void onCollision(Hero hero) {
        object.setImage(new Image("Assets/TNT_Boom.png"));
        object.setLayoutX(cx);
        object.setFitWidth(50);
        object.setFitHeight(50);
        hero.killHero();
    }

}
