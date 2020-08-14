
package Responses.OBReadBeneficiaries3;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;



public class OBReadBeneficiaries {

    @JsonProperty("Data")
    private Data mData;
    @JsonProperty("Links")
    private Links mLinks;
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

        public OBReadBeneficiaries.Builder withData(Data data) {
            mData = data;
            return this;
        }

        public OBReadBeneficiaries.Builder withLinks(Links links) {
            mLinks = links;
            return this;
        }

        public OBReadBeneficiaries.Builder withMeta(Meta meta) {
            mMeta = meta;
            return this;
        }

        public OBReadBeneficiaries build() {
            OBReadBeneficiaries OBReadBeneficiaries = new OBReadBeneficiaries();
            OBReadBeneficiaries.mData = mData;
            OBReadBeneficiaries.mLinks = mLinks;
            OBReadBeneficiaries.mMeta = mMeta;
            return OBReadBeneficiaries;
        }

    }

}
