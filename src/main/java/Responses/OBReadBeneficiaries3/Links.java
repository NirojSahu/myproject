
package Responses.OBReadBeneficiaries3;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;



public class Links {

    @SerializedName("Self")
    @JsonProperty("Self")
    private String mSelf;

    public String getSelf() {
        return mSelf;
    }

    public static class Builder {

        private String mSelf;

        public Builder withSelf(String self) {
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
