
package Responses.OBReadTransaction4;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;



public class BankTransactionCode {

    @JsonProperty("Code")
    private String mCode;
    @JsonProperty("SubCode")
    private String mSubCode;

    public String getCode() {
        return mCode;
    }

    public String getSubCode() {
        return mSubCode;
    }

    public static class Builder {

        private String mCode;
        private String mSubCode;

        public BankTransactionCode.Builder withCode(String code) {
            mCode = code;
            return this;
        }

        public BankTransactionCode.Builder withSubCode(String subCode) {
            mSubCode = subCode;
            return this;
        }

        public BankTransactionCode build() {
            BankTransactionCode bankTransactionCode = new BankTransactionCode();
            bankTransactionCode.mCode = mCode;
            bankTransactionCode.mSubCode = mSubCode;
            return bankTransactionCode;
        }

    }

}
