package Responses.OBReadConsentResponse1;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Meta
{
    @JsonProperty("TotalPages")
    private String totalPages;

    public String getTotalPages ()
    {
        return totalPages;
    }

    /*@Override
    public String toString()
    {
        return "ClassPojo [TotalPages = "+TotalPages+"]";
    }*/
}

