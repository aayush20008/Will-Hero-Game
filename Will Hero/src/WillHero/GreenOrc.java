package WillHero;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class GreenOrc extends Orcs{

    GreenOrc(int x){
        object=new ImageView(new Image("Assets/greenOrc.jpeg"));
        object.setLayoutX(x);
        object.setFitWidth(33);
        object.setFitHeight(40);
    }
}
