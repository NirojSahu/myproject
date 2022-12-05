package Responses.OBReadTransaction4;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Balance
{
    @JsonProperty("Type")
    private String type;

    @JsonProperty("Amount")
    private Amount amount;

    @JsonProperty("CreditDebitIndicator")
    private String creditDebitIndicator;

    public String getType ()
    {
        return type;
    }


    public Amount getAmount ()
    {
        return amount;
    }


    public String getCreditDebitIndicator ()
    {
        return creditDebitIndicator;
    }


   /* @Override
    public String toString()
    {
        return "ClassPojo [Type = "+Type+", Amount = "+Amount+", CreditDebitIndicator = "+CreditDebitIndicator+"]";
    }*/
}
