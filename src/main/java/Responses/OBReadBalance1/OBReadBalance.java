
package Responses.OBReadBalance1;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;



public class OBReadBalance {

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

        public OBReadBalance.Builder withData(Data data) {
            mData = data;
            return this;
        }

        public OBReadBalance.Builder withLinks(Links links) {
            mLinks = links;
            return this;
        }

        public OBReadBalance.Builder withMeta(Meta meta) {
            mMeta = meta;
            return this;
        }

        public OBReadBalance build() {
            OBReadBalance oBReadBalance = new OBReadBalance();
            oBReadBalance.mData = mData;
            oBReadBalance.mLinks = mLinks;
            oBReadBalance.mMeta = mMeta;
            return oBReadBalance;
        }

    }

}
