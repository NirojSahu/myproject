package Responses.OBReadBalance1;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreditLine {

    @JsonProperty("Type")
    private String type;

    @JsonProperty("Included")
    private String included;

    @JsonProperty("Amount")
    private Amount amount;

    public String getType ()
    {
        return type;
    }

    public String getIncluded ()
    {
        return included;
    }

    public Amount getAmount ()
    {
        return amount;
    }


    /*@Override
    public String toString()
    {
        return "ClassPojo [Type = "+Type+", Included = "+Included+", Amount = "+Amount+"]";
    }*/
}
