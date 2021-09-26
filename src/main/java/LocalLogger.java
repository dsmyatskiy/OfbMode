import java.io.FileInputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class LocalLogger {
    Logger LOGGER;

    {
        try (FileInputStream ins = new FileInputStream("src/main/resources/log.config")) {
            LogManager.getLogManager().readConfiguration(ins);
            LOGGER = Logger.getLogger(this.getClass().getName());
        } catch (Exception ignore) {
            ignore.printStackTrace();
        }
    }
}
