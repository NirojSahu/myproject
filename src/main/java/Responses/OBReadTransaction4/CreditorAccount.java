package Responses.OBReadTransaction4;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreditorAccount
{
    @JsonProperty("SchemeName")
    private String schemeName;

    @JsonProperty("Identification")
    private String identification;

    @JsonProperty("Name")
    private String name;

    public String getType ()
    {
        return schemeName;
    }


    public String getAmount ()
    {
        return identification;
    }


    public String getCreditDebitIndicator ()
    {
        return name;
    }


   /* @Override
    public String toString()
    {
        return "ClassPojo [Type = "+Type+", Amount = "+Amount+", CreditDebitIndicator = "+CreditDebitIndicator+"]";
    }*/
}
