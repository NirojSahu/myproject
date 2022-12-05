
package Responses.OBReadStandingOrder4;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;



public class Meta {

    @JsonProperty("TotalPages")
    private Long mTotalPages;

    public Long getTotalPages() {
        return mTotalPages;
    }

    public static class Builder {

        private Long mTotalPages;

        public Meta.Builder withTotalPages(Long totalPages) {
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
