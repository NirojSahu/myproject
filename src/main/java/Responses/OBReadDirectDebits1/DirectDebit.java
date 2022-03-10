
package Responses.OBReadDirectDebits1;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DirectDebit {


    @JsonProperty("AccountId")
    private String mAccountId;
    @JsonProperty("DirectDebitId")
    private String mDirectDebitId;
    @JsonProperty("DirectDebitStatusCode")
    private String mDirectDebitStatusCode;
    @JsonProperty("MandateIdentification")
    private String mMandateIdentification;
    @JsonProperty("Name")
    private String mName;
    @JsonProperty("PreviousPaymentAmount")
    private PreviousPaymentAmount mPreviousPaymentAmount;
    @JsonProperty("PreviousPaymentDateTime")
    private String mPreviousPaymentDateTime;

    public String getAccountId() {
        return mAccountId;
    }

    public void setAccountId(String accountId) {
        mAccountId = accountId;
    }

    public String getDirectDebitId() {
        return mDirectDebitId;
    }

    public void setDirectDebitId(String directDebitId) {
        mDirectDebitId = directDebitId;
    }

    public String getDirectDebitStatusCode() {
        return mDirectDebitStatusCode;
    }

    public void setDirectDebitStatusCode(String directDebitStatusCode) {
        mDirectDebitStatusCode = directDebitStatusCode;
    }

    public String getMandateIdentification() {
        return mMandateIdentification;
    }

    public void setMandateIdentification(String mandateIdentification) {
        mMandateIdentification = mandateIdentification;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public PreviousPaymentAmount getPreviousPaymentAmount() {
        return mPreviousPaymentAmount;
    }

    public void setPreviousPaymentAmount(PreviousPaymentAmount previousPaymentAmount) {
        mPreviousPaymentAmount = previousPaymentAmount;
    }

    public String getPreviousPaymentDateTime() {
        return mPreviousPaymentDateTime;
    }

    public void setPreviousPaymentDateTime(String previousPaymentDateTime) {
        mPreviousPaymentDateTime = previousPaymentDateTime;
    }

}
