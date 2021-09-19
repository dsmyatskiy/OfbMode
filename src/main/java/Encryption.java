import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Encryption {

    public static void encryptFile(String inputFile, String outputFile, Integer key) {
        File file = new File(inputFile);
        FileInputStream fin = null;
        byte[] cipherText = null;
        try {
            fin = new FileInputStream(file);
            byte[] fileContent = new byte[(int) file.length()];
            fin.read(fileContent);
            OfbMode em = new OfbMode((byte) 1997, key);
            cipherText = em.encryptOFB(fileContent);
            FileOutputStream fos = new FileOutputStream(outputFile);
            fos.write(cipherText);
            fos.close();
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
