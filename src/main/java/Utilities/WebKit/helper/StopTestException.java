package Utilities.WebKit.helper;

public class StopTestException extends Exception {

    private static final long serialVersionUID=1L;
    private String screenshot;

    public StopTestException(String message, boolean screenshot){
        this.initCause(new Throwable(message));
    }

    public StopTestException(String message, Throwable exception){
        super(message, exception);
        ExecControlHelper.stopTest(message);
    }
    public StopTestException(String message){
        super(message);
        ExecControlHelper.stopTest(message);
    }
    public StopTestException(String messageFormat, Object...argumrnts){
        this(String.format(messageFormat, argumrnts));
    }
    public void setScreenshot(String screenshot){
        this.screenshot=screenshot;
    }
}
