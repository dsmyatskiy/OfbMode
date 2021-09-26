import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;

public class Decryption {
    static LocalLogger localLogger = new LocalLogger();

    public static void decryptFile(String inputFile, String outputFile, Integer key) {
        File file = new File(inputFile);
        FileInputStream fin = null;
        byte[] plainText = null;
        try {
            fin = new FileInputStream(file);

            long millisecondsStart = System.currentTimeMillis();
            localLogger.LOGGER.log(Level.INFO, "[Дешифрование] Файл считан. Размер файла " +
                    (double) file.length() / (1024 * 1024) + " мегабайт; ключ " + key);

            byte[] fileContent = new byte[(int) file.length()];
            fin.read(fileContent);
            plainText = OfbMode.fileDecryption(fileContent, key);
            FileOutputStream fos = new FileOutputStream(outputFile);
            fos.write(plainText);
            fos.close();

            long timeSpentInMilliseconds = System.currentTimeMillis() - millisecondsStart;
            localLogger.LOGGER.log(Level.INFO, "[Дешифрование] Файл успешно расшифрован. Время операции " +
                    (double) timeSpentInMilliseconds / 1000 + " секунд");
            System.out.println(outputFile + "\nФайл успешно расшифрован");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fin != null) {
                    fin.close();
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }
}
