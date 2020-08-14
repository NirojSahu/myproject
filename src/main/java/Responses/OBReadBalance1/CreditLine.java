
package Responses.OBReadBalance1;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;


public class CreditLine {

    @JsonProperty("Amount")
    private Amount mAmount;
    @JsonProperty("Included")
    private Boolean mIncluded;
    @JsonProperty("Type")
    private String mType;

    public Amount getAmount() {
        return mAmount;
    }

    public Boolean getIncluded() {
        return mIncluded;
    }

    public String getType() {
        return mType;
    }

    public static class Builder {

        private Amount mAmount;
        private Boolean mIncluded;
        private String mType;

        public CreditLine.Builder withAmount(Amount amount) {
            mAmount = amount;
            return this;
        }

        public CreditLine.Builder withIncluded(Boolean included) {
            mIncluded = included;
            return this;
        }

        public CreditLine.Builder withType(String type) {
            mType = type;
            return this;
        }

        public CreditLine build() {
            CreditLine creditLine = new CreditLine();
            creditLine.mAmount = mAmount;
            creditLine.mIncluded = mIncluded;
            creditLine.mType = mType;
            return creditLine;
        }

    }

}
