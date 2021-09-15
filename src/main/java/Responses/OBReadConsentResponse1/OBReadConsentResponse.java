package Responses.OBReadConsentResponse1;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OBReadConsentResponse
{
    @JsonProperty("Meta")
    private Meta meta;

    /*@JsonProperty("Risk")
    private String risk;*/

    @JsonProperty("Links")
    private Links links;

    @JsonProperty("Data")
    private Data data;

    @JsonProperty("Risk")
    public Object risk;

    public Object getRisk ()
    {
        return risk ;
    }

    public Meta getMeta ()
    {
        return meta;
    }

    public Links getLinks ()
    {
        return links;
    }

    public Data getData ()
    {
        return data;
    }



   /* @Override
    public String toString()
    {
        return "ClassPojo [Meta = "+Meta+", Risk = "+Risk+", Links = "+Links+", Data = "+Data+"]";
    }*/
}
