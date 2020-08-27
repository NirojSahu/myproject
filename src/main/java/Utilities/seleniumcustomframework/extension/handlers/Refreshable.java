package Utilities.seleniumcustomframework.extension.handlers;

import Utilities.seleniumcustomframework.extension.exceptions.PageFactortError;

public interface Refreshable {
    void invalidate();

    void refresh() throws PageFactortError;
    void setParent(Refreshable var1);
}
