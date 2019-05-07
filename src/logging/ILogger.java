package logging;

public interface ILogger {
    void info(String msg);

    void warning(String msg);

    void error(String msg);

    void info(String msg, boolean verbose);

    void warning(String msg, boolean verbose);

    void error(String msg, boolean verbose);
}
