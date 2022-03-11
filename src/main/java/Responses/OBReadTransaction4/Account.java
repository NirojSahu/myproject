package Responses.OBReadTransaction4;

import com.fasterxml.jackson.annotation.JsonProperty;

class Amount
{
    @JsonProperty("Amount")
    private String amount;

    @JsonProperty("Currency")
    private String currency;

    public String getAmount ()
    {
        return amount;
    }

    public String getCurrency ()
    {
        return currency;
    }

    /*@Override
    public String toString()
    {
        return "ClassPojo [Amount = "+Amount+", Currency = "+Currency+"]";
    }*/
}
