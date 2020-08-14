
package Responses.OBReadStandingOrder4;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;



public class FinalPaymentAmount {

    @JsonProperty("Amount")
    private String mAmount;
    @JsonProperty("Currency")
    private String mCurrency;

    public String getAmount() {
        return mAmount;
    }

    public String getCurrency() {
        return mCurrency;
    }

    public static class Builder {

        private String mAmount;
        private String mCurrency;

        public FinalPaymentAmount.Builder withAmount(String amount) {
            mAmount = amount;
            return this;
        }

        public FinalPaymentAmount.Builder withCurrency(String currency) {
            mCurrency = currency;
            return this;
        }

        public FinalPaymentAmount build() {
            FinalPaymentAmount finalPaymentAmount = new FinalPaymentAmount();
            finalPaymentAmount.mAmount = mAmount;
            finalPaymentAmount.mCurrency = mCurrency;
            return finalPaymentAmount;
        }

    }

}
