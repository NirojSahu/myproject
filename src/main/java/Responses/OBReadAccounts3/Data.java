
package Responses.OBReadAccounts3;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public class Data {

   // @SerializedName("Account")
    @JsonProperty("Account")
    private List<Account> mAccount;

    public List<Account> getAccount() {
        return mAccount;
    }

    public static class Builder {

        private List<Account> mAccount;

        public Builder withAccount(List<Account> account) {
            mAccount = account;
            return this;
        }

        public Data build() {
            Data data = new Data();
            data.mAccount = mAccount;
            return data;
        }

    }

}
