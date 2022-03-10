
package Responses.OBReadStandingOrder4;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;



public class FirstPaymentAmount {

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

        public FirstPaymentAmount.Builder withAmount(String amount) {
            mAmount = amount;
            return this;
        }

        public FirstPaymentAmount.Builder withCurrency(String currency) {
            mCurrency = currency;
            return this;
        }

        public FirstPaymentAmount build() {
            FirstPaymentAmount firstPaymentAmount = new FirstPaymentAmount();
            firstPaymentAmount.mAmount = mAmount;
            firstPaymentAmount.mCurrency = mCurrency;
            return firstPaymentAmount;
        }

    }

}
