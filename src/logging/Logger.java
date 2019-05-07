package logging;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;

public class Logger implements ILogger {
    private final static java.util.logging.Logger _logger = java.util.logging.Logger.getLogger("logging.Logger");
    private final static Logger _instance = new Logger();

    private Logger() {
        try {
            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy");
            FileHandler fileHandler = new FileHandler(dateFormatter.format(new Date()) + ".log", true);
            _logger.addHandler(fileHandler);
            _logger.setUseParentHandlers(false);

            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Logger getInstance() {
        return _instance;
    }

    @Override
    public void info(String msg) {
        _logger.info(msg);
    }

    @Override
    public void warning(String msg) {
        _logger.warning(msg);
    }

    @Override
    public void error(String msg) {
        _logger.log(Level.SEVERE, msg);
    }

    @Override
    public void info(String msg, boolean verbose) {
        info(msg);
        if (verbose) System.out.println(msg);
    }

    @Override
    public void warning(String msg, boolean verbose) {
        warning(msg);
        if (verbose) System.out.println(msg);
    }

    @Override
    public void error(String msg, boolean verbose) {
        error(msg);
        if (verbose) System.out.println(msg);
    }
}
