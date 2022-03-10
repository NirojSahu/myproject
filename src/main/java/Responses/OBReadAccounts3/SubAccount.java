package Responses.OBReadAccounts3;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SubAccount {
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
}
