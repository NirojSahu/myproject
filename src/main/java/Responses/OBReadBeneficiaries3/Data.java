
package Responses.OBReadBeneficiaries3;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Data {

    @SerializedName("Beneficiary")
    @JsonProperty("Beneficiary")
    private List<Beneficiary> mBeneficiary;

    public List<Beneficiary> getBeneficiary() {
        return mBeneficiary;
    }

    public static class Builder {

        private List<Beneficiary> mBeneficiary;

        public Builder withBeneficiary(List<Beneficiary> beneficiary) {
            mBeneficiary = beneficiary;
            return this;
        }

        public Data build() {
            Data data = new Data();
            data.mBeneficiary = mBeneficiary;
            return data;
        }

    }

}
