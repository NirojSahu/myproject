
package Responses.OBReadProducts2;

import com.fasterxml.jackson.annotation.JsonProperty;


public class OBReadProducts {


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

        public OBReadProducts build() {
            OBReadProducts oBReadProducts = new OBReadProducts();
            oBReadProducts.mData = mData;
            oBReadProducts.mLinks = mLinks;
            oBReadProducts.mMeta = mMeta;
            return oBReadProducts;
        }

    }

}
