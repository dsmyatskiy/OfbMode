import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;

public class Encryption {
    static LocalLogger localLogger = new LocalLogger();

    public static void encryptFile(String inputFile, String outputFile, Integer key) {
        File file = new File(inputFile);
        FileInputStream fin = null;
        byte[] cipherText = null;
        try {
            fin = new FileInputStream(file);

            long millisecondsStart = System.currentTimeMillis();
            localLogger.LOGGER.log(Level.INFO, "[Шифрование] Файл считан. Размер файла " +
                    (double) file.length() / (1024 * 1024) + " мегабайт; ключ " + key);

            byte[] fileContent = new byte[(int) file.length()];
            fin.read(fileContent);
            OfbMode ofbMode = new OfbMode((byte) 1997, key);
            cipherText = ofbMode.encryptOFB(fileContent);
            FileOutputStream fos = new FileOutputStream(outputFile);
            fos.write(cipherText);
            fos.close();

            long timeSpentInMilliseconds = System.currentTimeMillis() - millisecondsStart;
            localLogger.LOGGER.log(Level.INFO, "[Шифрование] Файл успешно зашифрован. Время операции " +
                    (double) timeSpentInMilliseconds / 1000 + " секунд");

            System.out.println(outputFile + "\nФайл успешно зашифрован");
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
