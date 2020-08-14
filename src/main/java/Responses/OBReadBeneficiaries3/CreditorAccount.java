
package Responses.OBReadBeneficiaries3;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;



public class CreditorAccount {

    @JsonProperty("Identification")
    private String mIdentification;
    @JsonProperty("Name")
    private String mName;
    @JsonProperty("SchemeName")
    private String mSchemeName;

    public String getIdentification() {
        return mIdentification;
    }

    public String getName() {
        return mName;
    }

    public String getSchemeName() {
        return mSchemeName;
    }

    public static class Builder {

        private String mIdentification;
        private String mName;
        private String mSchemeName;

        public CreditorAccount.Builder withIdentification(String identification) {
            mIdentification = identification;
            return this;
        }

        public CreditorAccount.Builder withName(String name) {
            mName = name;
            return this;
        }

        public CreditorAccount.Builder withSchemeName(String schemeName) {
            mSchemeName = schemeName;
            return this;
        }

        public CreditorAccount build() {
            CreditorAccount creditorAccount = new CreditorAccount();
            creditorAccount.mIdentification = mIdentification;
            creditorAccount.mName = mName;
            creditorAccount.mSchemeName = mSchemeName;
            return creditorAccount;
        }

    }

}
