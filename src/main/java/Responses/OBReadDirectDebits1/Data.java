
package Responses.OBReadDirectDebits1;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import javax.annotation.Generated;



public class Data {

    @JsonProperty("DirectDebit")
    private List<DirectDebit> mDirectDebit;

    public List<DirectDebit> getDirectDebit() {
        return mDirectDebit;
    }

    public static class Builder {

        private List<DirectDebit> mDirectDebit;

        public Data.Builder withDirectDebit(List<DirectDebit> directDebit) {
            mDirectDebit = directDebit;
            return this;
        }

        public Data build() {
            Data data = new Data();
            data.mDirectDebit = mDirectDebit;
            return data;
        }

    }

}
