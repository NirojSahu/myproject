package Responses.OBReadTransaction4;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OBReadTransaction
{
    @JsonProperty("Meta")
    private Meta meta;

    @JsonProperty("Links")
    private Links links;

    @JsonProperty("Data")
    private Data data;

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
        return "ClassPojo [Meta = "+Meta+", Links = "+Links+", Data = "+Data+"]";
    }*/
}
