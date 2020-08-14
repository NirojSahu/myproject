
package Responses.OBReadSheduledPayments2;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;



public class OBReadSheduledPayments {

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

        public OBReadSheduledPayments.Builder withData(Data data) {
            mData = data;
            return this;
        }

        public OBReadSheduledPayments.Builder withLinks(Links links) {
            mLinks = links;
            return this;
        }

        public OBReadSheduledPayments.Builder withMeta(Meta meta) {
            mMeta = meta;
            return this;
        }

        public OBReadSheduledPayments build() {
            OBReadSheduledPayments oBReadSheduledPayments = new OBReadSheduledPayments();
            oBReadSheduledPayments.mData = mData;
            oBReadSheduledPayments.mLinks = mLinks;
            oBReadSheduledPayments.mMeta = mMeta;
            return oBReadSheduledPayments;
        }

    }

}
