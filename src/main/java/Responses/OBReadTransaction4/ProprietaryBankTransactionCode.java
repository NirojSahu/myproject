
package Responses.OBReadTransaction4;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;



public class ProprietaryBankTransactionCode {

    @JsonProperty("Code")
    private String mCode;
    @JsonProperty("Issuer")
    private String mIssuer;

    public String getCode() {
        return mCode;
    }

    public String getIssuer() {
        return mIssuer;
    }

    public static class Builder {

        private String mCode;
        private String mIssuer;

        public ProprietaryBankTransactionCode.Builder withCode(String code) {
            mCode = code;
            return this;
        }

        public ProprietaryBankTransactionCode.Builder withIssuer(String issuer) {
            mIssuer = issuer;
            return this;
        }

        public ProprietaryBankTransactionCode build() {
            ProprietaryBankTransactionCode proprietaryBankTransactionCode = new ProprietaryBankTransactionCode();
            proprietaryBankTransactionCode.mCode = mCode;
            proprietaryBankTransactionCode.mIssuer = mIssuer;
            return proprietaryBankTransactionCode;
        }

    }

}
