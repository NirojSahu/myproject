
package Responses.OBReadSheduledPayments2;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;


public class InstructedAmount {

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

        public InstructedAmount.Builder withAmount(String amount) {
            mAmount = amount;
            return this;
        }

        public InstructedAmount.Builder withCurrency(String currency) {
            mCurrency = currency;
            return this;
        }

        public InstructedAmount build() {
            InstructedAmount instructedAmount = new InstructedAmount();
            instructedAmount.mAmount = mAmount;
            instructedAmount.mCurrency = mCurrency;
            return instructedAmount;
        }

    }

}
