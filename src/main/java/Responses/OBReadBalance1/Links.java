package Responses.OBReadBalance1;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Links
{
    @JsonProperty("Self")
    private String self;

    public String getSelf ()
    {
        return self;
    }

   /* @Override
    public String toString()
    {
        return "ClassPojo [Self = "+Self+"]";
    }*/
}
