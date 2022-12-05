package Responses.OBReadTransaction4;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Transaction
{
    @JsonProperty("Status")
    private String status;

    @JsonProperty("TransactionInformation")
    private String transactionInformation;

    @JsonProperty("BookingDateTime")
    private String bookingDateTime;

    @JsonProperty("AccountId")
    private String accountId;

    @JsonProperty("TransactionReference")
    private String transactionReference;

    @JsonProperty("Amount")
    private Amount amount;

    @JsonProperty("ValueDateTime")
    private String valueDateTime;

    @JsonProperty("CreditDebitIndicator")
    private String creditDebitIndicator;

    @JsonProperty("Balance")
    private Balance balance;

    @JsonProperty("CreditorAccount")
    private CreditorAccount creditorAccount;

   /* @JsonProperty("TransactionId")
    private String transactionId;*/

    public String getStatus ()
    {
        return status;
    }

    public String getTransactionInformation ()
    {
        return transactionInformation;
    }

    public String getBookingDateTime ()
    {
        return bookingDateTime;
    }

    public String getAccountId ()
    {
        return accountId;
    }

    public String getTransactionReference ()
    {
        return transactionReference;
    }

    public Amount getAmount ()
    {
        return amount;
    }

    public String getValueDateTime ()
    {
        return valueDateTime;
    }


    public String getCreditDebitIndicator ()
    {
        return creditDebitIndicator;
    }

    public Balance getBalance ()
    {
        return balance;
    }

    public CreditorAccount getCreditorAccount ()
    {
        return creditorAccount;
    }

    /*public String getTransactionId ()
    {
        return transactionId;
    }*/

  /*  @Override
    public String toString()
    {
        return "ClassPojo [Status = "+Status+", TransactionInformation = "+TransactionInformation+", BookingDateTime = "+BookingDateTime+", AccountId = "+AccountId+", TransactionReference = "+TransactionReference+", Amount = "+Amount+", ValueDateTime = "+ValueDateTime+", CreditDebitIndicator = "+CreditDebitIndicator+", Balance = "+Balance+", TransactionId = "+TransactionId+"]";
    }*/
}
