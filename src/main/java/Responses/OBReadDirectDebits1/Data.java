
package Responses.OBReadDirectDebits1;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public class Data {


    @JsonProperty("DirectDebit")
    private List<DirectDebit> mDirectDebit;

    public List<DirectDebit> getDirectDebit() {
        return mDirectDebit;
    }

    public void setDirectDebit(List<DirectDebit> directDebit) {
        mDirectDebit = directDebit;
    }

}
