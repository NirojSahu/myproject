
package Responses.OBReadTransaction4;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;



public class Meta {

    @JsonProperty("FirstAvailableDateTime")
    private String mFirstAvailableDateTime;
    @JsonProperty("LastAvailableDateTime")
    private String mLastAvailableDateTime;
    @JsonProperty("TotalPages")
    private Long mTotalPages;

    public String getFirstAvailableDateTime() {
        return mFirstAvailableDateTime;
    }

    public String getLastAvailableDateTime() {
        return mLastAvailableDateTime;
    }

    public Long getTotalPages() {
        return mTotalPages;
    }

    public static class Builder {

        private String mFirstAvailableDateTime;
        private String mLastAvailableDateTime;
        private Long mTotalPages;

        public Meta.Builder withFirstAvailableDateTime(String firstAvailableDateTime) {
            mFirstAvailableDateTime = firstAvailableDateTime;
            return this;
        }

        public Meta.Builder withLastAvailableDateTime(String lastAvailableDateTime) {
            mLastAvailableDateTime = lastAvailableDateTime;
            return this;
        }

        public Meta.Builder withTotalPages(Long totalPages) {
            mTotalPages = totalPages;
            return this;
        }

        public Meta build() {
            Meta meta = new Meta();
            meta.mFirstAvailableDateTime = mFirstAvailableDateTime;
            meta.mLastAvailableDateTime = mLastAvailableDateTime;
            meta.mTotalPages = mTotalPages;
            return meta;
        }

    }

}
