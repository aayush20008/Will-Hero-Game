package WillHero;

import javafx.animation.FadeTransition;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public abstract class Game_Objects {
    protected ImageView object;

    public int if_collides(Hero hero){
        if(hero.getHero().getBoundsInParent().getMinX()>=object.getBoundsInParent().getMaxX())
            return -1;
        if(hero.getHero().getBoundsInParent().intersects(object.getBoundsInParent())){
            onCollision(hero);
            return -1;
        }
        return 0;
    }

    public ImageView getObject() {
        return object;
    }

    public ImageView display(){
        return object;
    }

    public abstract void onCollision(Hero hero);

    public void disappear(){
        object.setOpacity(0);
    }
}
