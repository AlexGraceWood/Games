package Lessons.ru.netology.Games;

import java.io.File;
import java.util.List;

public class FileDeleter {

    // Метод для удаления файлов после упаковки
    public static void deleteFiles(List<String> filePaths) {
        for (String filePath : filePaths) {
            File file = new File(filePath);
            if (file.delete()) {
                System.out.println("Файл " + file.getName() + " удален.");
            } else {
                System.out.println("Не удалось удалить файл " + file.getName());
            }
        }
    }
}

