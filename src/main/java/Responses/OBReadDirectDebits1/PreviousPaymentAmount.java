
package Responses.OBReadDirectDebits1;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;



public class PreviousPaymentAmount {

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

        public PreviousPaymentAmount.Builder withAmount(String amount) {
            mAmount = amount;
            return this;
        }

        public PreviousPaymentAmount.Builder withCurrency(String currency) {
            mCurrency = currency;
            return this;
        }

        public PreviousPaymentAmount build() {
            PreviousPaymentAmount previousPaymentAmount = new PreviousPaymentAmount();
            previousPaymentAmount.mAmount = mAmount;
            previousPaymentAmount.mCurrency = mCurrency;
            return previousPaymentAmount;
        }

    }

}
