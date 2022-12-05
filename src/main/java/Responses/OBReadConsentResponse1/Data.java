package Responses.OBReadConsentResponse1;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Data
{
    @JsonProperty("Status")
    private String status;

    @JsonProperty("StatusUpdateDateTime")
    private String statusUpdateDateTime;

    @JsonProperty("CreationDateTime")
    private String creationDateTime;

    @JsonProperty("TransactionToDateTime")
    private String transactionToDateTime;

    @JsonProperty("ExpirationDateTime")
    private String expirationDateTime;

    @JsonProperty("Permissions")
    private List<String> permissions;

    @JsonProperty("ConsentId")
    private String consentId;

    @JsonProperty("TransactionFromDateTime")
    private String transactionFromDateTime;

    public String getStatus ()
    {
        return status;
    }

    public String getStatusUpdateDateTime ()
    {
        return statusUpdateDateTime;
    }

    public String getCreationDateTime ()
    {
        return creationDateTime;
    }

    public String getTransactionToDateTime ()
    {
        return transactionToDateTime;
    }

    public String getExpirationDateTime ()
    {
        return expirationDateTime;
    }

    public List<String> getPermissions ()
    {
        return permissions;
    }

    public String getConsentId ()
    {
        return consentId;
    }

    public String getTransactionFromDateTime ()
    {
        return transactionFromDateTime;
    }

    /*@Override
    public String toString()
    {
        return "ClassPojo [Status = "+Status+", StatusUpdateDateTime = "+StatusUpdateDateTime+", CreationDateTime = "+CreationDateTime+", TransactionToDateTime = "+TransactionToDateTime+", ExpirationDateTime = "+ExpirationDateTime+", Permissions = "+Permissions+", ConsentId = "+ConsentId+", TransactionFromDateTime = "+TransactionFromDateTime+"]";
    }*/
}
