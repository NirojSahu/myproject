
package Responses.OBReadBalance1;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import javax.annotation.Generated;



public class Balance {

    @JsonProperty("AccountId")
    private String mAccountId;
    @JsonProperty("Amount")
    private Amount mAmount;
    @JsonProperty("CreditDebitIndicator")
    private String mCreditDebitIndicator;
    @JsonProperty("CreditLine")
    private List<CreditLine> mCreditLine;
    @JsonProperty("DateTime")
    private String mDateTime;
    @JsonProperty("Type")
    private String mType;

    public String getAccountId() {
        return mAccountId;
    }

    public Amount getAmount() {
        return mAmount;
    }

    public String getCreditDebitIndicator() {
        return mCreditDebitIndicator;
    }

    public List<CreditLine> getCreditLine() {
        return mCreditLine;
    }

    public String getDateTime() {
        return mDateTime;
    }

    public String getType() {
        return mType;
    }

    public static class Builder {

        private String mAccountId;
        private Amount mAmount;
        private String mCreditDebitIndicator;
        private List<CreditLine> mCreditLine;
        private String mDateTime;
        private String mType;

        public Balance.Builder withAccountId(String accountId) {
            mAccountId = accountId;
            return this;
        }

        public Balance.Builder withAmount(Amount amount) {
            mAmount = amount;
            return this;
        }

        public Balance.Builder withCreditDebitIndicator(String creditDebitIndicator) {
            mCreditDebitIndicator = creditDebitIndicator;
            return this;
        }

        public Balance.Builder withCreditLine(List<CreditLine> creditLine) {
            mCreditLine = creditLine;
            return this;
        }

        public Balance.Builder withDateTime(String dateTime) {
            mDateTime = dateTime;
            return this;
        }

        public Balance.Builder withType(String type) {
            mType = type;
            return this;
        }

        public Balance build() {
            Balance balance = new Balance();
            balance.mAccountId = mAccountId;
            balance.mAmount = mAmount;
            balance.mCreditDebitIndicator = mCreditDebitIndicator;
            balance.mCreditLine = mCreditLine;
            balance.mDateTime = mDateTime;
            balance.mType = mType;
            return balance;
        }

    }

}
