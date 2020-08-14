
package Responses.OBReadAccounts3;
import com.fasterxml.jackson.annotation.JsonProperty;
public class OBReadAccounts {

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

        public OBReadAccounts.Builder withData(Data data) {
            mData = data;
            return this;
        }

        public OBReadAccounts.Builder withLinks(Links links) {
            mLinks = links;
            return this;
        }

        public OBReadAccounts.Builder withMeta(Meta meta) {
            mMeta = meta;
            return this;
        }

        public OBReadAccounts build() {
            OBReadAccounts oBReadAccounts = new OBReadAccounts();
            oBReadAccounts.mData = mData;
            oBReadAccounts.mLinks = mLinks;
            oBReadAccounts.mMeta = mMeta;
            return oBReadAccounts;
        }

    }

}
