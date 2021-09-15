
package Responses.OBReadDirectDebits1;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Meta {


    @JsonProperty("TotalPages")
    private Long mTotalPages;

    public Long getTotalPages() {
        return mTotalPages;
    }

    public void setTotalPages(Long totalPages) {
        mTotalPages = totalPages;
    }

}
