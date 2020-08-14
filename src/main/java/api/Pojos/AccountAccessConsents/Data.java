
package api.Pojos.AccountAccessConsents;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import javax.annotation.Generated;
public class Data {

    @JsonProperty("ExpirationDateTime")
    private String mExpirationDateTime;
    @JsonProperty("Permissions")
    private List<String> mPermissions;
    @JsonProperty("TransactionFromDateTime")
    private String mTransactionFromDateTime;
    @JsonProperty("TransactionToDateTime")
    private String mTransactionToDateTime;

    public String getExpirationDateTime() {
        return mExpirationDateTime;
    }

    public List<String> getPermissions() {
        return mPermissions;
    }

    public String getTransactionFromDateTime() {
        return mTransactionFromDateTime;
    }

    public String getTransactionToDateTime() {
        return mTransactionToDateTime;
    }

    public static class Builder {

        private String mExpirationDateTime;
        private List<String> mPermissions;
        private String mTransactionFromDateTime;
        private String mTransactionToDateTime;

        public Data.Builder withExpirationDateTime(String expirationDateTime) {
            mExpirationDateTime = expirationDateTime;
            return this;
        }

        public Data.Builder withPermissions(List<String> permissions) {
            mPermissions = permissions;
            return this;
        }

        public Data.Builder withTransactionFromDateTime(String transactionFromDateTime) {
            mTransactionFromDateTime = transactionFromDateTime;
            return this;
        }

        public Data.Builder withTransactionToDateTime(String transactionToDateTime) {
            mTransactionToDateTime = transactionToDateTime;
            return this;
        }

        public Data build() {
            Data data = new Data();
            data.mExpirationDateTime = mExpirationDateTime;
            data.mPermissions = mPermissions;
            data.mTransactionFromDateTime = mTransactionFromDateTime;
            data.mTransactionToDateTime = mTransactionToDateTime;
            return data;
        }

    }

}
