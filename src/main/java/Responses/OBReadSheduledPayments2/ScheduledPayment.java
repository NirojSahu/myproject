
package Responses.OBReadSheduledPayments2;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;



public class ScheduledPayment {

    @JsonProperty("AccountId")
    private String mAccountId;
    @JsonProperty("CreditorAccount")
    private CreditorAccount mCreditorAccount;
    @JsonProperty("InstructedAmount")
    private InstructedAmount mInstructedAmount;
    @JsonProperty("ScheduledPaymentDateTime")
    private String mScheduledPaymentDateTime;
    @JsonProperty("ScheduledPaymentId")
    private String mScheduledPaymentId;
    @JsonProperty("ScheduledType")
    private String mScheduledType;

    public String getAccountId() {
        return mAccountId;
    }

    public CreditorAccount getCreditorAccount() {
        return mCreditorAccount;
    }

    public InstructedAmount getInstructedAmount() {
        return mInstructedAmount;
    }

    public String getScheduledPaymentDateTime() {
        return mScheduledPaymentDateTime;
    }

    public String getScheduledPaymentId() {
        return mScheduledPaymentId;
    }

    public String getScheduledType() {
        return mScheduledType;
    }

    public static class Builder {

        private String mAccountId;
        private CreditorAccount mCreditorAccount;
        private InstructedAmount mInstructedAmount;
        private String mScheduledPaymentDateTime;
        private String mScheduledPaymentId;
        private String mScheduledType;

        public ScheduledPayment.Builder withAccountId(String accountId) {
            mAccountId = accountId;
            return this;
        }

        public ScheduledPayment.Builder withCreditorAccount(CreditorAccount creditorAccount) {
            mCreditorAccount = creditorAccount;
            return this;
        }

        public ScheduledPayment.Builder withInstructedAmount(InstructedAmount instructedAmount) {
            mInstructedAmount = instructedAmount;
            return this;
        }

        public ScheduledPayment.Builder withScheduledPaymentDateTime(String scheduledPaymentDateTime) {
            mScheduledPaymentDateTime = scheduledPaymentDateTime;
            return this;
        }

        public ScheduledPayment.Builder withScheduledPaymentId(String scheduledPaymentId) {
            mScheduledPaymentId = scheduledPaymentId;
            return this;
        }

        public ScheduledPayment.Builder withScheduledType(String scheduledType) {
            mScheduledType = scheduledType;
            return this;
        }

        public ScheduledPayment build() {
            ScheduledPayment scheduledPayment = new ScheduledPayment();
            scheduledPayment.mAccountId = mAccountId;
            scheduledPayment.mCreditorAccount = mCreditorAccount;
            scheduledPayment.mInstructedAmount = mInstructedAmount;
            scheduledPayment.mScheduledPaymentDateTime = mScheduledPaymentDateTime;
            scheduledPayment.mScheduledPaymentId = mScheduledPaymentId;
            scheduledPayment.mScheduledType = mScheduledType;
            return scheduledPayment;
        }

    }

}
