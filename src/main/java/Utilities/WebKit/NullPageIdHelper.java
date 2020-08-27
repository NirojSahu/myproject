package Utilities.WebKit;

public class NullPageIdHelper implements PageIdHelper {
    public NullPageIdHelper(){

    }
    public String getPageId(){
        throw new UnsupportedOperationException();
    }
}
