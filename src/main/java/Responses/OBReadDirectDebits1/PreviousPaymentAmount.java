
package Responses.OBReadDirectDebits1;

import com.fasterxml.jackson.annotation.JsonProperty;


public class PreviousPaymentAmount {


    @JsonProperty("Amount")
    private String mAmount;
    @JsonProperty("Currency")
    private String mCurrency;

    public String getAmount() {
        return mAmount;
    }

    public void setAmount(String amount) {
        mAmount = amount;
    }

    public String getCurrency() {
        return mCurrency;
    }

    public void setCurrency(String currency) {
        mCurrency = currency;
    }

}
