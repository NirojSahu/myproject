
package Responses.OBReadAccounts3;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Account {

    @JsonProperty("Account")
    private List<Account> mAccount;
    @JsonProperty("AccountId")
    private String mAccountId;
    @JsonProperty("AccountSubType")
    private String mAccountSubType;
    @JsonProperty("AccountType")
    private String mAccountType;
    @JsonProperty("Currency")
    private String mCurrency;
    @JsonProperty("Identification")
    private String mIdentification;
    @JsonProperty("Name")
    private String mName;
    @JsonProperty("Nickname")
    private String mNickname;
    @JsonProperty("OpeningDate")
    private String mOpeningDate;
    @JsonProperty("SchemeName")
    private String mSchemeName;
    @JsonProperty("SecondaryIdentification")
    private String mSecondaryIdentification;
    @JsonProperty("Status")
    private String mStatus;
    @JsonProperty("StatusUpdateDateTime")
    private String mStatusUpdateDateTime;

    public List<Account> getAccount() {
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

    public String getIdentification() {
        return mIdentification;
    }

    public String getName() {
        return mName;
    }

    public String getNickname() {
        return mNickname;
    }

    public String getOpeningDate() {
        return mOpeningDate;
    }

    public String getSchemeName() {
        return mSchemeName;
    }

    public String getSecondaryIdentification() {
        return mSecondaryIdentification;
    }

    public String getStatus() {
        return mStatus;
    }

    public String getStatusUpdateDateTime() {
        return mStatusUpdateDateTime;
    }

    public static class Builder {

        private List<Account> mAccount;
        private String mAccountId;
        private String mAccountSubType;
        private String mAccountType;
        private String mCurrency;
        private String mIdentification;
        private String mName;
        private String mNickname;
        private String mOpeningDate;
        private String mSchemeName;
        private String mSecondaryIdentification;
        private String mStatus;
        private String mStatusUpdateDateTime;

        public Account.Builder withAccount(List<Account> account) {
            mAccount = account;
            return this;
        }

        public Account.Builder withAccountId(String accountId) {
            mAccountId = accountId;
            return this;
        }

        public Account.Builder withAccountSubType(String accountSubType) {
            mAccountSubType = accountSubType;
            return this;
        }

        public Account.Builder withAccountType(String accountType) {
            mAccountType = accountType;
            return this;
        }

        public Account.Builder withCurrency(String currency) {
            mCurrency = currency;
            return this;
        }

        public Account.Builder withIdentification(String identification) {
            mIdentification = identification;
            return this;
        }

        public Account.Builder withName(String name) {
            mName = name;
            return this;
        }

        public Account.Builder withNickname(String nickname) {
            mNickname = nickname;
            return this;
        }

        public Account.Builder withOpeningDate(String openingDate) {
            mOpeningDate = openingDate;
            return this;
        }

        public Account.Builder withSchemeName(String schemeName) {
            mSchemeName = schemeName;
            return this;
        }

        public Account.Builder withSecondaryIdentification(String secondaryIdentification) {
            mSecondaryIdentification = secondaryIdentification;
            return this;
        }

        public Account.Builder withStatus(String status) {
            mStatus = status;
            return this;
        }

        public Account.Builder withStatusUpdateDateTime(String statusUpdateDateTime) {
            mStatusUpdateDateTime = statusUpdateDateTime;
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
            account.mOpeningDate = mOpeningDate;
            account.mSchemeName = mSchemeName;
            account.mSecondaryIdentification = mSecondaryIdentification;
            account.mStatus = mStatus;
            account.mStatusUpdateDateTime = mStatusUpdateDateTime;
            return account;
        }

    }

}
