package Lessons.ru.netology.Games;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class GameZipper {

    // Метод для упаковки файлов в архив
    public static void zipFiles(String zipFilePath, List<String> filePaths) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(zipFilePath);
             ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream)) {

            for (String filePath : filePaths) {
                File fileToZip = new File(filePath);
                if (fileToZip.exists()) {
                    try (FileInputStream fileInputStream = new FileInputStream(fileToZip)) {
                        ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
                        zipOutputStream.putNextEntry(zipEntry);

                        byte[] buffer = new byte[1024];
                        int length;
                        while ((length = fileInputStream.read(buffer)) > 0) {
                            zipOutputStream.write(buffer, 0, length);
                        }

                        zipOutputStream.closeEntry();
                        System.out.println("Файл " + fileToZip.getName() + " добавлен в архив.");
                    } catch (IOException e) {
                        System.out.println("Ошибка при упаковке файла " + filePath + ": " + e.getMessage());
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Ошибка при создании архива: " + e.getMessage());
        }
    }
}
