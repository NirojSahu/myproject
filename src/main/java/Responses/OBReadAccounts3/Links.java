
package Responses.OBReadAccounts3;

import com.fasterxml.jackson.annotation.JsonProperty;



public class Links {

    @JsonProperty("Self")
    private String mSelf;

    public String getSelf() {
        return mSelf;
    }

    public static class Builder {

        private String mSelf;

        public Links.Builder withSelf(String self) {
            mSelf = self;
            return this;
        }

        public Links build() {
            Links links = new Links();
            links.mSelf = mSelf;
            return links;
        }

    }

}
