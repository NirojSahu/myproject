
package Responses.OBReadBeneficiaries3;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;


public class CreditorAccount {

    @SerializedName("Identification")
    @JsonProperty("Identification")
    private String mIdentification;
    @SerializedName("Name")
    @JsonProperty("Name")
    private String mName;
    @SerializedName("SchemeName")
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

        public Builder withIdentification(String identification) {
            mIdentification = identification;
            return this;
        }

        public Builder withName(String name) {
            mName = name;
            return this;
        }

        public Builder withSchemeName(String schemeName) {
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
