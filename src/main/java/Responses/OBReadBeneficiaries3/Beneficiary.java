
package Responses.OBReadBeneficiaries3;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;


public class Beneficiary {

    @SerializedName("AccountId")
    @JsonProperty("AccountId")
    private String mAccountId;
    @SerializedName("BeneficiaryId")
    @JsonProperty("BeneficiaryId")
    private String mBeneficiaryId;
    @SerializedName("CreditorAccount")
    @JsonProperty("CreditorAccount")
    private CreditorAccount mCreditorAccount;
    @SerializedName("Reference")
    @JsonProperty("Reference")
    private String mReference;

    public String getAccountId() {
        return mAccountId;
    }

    public String getBeneficiaryId() {
        return mBeneficiaryId;
    }

    public CreditorAccount getCreditorAccount() {
        return mCreditorAccount;
    }

    public String getReference() {
        return mReference;
    }

    public static class Builder {

        private String mAccountId;
        private String mBeneficiaryId;
        private CreditorAccount mCreditorAccount;
        private String mReference;

        public Builder withAccountId(String accountId) {
            mAccountId = accountId;
            return this;
        }

        public Builder withBeneficiaryId(String beneficiaryId) {
            mBeneficiaryId = beneficiaryId;
            return this;
        }

        public Builder withCreditorAccount(CreditorAccount creditorAccount) {
            mCreditorAccount = creditorAccount;
            return this;
        }

        public Builder withReference(String reference) {
            mReference = reference;
            return this;
        }

        public Beneficiary build() {
            Beneficiary beneficiary = new Beneficiary();
            beneficiary.mAccountId = mAccountId;
            beneficiary.mBeneficiaryId = mBeneficiaryId;
            beneficiary.mCreditorAccount = mCreditorAccount;
            beneficiary.mReference = mReference;
            return beneficiary;
        }

    }

}
