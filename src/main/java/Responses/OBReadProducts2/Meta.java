
package Responses.OBReadProducts2;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Meta {

    @JsonProperty("TotalPages")
    private String mTotalPages;

    public String getTotalPages() {
        return mTotalPages;
    }

    public static class Builder {

        private String mTotalPages;

        public Builder withTotalPages(String totalPages) {
            mTotalPages = totalPages;
            return this;
        }

        public Meta build() {
            Meta meta = new Meta();
            meta.mTotalPages = mTotalPages;
            return meta;
        }

    }

}
