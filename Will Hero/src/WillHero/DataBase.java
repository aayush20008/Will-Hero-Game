package WillHero;

import java.io.*;
import java.util.ArrayList;

public class DataBase implements Serializable {
    public static final long serialVersionUID = 10L;
    private int score;
    private double hero_xpos;
    private double hero_ypos;
    private double frame_x;
    private ArrayList<Weapons> hero_weapon;
    private String name;

    public DataBase(int score, double hero_xpos, double hero_ypos, double frame_x, ArrayList<Weapons> hero_weapon, String name) {
        this.score = score;
        this.hero_xpos = hero_xpos;
        this.hero_ypos = hero_ypos;
        this.frame_x = frame_x;
        this.hero_weapon = hero_weapon;
        this.name = name;
    }

    public static void serialize(DataBase d) throws IOException {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream("UserData.txt"));
            out.writeObject(d);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            out.close();
        }
    }

    public static void deserialize() throws IOException {
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new FileInputStream("UserData.txt"));
            DataBase d = (DataBase) in.readObject();
            SavedGames.add(d);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            in.close();
        }

    }

    public int getScore() {
        return score;
    }

    public double getHero_xpos() {
        return hero_xpos;
    }

    public double getHero_ypos() {
        return hero_ypos;
    }

    public double getFrame_x() {
        return frame_x;
    }

    public ArrayList<Weapons> getHero_weapon() {
        return hero_weapon;
    }

    public String getName() {
        return name;
    }
}