package WillHero;

import java.io.Serializable;

public class MovingProp implements Serializable {
    private int x_vel;
    private int y_vel;
    private int duration;

    public MovingProp(){
        x_vel=0;
        y_vel=0;
        duration=0;
    }

    public MovingProp(int x_vel, int y_vel, int duration) {
        this.x_vel = x_vel;
        this.y_vel = y_vel;
        this.duration = duration;
    }



    public int getX_vel() {
        return x_vel;
    }

    public void setX_vel(int x_vel) {
        this.x_vel = x_vel;
    }

    public int getY_vel() {
        return y_vel;
    }

    public void setY_vel(int y_vel) {
        this.y_vel = y_vel;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
