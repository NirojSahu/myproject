package Responses.OBReadBalance1;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Amount {

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
        return "ClassPojo [Amount = "+amount+", Currency = "+currency+"]";
    }*/
}
