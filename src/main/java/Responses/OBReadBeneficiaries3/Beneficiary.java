
package Responses.OBReadBeneficiaries3;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;



public class Beneficiary {

    @JsonProperty("AccountId")
    private String mAccountId;
    @JsonProperty("BeneficiaryId")
    private String mBeneficiaryId;
    @JsonProperty("CreditorAccount")
    private CreditorAccount mCreditorAccount;
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

        public Beneficiary.Builder withAccountId(String accountId) {
            mAccountId = accountId;
            return this;
        }

        public Beneficiary.Builder withBeneficiaryId(String beneficiaryId) {
            mBeneficiaryId = beneficiaryId;
            return this;
        }

        public Beneficiary.Builder withCreditorAccount(CreditorAccount creditorAccount) {
            mCreditorAccount = creditorAccount;
            return this;
        }

        public Beneficiary.Builder withReference(String reference) {
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
