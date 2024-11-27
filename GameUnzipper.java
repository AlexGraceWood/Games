package Lessons.ru.netology.Games;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class GameUnzipper {

    // Метод для распаковки архива
    public static void openZip(String zipFilePath, String outputDir) {
        try (FileInputStream fileInputStream = new FileInputStream(zipFilePath);
             ZipInputStream zipInputStream = new ZipInputStream(fileInputStream)) {

            ZipEntry entry;
            // Проходим по всем элементам архива
            while ((entry = zipInputStream.getNextEntry()) != null) {
                String fileName = entry.getName();
                File newFile = new File(outputDir + "/" + fileName);

                // Создаем папку, если она не существует
                new File(newFile.getParent()).mkdirs();

                // Записываем распакованный файл
                try (FileOutputStream fileOutputStream = new FileOutputStream(newFile)) {
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = zipInputStream.read(buffer)) > 0) {
                        fileOutputStream.write(buffer, 0, length);
                    }
                    System.out.println("Распакован файл: " + newFile.getAbsolutePath());
                }

                zipInputStream.closeEntry(); // Закрываем текущий элемент архива
            }

        } catch (IOException e) {
            System.out.println("Ошибка при распаковке архива: " + e.getMessage());
        }
    }
}

