package api.Pojos.AccountAccessConsents;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountAccessContent {

    @JsonProperty("Data")
    public Data data;

    @JsonProperty("Risk")
    public Object risk;

    public Object getRisk ()
    {
        return risk ;
    }

    public void setRisk (Object risk)
    {
        this.risk = risk;
    }

    public Data getData ()
    {
        return data;
    }

    public void setData (Data data)
    {
        this.data = data;
    }

    @Override
    public String toString()
    {
        return "[Data = "+ data +", Risk = "+ risk +"]";
    }
}
