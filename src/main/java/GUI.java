import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class GUI implements ActionListener {
    String file;
    String directory;
    String fileName;

    JFrame frame = new JFrame("Окно шифрования/дешифрования файла");
    JFrame parentFrame = new JFrame();
    JPanel panel = new JPanel();
    JButton encryptionButton = new JButton("Зашифровать файл");
    JButton decryptionButton = new JButton("Расшифровать файл");
    JButton selectFile = new JButton("Выбрать файл");
    JTextField keyText = new JTextField("Введите ключ шифрования", 16);
    JFileChooser fileChooser = new JFileChooser();

    public GUI() {
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));

        encryptionButton.addActionListener(this);
        decryptionButton.addActionListener(this);
        keyText.addActionListener(this);
        selectFile.addActionListener(this);

        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 5, 30));
        panel.setLayout(new GridLayout(4, 1));
        panel.add(selectFile);
        panel.add(keyText);
        panel.add(encryptionButton);
        panel.add(decryptionButton);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 300);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new GUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == encryptionButton) {
            Encryption.encryptFile(file, directory + "\\ENCRYPTED_" + fileName, Integer.parseInt(keyText.getText()));
        } else if (e.getSource() == decryptionButton) {
            Decryption.decryptFile(file, directory + "\\DECRYPTED_" + fileName, Integer.parseInt(keyText.getText()));
        } else if (e.getSource() == selectFile) {
            fileChooser.setDialogTitle("Выбрать файл");
            fileChooser.showOpenDialog(parentFrame);
            file = fileChooser.getSelectedFile().getAbsolutePath();
            directory = fileChooser.getCurrentDirectory().getPath();
            fileName = fileChooser.getSelectedFile().getName();
        }
    }
}
