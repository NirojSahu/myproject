package Responses.OBReadBalance1;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Data {
    @JsonProperty("Balance")
    private List<Balance> balance;

    public List<Balance> getBalance ()
    {
        return balance;
    }

    /*@Override
    public String toString()
    {
        return "ClassPojo [Balance = "+Balance+"]";
    }*/
}
