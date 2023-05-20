package WillHero;

import javafx.scene.image.ImageView;

public interface OrcsCollision {
    default int collisionType(Hero hero, ImageView object, int check){
        if(check==1) {
            if (hero.getHero().getBoundsInParent().getMaxX() == object.getBoundsInParent().getMinX()) {
                return 1;
            }
            if (hero.getHero().getBoundsInParent().getMaxY() == object.getBoundsInParent().getMinY()) {
                return 2;
            }
        }
        else {
            for (Weapons weapon : hero.getCloneWeapons()) {
                if (weapon.getWeapon().getBoundsInParent().intersects(object.getBoundsInParent())) {
                    return 1;
                }
            }
        }
        return 0;
    }
}
