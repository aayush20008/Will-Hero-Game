package WillHero;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Redorc extends Orcs{

    Redorc(int x){
        object=new ImageView(new Image("Assets/redOrc.jpeg"));
        object.setLayoutX(x);
        object.setFitWidth(33);
        object.setFitHeight(40);
    }


}
