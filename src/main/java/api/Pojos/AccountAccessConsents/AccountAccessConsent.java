
package api.Pojos.AccountAccessConsents;

import com.fasterxml.jackson.annotation.JsonProperty;
public class AccountAccessConsent {

    @JsonProperty("Data")
    private Data mData;
    @JsonProperty("Risk")
    private Risk mRisk;

    public Data getData() {
        return mData;
    }

    public Risk getRisk() {
        return mRisk;
    }

    public static class Builder {

        private Data mData;
        private Risk mRisk;

        public AccountAccessConsent.Builder withData(Data data) {
            mData = data;
            return this;
        }

        public AccountAccessConsent.Builder withRisk(Risk risk) {
            mRisk = risk;
            return this;
        }

        public AccountAccessConsent build() {
            AccountAccessConsent accountAccessConsent = new AccountAccessConsent();
            accountAccessConsent.mData = mData;
            accountAccessConsent.mRisk = mRisk;
            return accountAccessConsent;
        }

    }

}
