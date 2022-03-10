
package Responses.OBReadStandingOrder4;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;



public class OBReadStandingOrder {

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

        public OBReadStandingOrder.Builder withData(Data data) {
            mData = data;
            return this;
        }

        public OBReadStandingOrder.Builder withLinks(Links links) {
            mLinks = links;
            return this;
        }

        public OBReadStandingOrder.Builder withMeta(Meta meta) {
            mMeta = meta;
            return this;
        }

        public OBReadStandingOrder build() {
            OBReadStandingOrder oBReadStandingOrder = new OBReadStandingOrder();
            oBReadStandingOrder.mData = mData;
            oBReadStandingOrder.mLinks = mLinks;
            oBReadStandingOrder.mMeta = mMeta;
            return oBReadStandingOrder;
        }

    }

}
