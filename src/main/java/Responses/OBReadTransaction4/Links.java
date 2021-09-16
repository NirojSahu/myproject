package Responses.OBReadTransaction4;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Links
{
    @JsonProperty("Self")
    private String self;

    @JsonProperty("Next")
    private String next;

    public String getSelf ()
    {
        return self;
    }

    public String getNext ()
    {
        return next;
    }

  /*  @Override
    public String toString()
    {
        return "ClassPojo [Self = "+Self+"]";
    }*/
}

