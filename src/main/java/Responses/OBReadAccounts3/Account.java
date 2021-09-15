
package Responses.OBReadAccounts3;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;

import java.util.Comparator;
import java.util.List;


public class Account {


    @JsonProperty("Account")
    private List<SubAccount> mAccount;

    @JsonProperty("AccountId")
    private String mAccountId;

    @JsonProperty("AccountSubType")
    private String mAccountSubType;

    @JsonProperty("AccountType")
    private String mAccountType;

    @JsonProperty("Currency")
    private String mCurrency;

    @JsonProperty("Nickname")
    private String mNickname;

    public List<SubAccount> getSubAccount() {
        return mAccount;
    }

    public String getAccountId() {
        return mAccountId;
    }

    public String getAccountSubType() {
        return mAccountSubType;
    }

    public String getAccountType() {
        return mAccountType;
    }

   public String getCurrency() {
        return mCurrency;
    }

     public String getNickname() {
        return mNickname;
    }

    /*public static class Builder {

        private List<Account> mAccount;
        private String mAccountId;
        private String mAccountSubType;
        private String mAccountType;
        private String mCurrency;
        private String mIdentification;
        private String mName;
        private String mNickname;
        private String mSchemeName;

        public Builder withAccount(List<Account> account) {
            mAccount = account;
            return this;
        }

        public Builder withAccountId(String accountId) {
            mAccountId = accountId;
            return this;
        }

        public Builder withAccountSubType(String accountSubType) {
            mAccountSubType = accountSubType;
            return this;
        }

        public Builder withAccountType(String accountType) {
            mAccountType = accountType;
            return this;
        }

        public Builder withCurrency(String currency) {
            mCurrency = currency;
            return this;
        }

        public Builder withIdentification(String identification) {
            mIdentification = identification;
            return this;
        }

        public Builder withName(String name) {
            mName = name;
            return this;
        }

        public Builder withNickname(String nickname) {
            mNickname = nickname;
            return this;
        }

        public Builder withSchemeName(String schemeName) {
            mSchemeName = schemeName;
            return this;
        }

        public Account build() {
            Account account = new Account();
            account.mAccount = mAccount;
            account.mAccountId = mAccountId;
            account.mAccountSubType = mAccountSubType;
            account.mAccountType = mAccountType;
            account.mCurrency = mCurrency;
            account.mIdentification = mIdentification;
            account.mName = mName;
            account.mNickname = mNickname;
            account.mSchemeName = mSchemeName;
            return account;
        }

    }*/

}
