package WillHero;

import java.util.ArrayList;

public class SavedGames {
    private static ArrayList<DataBase> savedGames=new ArrayList<>();
    public static void add(DataBase d){
        savedGames.add(d);
    }
    public static DataBase getGame(String name){
        for(DataBase d: savedGames) {
            if(d.getName().equals(name))
                return d;
        }
        return null;
    }

    public static ArrayList<DataBase> display(){
        return savedGames;
    }

    public static DataBase getLatestGame(){
        return savedGames.get(savedGames.size()-1);
    }
}
