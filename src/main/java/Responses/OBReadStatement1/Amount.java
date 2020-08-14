
package Responses.OBReadStatement1;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;



public class Amount {

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

        public Amount.Builder withAmount(String amount) {
            mAmount = amount;
            return this;
        }

        public Amount.Builder withCurrency(String currency) {
            mCurrency = currency;
            return this;
        }

        public Amount build() {
            Amount amount = new Amount();
            amount.mAmount = mAmount;
            amount.mCurrency = mCurrency;
            return amount;
        }

    }

}
