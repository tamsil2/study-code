package me.tamsil.designpatterns._03_behavioral_patterns._18_memento._03_java;

import me.tamsil.designpatterns._03_behavioral_patterns._18_memento._01_before.Game;

import java.io.*;

public class MementoInJava {
    public static void main(String[] args) {
        // TODO Serializable
        Game game = new Game();
        game.setRedTeamScore(10);
        game.setBlueTeamScore(20);

        // TODO 직렬화
        try (FileOutputStream fileOut = new FileOutputStream("GameSave.hex");
             ObjectOutputStream out = new ObjectOutputStream(fileOut))
        {
            out.writeObject(game);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        game.setBlueTeamScore(25);
        game.setRedTeamScore(15);

        // TODO 역직렬화
//        try (FileInputStream fileIn = new FileInputStream("GameSave.hex");
//        ObjectInputStream in = new ObjectInputStream(fileIn))
//        {
//            game = (Game) in.readObject();
//            System.out.println(game.getBlueTeamScore());
//            System.out.println(game.getRedTeamScore());
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
    }
}
