//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package Utilities.WebKit.exceptions;

import Utilities.WebKit.helper.ExecControlHelper;

public class StopTestException extends Exception {
    private static final long serialVersionUID = 1L;
    private String screenshot;

    public StopTestException(String message, boolean screenshot) {
        this.initCause(new Throwable(message));
    }

    public StopTestException(String message, Throwable exception) {
        super(message, exception);
        ExecControlHelper.stopTest(message);
    }

    public StopTestException(String message) {
        super(message);
        ExecControlHelper.stopTest(message);
    }

    public StopTestException(String messageFormat, Object... arguments) {
        this(String.format(messageFormat, arguments));
    }

    public String getScreenshot() {
        return this.screenshot;
    }

    public void setScreenshot(String screenshot) {
        this.screenshot = screenshot;
    }
}
