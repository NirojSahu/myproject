
package Responses.OBReadAccounts3;

import com.fasterxml.jackson.annotation.JsonProperty;
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
