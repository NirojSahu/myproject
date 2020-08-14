
package Responses.OBReadBalance1;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import javax.annotation.Generated;



public class Data {

    @JsonProperty("Balance")
    private List<Balance> mBalance;

    public List<Balance> getBalance() {
        return mBalance;
    }

    public static class Builder {

        private List<Balance> mBalance;

        public Data.Builder withBalance(List<Balance> balance) {
            mBalance = balance;
            return this;
        }

        public Data build() {
            Data data = new Data();
            data.mBalance = mBalance;
            return data;
        }

    }

}
