
package Responses.OBReadTransaction4;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.Generated;



public class Transaction {

    @JsonProperty("AccountId")
    private String mAccountId;
    @JsonProperty("Amount")
    private Amount mAmount;
    @JsonProperty("Balance")
    private Balance mBalance;
    @JsonProperty("BankTransactionCode")
    private BankTransactionCode mBankTransactionCode;
    @JsonProperty("BookingDateTime")
    private String mBookingDateTime;
    @JsonProperty("CreditDebitIndicator")
    private String mCreditDebitIndicator;
    @JsonProperty("ProprietaryBankTransactionCode")
    private ProprietaryBankTransactionCode mProprietaryBankTransactionCode;
    @JsonProperty("Status")
    private String mStatus;
    @JsonProperty("TransactionId")
    private String mTransactionId;
    @JsonProperty("TransactionInformation")
    private String mTransactionInformation;
    @JsonProperty("TransactionReference")
    private String mTransactionReference;
    @JsonProperty("ValueDateTime")
    private String mValueDateTime;

    public String getAccountId() {
        return mAccountId;
    }

    public Amount getAmount() {
        return mAmount;
    }

    public Balance getBalance() {
        return mBalance;
    }

    public BankTransactionCode getBankTransactionCode() {
        return mBankTransactionCode;
    }

    public String getBookingDateTime() {
        return mBookingDateTime;
    }

    public String getCreditDebitIndicator() {
        return mCreditDebitIndicator;
    }

    public ProprietaryBankTransactionCode getProprietaryBankTransactionCode() {
        return mProprietaryBankTransactionCode;
    }

    public String getStatus() {
        return mStatus;
    }

    public String getTransactionId() {
        return mTransactionId;
    }

    public String getTransactionInformation() {
        return mTransactionInformation;
    }

    public String getTransactionReference() {
        return mTransactionReference;
    }

    public String getValueDateTime() {
        return mValueDateTime;
    }

    public static class Builder {

        private String mAccountId;
        private Amount mAmount;
        private Balance mBalance;
        private BankTransactionCode mBankTransactionCode;
        private String mBookingDateTime;
        private String mCreditDebitIndicator;
        private ProprietaryBankTransactionCode mProprietaryBankTransactionCode;
        private String mStatus;
        private String mTransactionId;
        private String mTransactionInformation;
        private String mTransactionReference;
        private String mValueDateTime;

        public Transaction.Builder withAccountId(String accountId) {
            mAccountId = accountId;
            return this;
        }

        public Transaction.Builder withAmount(Amount amount) {
            mAmount = amount;
            return this;
        }

        public Transaction.Builder withBalance(Balance balance) {
            mBalance = balance;
            return this;
        }

        public Transaction.Builder withBankTransactionCode(BankTransactionCode bankTransactionCode) {
            mBankTransactionCode = bankTransactionCode;
            return this;
        }

        public Transaction.Builder withBookingDateTime(String bookingDateTime) {
            mBookingDateTime = bookingDateTime;
            return this;
        }

        public Transaction.Builder withCreditDebitIndicator(String creditDebitIndicator) {
            mCreditDebitIndicator = creditDebitIndicator;
            return this;
        }

        public Transaction.Builder withProprietaryBankTransactionCode(ProprietaryBankTransactionCode proprietaryBankTransactionCode) {
            mProprietaryBankTransactionCode = proprietaryBankTransactionCode;
            return this;
        }

        public Transaction.Builder withStatus(String status) {
            mStatus = status;
            return this;
        }

        public Transaction.Builder withTransactionId(String transactionId) {
            mTransactionId = transactionId;
            return this;
        }

        public Transaction.Builder withTransactionInformation(String transactionInformation) {
            mTransactionInformation = transactionInformation;
            return this;
        }

        public Transaction.Builder withTransactionReference(String transactionReference) {
            mTransactionReference = transactionReference;
            return this;
        }

        public Transaction.Builder withValueDateTime(String valueDateTime) {
            mValueDateTime = valueDateTime;
            return this;
        }

        public Transaction build() {
            Transaction transaction = new Transaction();
            transaction.mAccountId = mAccountId;
            transaction.mAmount = mAmount;
            transaction.mBalance = mBalance;
            transaction.mBankTransactionCode = mBankTransactionCode;
            transaction.mBookingDateTime = mBookingDateTime;
            transaction.mCreditDebitIndicator = mCreditDebitIndicator;
            transaction.mProprietaryBankTransactionCode = mProprietaryBankTransactionCode;
            transaction.mStatus = mStatus;
            transaction.mTransactionId = mTransactionId;
            transaction.mTransactionInformation = mTransactionInformation;
            transaction.mTransactionReference = mTransactionReference;
            transaction.mValueDateTime = mValueDateTime;
            return transaction;
        }

    }

}
