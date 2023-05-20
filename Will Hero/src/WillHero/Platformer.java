package WillHero;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class Platformer {

    private ArrayList<ImageView> islands;

    Platformer(){
        islands=new ArrayList<>();
    }

    public void createIslands(){
        Image island1=new Image("Assets/island1.png");
        Image island2=new Image("Assets/island2.png");
        int k=0;
        for(int i=0;i<3000;i+=320) {
            islands.add(new ImageView(island1));
            islands.get(k).setLayoutX(i);
            islands.get(k).setY(-40);
            System.out.println("Island1: " + islands.get(k++).getBoundsInParent());
        }
        for(int j = 0; j<3600; j+=500) {
            islands.add(new ImageView(island2));
            islands.get(k).setLayoutX(j - (700%(k+1)+2));
            islands.get(k).setY(-40);
            System.out.println("Island2: " + islands.get(k++).getBoundsInParent());
        }
    }

    public boolean if_gap(){
        return false;
    }
    public ArrayList<ImageView> getIslands(){return islands;}


}
