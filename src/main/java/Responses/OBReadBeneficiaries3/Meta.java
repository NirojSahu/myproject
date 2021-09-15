
package Responses.OBReadBeneficiaries3;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;


public class Meta {

    @SerializedName("TotalPages")
    @JsonProperty("TotalPages")
    private Long mTotalPages;

    public Long getTotalPages() {
        return mTotalPages;
    }

    public static class Builder {

        private Long mTotalPages;

        public Builder withTotalPages(Long totalPages) {
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
