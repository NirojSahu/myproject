
package Responses.OBReadTransaction4;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;



public class Balance {

    @JsonProperty("Amount")
    private Amount mAmount;
    @JsonProperty("CreditDebitIndicator")
    private String mCreditDebitIndicator;
    @JsonProperty("Type")
    private String mType;

    public Amount getAmount() {
        return mAmount;
    }

    public String getCreditDebitIndicator() {
        return mCreditDebitIndicator;
    }

    public String getType() {
        return mType;
    }

    public static class Builder {

        private Amount mAmount;
        private String mCreditDebitIndicator;
        private String mType;

        public Balance.Builder withAmount(Amount amount) {
            mAmount = amount;
            return this;
        }

        public Balance.Builder withCreditDebitIndicator(String creditDebitIndicator) {
            mCreditDebitIndicator = creditDebitIndicator;
            return this;
        }

        public Balance.Builder withType(String type) {
            mType = type;
            return this;
        }

        public Balance build() {
            Balance balance = new Balance();
            balance.mAmount = mAmount;
            balance.mCreditDebitIndicator = mCreditDebitIndicator;
            balance.mType = mType;
            return balance;
        }

    }

}
