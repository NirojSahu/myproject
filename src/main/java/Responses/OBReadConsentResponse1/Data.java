
package Responses.OBReadConsentResponse1;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import javax.annotation.Generated;



public class Data {

    @JsonProperty("ConsentId")
    private String mConsentId;
    @JsonProperty("CreationDateTime")
    private String mCreationDateTime;
    @JsonProperty("ExpirationDateTime")
    private String mExpirationDateTime;
    @JsonProperty("Permissions")
    private List<String> mPermissions;
    @JsonProperty("Status")
    private String mStatus;
    @JsonProperty("StatusUpdateDateTime")
    private String mStatusUpdateDateTime;
    @JsonProperty("TransactionFromDateTime")
    private String mTransactionFromDateTime;
    @JsonProperty("TransactionToDateTime")
    private String mTransactionToDateTime;

    public String getConsentId() {
        return mConsentId;
    }

    public String getCreationDateTime() {
        return mCreationDateTime;
    }

    public String getExpirationDateTime() {
        return mExpirationDateTime;
    }

    public List<String> getPermissions() {
        return mPermissions;
    }

    public String getStatus() {
        return mStatus;
    }

    public String getStatusUpdateDateTime() {
        return mStatusUpdateDateTime;
    }

    public String getTransactionFromDateTime() {
        return mTransactionFromDateTime;
    }

    public String getTransactionToDateTime() {
        return mTransactionToDateTime;
    }

    public static class Builder {

        private String mConsentId;
        private String mCreationDateTime;
        private String mExpirationDateTime;
        private List<String> mPermissions;
        private String mStatus;
        private String mStatusUpdateDateTime;
        private String mTransactionFromDateTime;
        private String mTransactionToDateTime;

        public Data.Builder withConsentId(String consentId) {
            mConsentId = consentId;
            return this;
        }

        public Data.Builder withCreationDateTime(String creationDateTime) {
            mCreationDateTime = creationDateTime;
            return this;
        }

        public Data.Builder withExpirationDateTime(String expirationDateTime) {
            mExpirationDateTime = expirationDateTime;
            return this;
        }

        public Data.Builder withPermissions(List<String> permissions) {
            mPermissions = permissions;
            return this;
        }

        public Data.Builder withStatus(String status) {
            mStatus = status;
            return this;
        }

        public Data.Builder withStatusUpdateDateTime(String statusUpdateDateTime) {
            mStatusUpdateDateTime = statusUpdateDateTime;
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
            data.mConsentId = mConsentId;
            data.mCreationDateTime = mCreationDateTime;
            data.mExpirationDateTime = mExpirationDateTime;
            data.mPermissions = mPermissions;
            data.mStatus = mStatus;
            data.mStatusUpdateDateTime = mStatusUpdateDateTime;
            data.mTransactionFromDateTime = mTransactionFromDateTime;
            data.mTransactionToDateTime = mTransactionToDateTime;
            return data;
        }

    }

}
