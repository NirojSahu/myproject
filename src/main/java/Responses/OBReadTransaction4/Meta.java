package Responses.OBReadTransaction4;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Meta
{
    @JsonProperty("LastAvailableDateTime")
    private String lastAvailableDateTime;

    @JsonProperty("FirstAvailableDateTime")
    private String firstAvailableDateTime;

    @JsonProperty("TotalPages")
    private String totalPages;

    public String getLastAvailableDateTime ()
    {
        return lastAvailableDateTime;
    }


    public String getFirstAvailableDateTime ()
    {
        return firstAvailableDateTime;
    }

    public String getTotalPages ()
    {
        return totalPages;
    }

  /*  @Override
    public String toString()
    {
        return "ClassPojo [LastAvailableDateTime = "+LastAvailableDateTime+", FirstAvailableDateTime = "+FirstAvailableDateTime+", TotalPages = "+TotalPages+"]";
    }*/
}
