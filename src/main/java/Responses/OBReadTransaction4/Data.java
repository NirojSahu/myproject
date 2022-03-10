package Responses.OBReadTransaction4;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Data
{
    @JsonProperty("Transaction")
    private List<Transaction> transaction;

    public List<Transaction> getTransaction ()
    {
        return transaction;
    }


    /*@Override
    public String toString()
    {
        return "ClassPojo [Transaction = "+Transaction+"]";
    }*/
}

