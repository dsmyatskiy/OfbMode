import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Decryption {
    public static void decryptFile(String inputFile, String outputFile, Integer key) {
        File file = new File(inputFile);
        FileInputStream fin = null;
        byte[] plainText = null;
        try {
            fin = new FileInputStream(file);
            byte[] fileContent = new byte[(int) file.length()];
            fin.read(fileContent);
            plainText = OfbMode.fileDecryption(fileContent, key);
            FileOutputStream fos = new FileOutputStream(outputFile);
            fos.write(plainText);
            fos.close();
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
