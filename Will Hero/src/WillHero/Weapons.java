package WillHero;

import javafx.scene.image.ImageView;

import java.io.Serializable;

public abstract class Weapons implements Cloneable, Serializable {
    private int power;
    private int ID;
    private String name;
    protected ImageView weapon;
    private MovingProp m_prop;

    Weapons(String name){
        this.name =name;
        m_prop=new MovingProp(0,0,0);
    }

    public int getPower(){
        return power;
    }
    public void setPower(int power){
        this.power=power;
    }

    public ImageView getWeapon() {
        return weapon;
    }

    public int getX_vel(){
        return m_prop.getX_vel();
    }
    public void setX_vel(int x_vel){
        m_prop.setX_vel(x_vel);
    }
    public int getY_vel(){
        return m_prop.getY_vel();
    }
    public void setY_vel(int y_vel){
        m_prop.setY_vel(y_vel);
    }
    public void setDuration(int dur ){
        m_prop.setDuration(dur);
    }
    public int getDuration(){
        return m_prop.getDuration();
    }
    @Override
    public String toString() {
        return name;
    }

    @Override
    public Weapons clone(){
        try{
            Weapons weap=(Weapons)super.clone();
            weap.weapon=new ImageView(weapon.getImage());
            weap.weapon.setFitWidth(60);
            weap.weapon.setFitHeight(60);
            return weap;
        }
        catch (CloneNotSupportedException e){
            System.out.println("Problem creating weapon");
        }
        return null;
    }
}
