
package Responses.OBReadTransaction4;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import javax.annotation.Generated;



public class Data {

    @JsonProperty("Transaction")
    private List<Transaction> mTransaction;

    public List<Transaction> getTransaction() {
        return mTransaction;
    }

    public static class Builder {

        private List<Transaction> mTransaction;

        public Data.Builder withTransaction(List<Transaction> transaction) {
            mTransaction = transaction;
            return this;
        }

        public Data build() {
            Data data = new Data();
            data.mTransaction = mTransaction;
            return data;
        }

    }

}
