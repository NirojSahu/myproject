
package Responses.OBReadStandingOrder4;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;


public class StandingOrder {

    @JsonProperty("AccountId")
    private String mAccountId;
    @JsonProperty("CreditorAccount")
    private CreditorAccount mCreditorAccount;
    @JsonProperty("FinalPaymentAmount")
    private FinalPaymentAmount mFinalPaymentAmount;
    @JsonProperty("FinalPaymentDateTime")
    private String mFinalPaymentDateTime;
    @JsonProperty("FirstPaymentAmount")
    private FirstPaymentAmount mFirstPaymentAmount;
    @JsonProperty("FirstPaymentDateTime")
    private String mFirstPaymentDateTime;
    @JsonProperty("Frequency")
    private String mFrequency;
    @JsonProperty("NextPaymentAmount")
    private NextPaymentAmount mNextPaymentAmount;
    @JsonProperty("NextPaymentDateTime")
    private String mNextPaymentDateTime;
    @JsonProperty("Reference")
    private String mReference;
    @JsonProperty("StandingOrderId")
    private String mStandingOrderId;
    @JsonProperty("StandingOrderStatusCode")
    private String mStandingOrderStatusCode;

    public String getAccountId() {
        return mAccountId;
    }

    public CreditorAccount getCreditorAccount() {
        return mCreditorAccount;
    }

    public FinalPaymentAmount getFinalPaymentAmount() {
        return mFinalPaymentAmount;
    }

    public String getFinalPaymentDateTime() {
        return mFinalPaymentDateTime;
    }

    public FirstPaymentAmount getFirstPaymentAmount() {
        return mFirstPaymentAmount;
    }

    public String getFirstPaymentDateTime() {
        return mFirstPaymentDateTime;
    }

    public String getFrequency() {
        return mFrequency;
    }

    public NextPaymentAmount getNextPaymentAmount() {
        return mNextPaymentAmount;
    }

    public String getNextPaymentDateTime() {
        return mNextPaymentDateTime;
    }

    public String getReference() {
        return mReference;
    }

    public String getStandingOrderId() {
        return mStandingOrderId;
    }

    public String getStandingOrderStatusCode() {
        return mStandingOrderStatusCode;
    }

    public static class Builder {

        private String mAccountId;
        private CreditorAccount mCreditorAccount;
        private FinalPaymentAmount mFinalPaymentAmount;
        private String mFinalPaymentDateTime;
        private FirstPaymentAmount mFirstPaymentAmount;
        private String mFirstPaymentDateTime;
        private String mFrequency;
        private NextPaymentAmount mNextPaymentAmount;
        private String mNextPaymentDateTime;
        private String mReference;
        private String mStandingOrderId;
        private String mStandingOrderStatusCode;

        public StandingOrder.Builder withAccountId(String accountId) {
            mAccountId = accountId;
            return this;
        }

        public StandingOrder.Builder withCreditorAccount(CreditorAccount creditorAccount) {
            mCreditorAccount = creditorAccount;
            return this;
        }

        public StandingOrder.Builder withFinalPaymentAmount(FinalPaymentAmount finalPaymentAmount) {
            mFinalPaymentAmount = finalPaymentAmount;
            return this;
        }

        public StandingOrder.Builder withFinalPaymentDateTime(String finalPaymentDateTime) {
            mFinalPaymentDateTime = finalPaymentDateTime;
            return this;
        }

        public StandingOrder.Builder withFirstPaymentAmount(FirstPaymentAmount firstPaymentAmount) {
            mFirstPaymentAmount = firstPaymentAmount;
            return this;
        }

        public StandingOrder.Builder withFirstPaymentDateTime(String firstPaymentDateTime) {
            mFirstPaymentDateTime = firstPaymentDateTime;
            return this;
        }

        public StandingOrder.Builder withFrequency(String frequency) {
            mFrequency = frequency;
            return this;
        }

        public StandingOrder.Builder withNextPaymentAmount(NextPaymentAmount nextPaymentAmount) {
            mNextPaymentAmount = nextPaymentAmount;
            return this;
        }

        public StandingOrder.Builder withNextPaymentDateTime(String nextPaymentDateTime) {
            mNextPaymentDateTime = nextPaymentDateTime;
            return this;
        }

        public StandingOrder.Builder withReference(String reference) {
            mReference = reference;
            return this;
        }

        public StandingOrder.Builder withStandingOrderId(String standingOrderId) {
            mStandingOrderId = standingOrderId;
            return this;
        }

        public StandingOrder.Builder withStandingOrderStatusCode(String standingOrderStatusCode) {
            mStandingOrderStatusCode = standingOrderStatusCode;
            return this;
        }

        public StandingOrder build() {
            StandingOrder standingOrder = new StandingOrder();
            standingOrder.mAccountId = mAccountId;
            standingOrder.mCreditorAccount = mCreditorAccount;
            standingOrder.mFinalPaymentAmount = mFinalPaymentAmount;
            standingOrder.mFinalPaymentDateTime = mFinalPaymentDateTime;
            standingOrder.mFirstPaymentAmount = mFirstPaymentAmount;
            standingOrder.mFirstPaymentDateTime = mFirstPaymentDateTime;
            standingOrder.mFrequency = mFrequency;
            standingOrder.mNextPaymentAmount = mNextPaymentAmount;
            standingOrder.mNextPaymentDateTime = mNextPaymentDateTime;
            standingOrder.mReference = mReference;
            standingOrder.mStandingOrderId = mStandingOrderId;
            standingOrder.mStandingOrderStatusCode = mStandingOrderStatusCode;
            return standingOrder;
        }

    }

}
