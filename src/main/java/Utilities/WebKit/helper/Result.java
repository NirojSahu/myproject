//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.test.helper;

public class Result {
    private String testName = "0";
    private String successes = "0";
    private String failures = "0";
    private String exceptions = "0";
    private String errorMessage = "0";
    private String environment = "unknown";
    private String sessionID = "unknown";

    public Result() {
    }

    public String getTestName() {
        return this.testName;
    }

    public void setTestName(String testName) {
        this.testName = testName.trim();
    }

    public String getSuccesses() {
        return this.successes;
    }

    public void setSuccesses(String successes) {
        this.successes = successes.trim();
    }

    public String getFailures() {
        return this.failures;
    }

    public void setFailures(String failures) {
        this.failures = failures.trim();
    }

    public String getExceptions() {
        return this.exceptions;
    }

    public void setExceptions(String exceptions) {
        this.exceptions = exceptions.trim();
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage.trim();
    }

    public String getEnvironemnt() {
        return this.environment;
    }

    public void setEnvironemnt(String environment) {
        this.environment = environment;
    }

    public String getSessionID() {
        return this.sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }
}
