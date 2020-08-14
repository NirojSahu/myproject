
package Responses.OBReadBeneficiaries3;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import javax.annotation.Generated;



public class Data {

    @JsonProperty("Beneficiary")
    private List<Beneficiary> mBeneficiary;

    public List<Beneficiary> getBeneficiary() {
        return mBeneficiary;
    }

    public static class Builder {

        private List<Beneficiary> mBeneficiary;

        public Data.Builder withBeneficiary(List<Beneficiary> beneficiary) {
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
