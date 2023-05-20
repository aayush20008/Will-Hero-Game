package WillHero;

import javafx.scene.layout.AnchorPane;
import java.util.ArrayList;
import java.util.Iterator;

public class Helmet {
    private ArrayList<Weapons> weapons;
    private String hel_name;
    private ArrayList<Weapons> weapons_clone;

    Helmet(String name){
        weapons=new ArrayList<>();
        hel_name=name;
        weapons_clone=new ArrayList<>();
    }
    public void store_Weapon(Weapons weapon){
        for(Weapons weap: weapons)
        {
            if(weap.getClass()==weapon.getClass()) {
                upgrade_weapon(weapons.indexOf(weap));
                return;
            }
        }
        weapons.add(weapon);
        weapon.getWeapon().setFitHeight(80);
        weapon.getWeapon().setFitWidth(80);
        weapon.getWeapon().setLayoutX(weapons.indexOf(weapon)*75);
        weapon.getWeapon().setLayoutY(555);
    }
    public void throw_weapon(Hero hero, AnchorPane movingPlatform){
        for(Weapons w: weapons) {
            Weapons weapon1 = w.clone();
            weapon1.setX_vel(2);
            weapon1.setDuration(200);
            weapon1.getWeapon().setLayoutX(hero.getHero().getBoundsInParent().getCenterX()+2);
            weapon1.getWeapon().setLayoutY(hero.getHero().getBoundsInParent().getCenterY()+3);
            weapons_clone.add(weapon1);
            movingPlatform.getChildren().add(weapon1.getWeapon());
        }
    }
    public void move_weapon(AnchorPane movingPlatform){
        Iterator i=weapons_clone.iterator();
        while(i.hasNext()){
            Weapons w=(Weapons)i.next();
            if(w.getDuration()>0) {
                w.getWeapon().setTranslateX(w.getWeapon().getTranslateX() + w.getX_vel());
                w.setDuration(w.getDuration() - 1);
            }
            else{
                w.getWeapon().setOpacity(0);
                movingPlatform.getChildren().remove(w);
                i.remove();

            }
        }
    }

    public ArrayList<Weapons> getCloneWeapons(){
        return weapons_clone;
    }
    public boolean hasWeapon(){
        if(weapons.iterator().hasNext() == true ){
            return true;
        } else {
            return false ;
        }
    }
    public ArrayList<Weapons> getWeapon(){
        return weapons;
    }
    public void upgrade_weapon(int ID ){
        weapons.get(ID).setPower(weapons.get(ID).getPower()+1);
    }
}

