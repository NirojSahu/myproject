
package Responses.OBReadTransaction4;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;



public class OBReadTransaction {

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

        public OBReadTransaction.Builder withData(Data data) {
            mData = data;
            return this;
        }

        public OBReadTransaction.Builder withLinks(Links links) {
            mLinks = links;
            return this;
        }

        public OBReadTransaction.Builder withMeta(Meta meta) {
            mMeta = meta;
            return this;
        }

        public OBReadTransaction build() {
            OBReadTransaction oBReadTransaction = new OBReadTransaction();
            oBReadTransaction.mData = mData;
            oBReadTransaction.mLinks = mLinks;
            oBReadTransaction.mMeta = mMeta;
            return oBReadTransaction;
        }

    }

}
