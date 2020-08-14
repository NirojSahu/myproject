
package Responses.OBReadDirectDebits1;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;



public class DirectDebit {

    @JsonProperty("AccountId")
    private String mAccountId;
    @JsonProperty("DirectDebitId")
    private String mDirectDebitId;
    @JsonProperty("DirectDebitStatusCode")
    private String mDirectDebitStatusCode;
    @JsonProperty("MandateIdentification")
    private String mMandateIdentification;
    @JsonProperty("Name")
    private String mName;
    @JsonProperty("PreviousPaymentAmount")
    private PreviousPaymentAmount mPreviousPaymentAmount;
    @JsonProperty("PreviousPaymentDateTime")
    private String mPreviousPaymentDateTime;

    public String getAccountId() {
        return mAccountId;
    }

    public String getDirectDebitId() {
        return mDirectDebitId;
    }

    public String getDirectDebitStatusCode() {
        return mDirectDebitStatusCode;
    }

    public String getMandateIdentification() {
        return mMandateIdentification;
    }

    public String getName() {
        return mName;
    }

    public PreviousPaymentAmount getPreviousPaymentAmount() {
        return mPreviousPaymentAmount;
    }

    public String getPreviousPaymentDateTime() {
        return mPreviousPaymentDateTime;
    }

    public static class Builder {

        private String mAccountId;
        private String mDirectDebitId;
        private String mDirectDebitStatusCode;
        private String mMandateIdentification;
        private String mName;
        private PreviousPaymentAmount mPreviousPaymentAmount;
        private String mPreviousPaymentDateTime;

        public DirectDebit.Builder withAccountId(String accountId) {
            mAccountId = accountId;
            return this;
        }

        public DirectDebit.Builder withDirectDebitId(String directDebitId) {
            mDirectDebitId = directDebitId;
            return this;
        }

        public DirectDebit.Builder withDirectDebitStatusCode(String directDebitStatusCode) {
            mDirectDebitStatusCode = directDebitStatusCode;
            return this;
        }

        public DirectDebit.Builder withMandateIdentification(String mandateIdentification) {
            mMandateIdentification = mandateIdentification;
            return this;
        }

        public DirectDebit.Builder withName(String name) {
            mName = name;
            return this;
        }

        public DirectDebit.Builder withPreviousPaymentAmount(PreviousPaymentAmount previousPaymentAmount) {
            mPreviousPaymentAmount = previousPaymentAmount;
            return this;
        }

        public DirectDebit.Builder withPreviousPaymentDateTime(String previousPaymentDateTime) {
            mPreviousPaymentDateTime = previousPaymentDateTime;
            return this;
        }

        public DirectDebit build() {
            DirectDebit directDebit = new DirectDebit();
            directDebit.mAccountId = mAccountId;
            directDebit.mDirectDebitId = mDirectDebitId;
            directDebit.mDirectDebitStatusCode = mDirectDebitStatusCode;
            directDebit.mMandateIdentification = mMandateIdentification;
            directDebit.mName = mName;
            directDebit.mPreviousPaymentAmount = mPreviousPaymentAmount;
            directDebit.mPreviousPaymentDateTime = mPreviousPaymentDateTime;
            return directDebit;
        }

    }

}
