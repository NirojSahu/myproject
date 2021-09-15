package api.Pojos.AccountAccessConsents;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Data
{
    @JsonProperty("Permissions")
    private List<String> permissions;

    @JsonProperty("TransactionToDateTime")
    private String transactionToDateTime;

    @JsonProperty("ExpirationDateTime")
    private String expirationDateTime;

    @JsonProperty("TransactionFromDateTime")
    private String transactionFromDateTime;

    public String getTransactionToDateTime ()
    {
        return transactionToDateTime;
    }

    public void setTransactionToDateTime (String transactionToDateTime)
    {
        this.transactionToDateTime = transactionToDateTime;
    }

    public String getExpirationDateTime ()
    {
        return expirationDateTime;
    }

    public void setExpirationDateTime (String expirationDateTime)
    {
        this.expirationDateTime = expirationDateTime;
    }

    public List<String> getPermissions ()
    {
        return permissions;
    }

    public void setPermissions (List<String> permissions)
    {
        this.permissions = permissions;
    }

    public String getTransactionFromDateTime ()
    {
        return transactionFromDateTime;
    }

    public void setTransactionFromDateTime (String transactionFromDateTime)
    {
        this.transactionFromDateTime = transactionFromDateTime;
    }

  /*  @Override
    public String toString()
    {
        return "ClassPojo [TransactionToDateTime = "+TransactionToDateTime+", ExpirationDateTime = "+ExpirationDateTime+", Permissions = "+Permissions+", TransactionFromDateTime = "+TransactionFromDateTime+"]";
    }*/
}
