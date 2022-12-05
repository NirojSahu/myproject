
package Responses.OBReadStatement1;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;



public class StatementAmount {

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

        public StatementAmount.Builder withAmount(Amount amount) {
            mAmount = amount;
            return this;
        }

        public StatementAmount.Builder withCreditDebitIndicator(String creditDebitIndicator) {
            mCreditDebitIndicator = creditDebitIndicator;
            return this;
        }

        public StatementAmount.Builder withType(String type) {
            mType = type;
            return this;
        }

        public StatementAmount build() {
            StatementAmount statementAmount = new StatementAmount();
            statementAmount.mAmount = mAmount;
            statementAmount.mCreditDebitIndicator = mCreditDebitIndicator;
            statementAmount.mType = mType;
            return statementAmount;
        }

    }

}
