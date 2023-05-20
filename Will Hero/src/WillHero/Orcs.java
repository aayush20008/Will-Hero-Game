package WillHero;

import javafx.animation.FadeTransition;
import javafx.util.Duration;

public abstract class Orcs extends Game_Objects implements OrcsCollision{
    private int collision_type;
    private boolean kill;
    private MovingProp m_prop;


    public Orcs() {
        this.collision_type = 0;
        m_prop=new MovingProp(0,1,0);
        this.kill = false;
    }
    public int getX_vel(){
        return m_prop.getX_vel();
    }
    public void setX_vel(int x_vel){
        m_prop.setX_vel(x_vel);
    }
    public int gety_vel(){
        return m_prop.getY_vel();
    }
    public void sety_vel(int y_vel){
        m_prop.setY_vel(y_vel);
    }
    public void setDuration(int dur ){
        m_prop.setDuration(dur);
    }
    public int getDuration(){
        return m_prop.getDuration();
    }

    @Override
    public void onCollision(Hero hero){
        switch (collision_type){
            case 1: setX_vel(1);
                setDuration(40);
                hero.displaceOrc(this);
                break;
            case 2: hero.killHero();
                break;
            case 3: killOrc(hero);
                break;
            default:
                break;
        }
    }

    public void killOrc(Hero hero){
        disappear();
        kill=true;
    }

    @Override
    public int if_collides(Hero hero){
        if(hero.getHero().getBoundsInParent().getMinX()>object.getBoundsInParent().getMaxX())
            return -1;
        if(collisionType(hero, object, 0)==1){
            collision_type=3;
            onCollision(hero);
        }
        if(hero.getHero().getBoundsInParent().intersects(object.getBoundsInParent())) {

            switch(collisionType(hero, object, 1)){
                case 1:
                    collision_type=1;
                    onCollision(hero);
                    break;
                case 2:
                    collision_type=2;
                    onCollision(hero);
                    break;
                default:
                    break;
            }
        }
        if(kill)
            return -11;
        return 0;
    }
}
