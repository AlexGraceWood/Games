package Lessons.ru.netology.Games;

import java.io.*;

public class GameLoader {

    // Метод для десериализации игрового процесса
    public static GameProgress openProgress(String saveFilePath) {
        try (FileInputStream fileInputStream = new FileInputStream(saveFilePath);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {

            // Десериализуем объект
            GameProgress gameProgress = (GameProgress) objectInputStream.readObject();
            return gameProgress;

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Ошибка при загрузке игры: " + e.getMessage());
            return null;
        }
    }
}
