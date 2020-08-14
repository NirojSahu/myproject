
package Responses.OBReadConsentResponse1;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;


public class OBReadConsentResponse {

    @JsonProperty("Data")
    private Data mData;
    @JsonProperty("Links")
    private Links mLinks;
    @JsonProperty("Meta")
    private Meta mMeta;
    @JsonProperty("Risk")
    private Risk mRisk;

    public Data getData() {
        return mData;
    }

    public Links getLinks() {
        return mLinks;
    }

    public Meta getMeta() {
        return mMeta;
    }

    public Risk getRisk() {
        return mRisk;
    }

    public static class Builder {

        private Data mData;
        private Links mLinks;
        private Meta mMeta;
        private Risk mRisk;

        public OBReadConsentResponse.Builder withData(Data data) {
            mData = data;
            return this;
        }

        public OBReadConsentResponse.Builder withLinks(Links links) {
            mLinks = links;
            return this;
        }

        public OBReadConsentResponse.Builder withMeta(Meta meta) {
            mMeta = meta;
            return this;
        }

        public OBReadConsentResponse.Builder withRisk(Risk risk) {
            mRisk = risk;
            return this;
        }

        public OBReadConsentResponse build() {
            OBReadConsentResponse oBReadConsentResponse = new OBReadConsentResponse();
            oBReadConsentResponse.mData = mData;
            oBReadConsentResponse.mLinks = mLinks;
            oBReadConsentResponse.mMeta = mMeta;
            oBReadConsentResponse.mRisk = mRisk;
            return oBReadConsentResponse;
        }

    }

}
