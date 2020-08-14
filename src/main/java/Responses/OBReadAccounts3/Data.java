
package Responses.OBReadAccounts3;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;



public class Data {

    @JsonProperty("Account")
    private List<Account> mAccount;

    public List<Account> getAccount() {
        return mAccount;
    }

    public static class Builder {

        private List<Account> mAccount;

        public Data.Builder withAccount(List<Account> account) {
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
