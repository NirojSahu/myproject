
package Responses.OBReadBeneficiaries3;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;


public class OBReadBeneficiaries {

    @SerializedName("Data")
    @JsonProperty("Data")
    private Data mData;
    @SerializedName("Links")
    @JsonProperty("Links")
    private Links mLinks;
    @SerializedName("Meta")
    @JsonProperty("Meta")
    private Meta mMeta;

    public Data getData() {
        return mData;
    }

    public Links getLinks() {
        return mLinks;
    }

    public Meta getMeta() {
        return mMeta;
    }

    public static class Builder {

        private Data mData;
        private Links mLinks;
        private Meta mMeta;

        public Builder withData(Data data) {
            mData = data;
            return this;
        }

        public Builder withLinks(Links links) {
            mLinks = links;
            return this;
        }

        public Builder withMeta(Meta meta) {
            mMeta = meta;
            return this;
        }

        public OBReadBeneficiaries build() {
            OBReadBeneficiaries oBReadBeneficiaries = new OBReadBeneficiaries();
            oBReadBeneficiaries.mData = mData;
            oBReadBeneficiaries.mLinks = mLinks;
            oBReadBeneficiaries.mMeta = mMeta;
            return oBReadBeneficiaries;
        }

    }

}
