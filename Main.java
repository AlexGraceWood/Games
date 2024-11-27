package Lessons.ru.netology.Games;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.StringBuilder;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Путь к каталогу "Games" на диске D
        String baseDir = "D:/Games"; // Используем прямые слэши

        StringBuilder log = new StringBuilder();

        // Создание главной директории "Games"
        File gamesDir = new File(baseDir);
        if (gamesDir.mkdir()) {
            log.append("Директория Games создана успешно.\n");
        } else {
            log.append("Не удалось создать директорию Games.\n");
        }

        // Создание подкаталогов в каталоге "Games"
        String[] subDirs = {"src", "res", "savegames", "temp"};
        for (String dir : subDirs) {
            File subDir = new File(gamesDir, dir);
            if (subDir.mkdir()) {
                log.append("Директория " + dir + " создана успешно.\n");
            } else {
                log.append("Не удалось создать директорию " + dir + ".\n");
            }
        }

        // Создание подкаталогов в каталоге "src"
        File srcDir = new File(gamesDir, "src");
        String[] srcSubDirs = {"main", "test"};
        for (String dir : srcSubDirs) {
            File subDir = new File(srcDir, dir);
            if (subDir.mkdir()) {
                log.append("Директория " + dir + " в каталоге src создана успешно.\n");
            } else {
                log.append("Не удалось создать директорию " + dir + " в каталоге src.\n");
            }
        }

        // Создание файлов в каталоге "src/main"
        File mainDir = new File(srcDir, "main");
        String[] files = {"Main.java", "Utils.java"};
        for (String file : files) {
            File newFile = new File(mainDir, file);
            try {
                if (newFile.createNewFile()) {
                    log.append("Файл " + file + " создан успешно.\n");
                } else {
                    log.append("Не удалось создать файл " + file + ".\n");
                }
            } catch (IOException e) {
                log.append("Ошибка при создании файла " + file + ": " + e.getMessage() + ".\n");
            }
        }

        // Создание подкаталогов в каталоге "res"
        File resDir = new File(gamesDir, "res");
        String[] resSubDirs = {"drawables", "vectors", "icons"};
        for (String dir : resSubDirs) {
            File subDir = new File(resDir, dir);
            if (subDir.mkdir()) {
                log.append("Директория " + dir + " в каталоге res создана успешно.\n");
            } else {
                log.append("Не удалось создать директорию " + dir + " в каталоге res.\n");
            }
        }

        // Создание файла temp.txt в каталоге "temp"
        File tempFile = new File(gamesDir, "temp/temp.txt");
        try {
            if (tempFile.createNewFile()) {
                log.append("Файл temp.txt создан успешно.\n");
            } else {
                log.append("Не удалось создать файл temp.txt.\n");
            }
        } catch (IOException e) {
            log.append("Ошибка при создании файла temp.txt: " + e.getMessage() + ".\n");
        }

        // Запись лога в файл temp.txt
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write(log.toString());
        } catch (IOException e) {
            System.out.println("Ошибка при записи в файл temp.txt: " + e.getMessage());
        }

        // Выводим информацию на экран
        System.out.println("Процесс создания файлов и директорий завершён. Лог записан в temp.txt.");

        GameProgress game1 = new GameProgress(100, 5, 1, 10.5);
        GameProgress game2 = new GameProgress(80, 3, 2, 20.3);
        GameProgress game3 = new GameProgress(60, 7, 3, 30.1);

        // Список для хранения путей к файлам сохранений
        List<String> saveFilePaths = new ArrayList<>();

        // Сохраняем игровые процессы в файлы
        String saveDir = "D:/Games/savegames/";
        saveFilePaths.add(saveDir + "save1.dat");
        saveFilePaths.add(saveDir + "save2.dat");
        saveFilePaths.add(saveDir + "save3.dat");

        GameSaver.saveGame(saveFilePaths.get(0), game1);
        GameSaver.saveGame(saveFilePaths.get(1), game2);
        GameSaver.saveGame(saveFilePaths.get(2), game3);

        // Упаковываем файлы сохранений в архив
        String zipFilePath = saveDir + "savegames.zip";
        GameZipper.zipFiles(zipFilePath, saveFilePaths);

        // Удаляем исходные файлы сохранений
        FileDeleter.deleteFiles(saveFilePaths);

        // Путь к архиву и папке для распаковки
        zipFilePath = "D:/Games/savegames/savegames.zip";
        String outputDir = "D:/Games/savegames";

        // Разархивируем архив
        GameUnzipper.openZip(zipFilePath, outputDir);

        // Путь к одному из файлов сохранений
        String saveFilePath = "D:/Games/savegames/save2.dat";

        // Загружаем игровой процесс из файла
        GameProgress gameProgress = GameLoader.openProgress(saveFilePath);

        if (gameProgress != null) {
            // Выводим информацию о сохраненном игровом процессе
            System.out.println(gameProgress);
        }
    }
}

