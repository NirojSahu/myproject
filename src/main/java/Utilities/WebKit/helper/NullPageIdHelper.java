package Utilities.WebKit.helper;

import Utilities.WebKit.helper.PageIdHelper;

public class NullPageIdHelper implements PageIdHelper {
    public NullPageIdHelper(){

    }
    public String getPageId(){
        throw new UnsupportedOperationException();
    }
}
