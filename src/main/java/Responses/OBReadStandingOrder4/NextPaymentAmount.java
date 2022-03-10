
package Responses.OBReadStandingOrder4;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;



public class NextPaymentAmount {

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

        public NextPaymentAmount.Builder withAmount(String amount) {
            mAmount = amount;
            return this;
        }

        public NextPaymentAmount.Builder withCurrency(String currency) {
            mCurrency = currency;
            return this;
        }

        public NextPaymentAmount build() {
            NextPaymentAmount nextPaymentAmount = new NextPaymentAmount();
            nextPaymentAmount.mAmount = mAmount;
            nextPaymentAmount.mCurrency = mCurrency;
            return nextPaymentAmount;
        }

    }

}
