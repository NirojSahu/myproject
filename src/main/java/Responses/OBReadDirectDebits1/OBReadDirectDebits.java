
package Responses.OBReadDirectDebits1;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;



public class OBReadDirectDebits {

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

        public OBReadDirectDebits.Builder withData(Data data) {
            mData = data;
            return this;
        }

        public OBReadDirectDebits.Builder withLinks(Links links) {
            mLinks = links;
            return this;
        }

        public OBReadDirectDebits.Builder withMeta(Meta meta) {
            mMeta = meta;
            return this;
        }

        public OBReadDirectDebits build() {
            OBReadDirectDebits oBReadDirectDebits = new OBReadDirectDebits();
            oBReadDirectDebits.mData = mData;
            oBReadDirectDebits.mLinks = mLinks;
            oBReadDirectDebits.mMeta = mMeta;
            return oBReadDirectDebits;
        }

    }

}
