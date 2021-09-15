
package Responses.OBReadDirectDebits1;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Links {


    @JsonProperty("Self")
    private String mSelf;

    public String getSelf() {
        return mSelf;
    }

    public void setSelf(String self) {
        mSelf = self;
    }

}
