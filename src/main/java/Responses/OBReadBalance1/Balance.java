package Responses.OBReadBalance1;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Balance {

    @JsonProperty("Type")
    private String type;

    @JsonProperty("AccountId")
    private String accountId;

    @JsonProperty("CreditLine")
    private List<CreditLine> creditLine;

    @JsonProperty("Amount")
    private Amount amount;

    @JsonProperty("CreditDebitIndicator")
    private String creditDebitIndicator;

    @JsonProperty("DateTime")
    private String dateTime;

    public String getType ()
    {
        return type;
    }

    public String getAccountId ()
    {
        return accountId;
    }

    public List<CreditLine> getCreditLine ()
    {
        return creditLine;
    }
    public Amount getAmount ()
    {
        return amount;
    }

    public String getCreditDebitIndicator ()
    {
        return creditDebitIndicator;
    }

    public String getDateTime ()
    {
        return dateTime;
    }

    /*@Override
    public String toString()
    {
        return "ClassPojo [Type = "+Type+", AccountId = "+AccountId+", CreditLine = "+CreditLine+", Amount = "+Amount+", CreditDebitIndicator = "+CreditDebitIndicator+", DateTime = "+DateTime+"]";
    }*/
}
