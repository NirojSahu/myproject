package com.test.pages;

public class TableDataSet {
        public String transactionDate;
        public String valueDate;
        public String transactionDetails;
        public String debits;
        public String credit;
        public String balance;

        public TableDataSet(){}
        public TableDataSet(String transactionDate,String valueDate,String transactionDetails, String debits, String credit,
                            String balance){
            this.transactionDate =transactionDate;
            this.valueDate=valueDate;
            this.transactionDetails=transactionDetails;
            this.debits=debits;
            this.credit=credit;
            this.balance=balance;
        }

        public String getTransactionDate() {
            return transactionDate;
        }

        public void setTransactionDate(String transactionDate) {
            this.transactionDate = transactionDate;
        }

        public String getValueDate() {
            return valueDate;
        }

        public void setValueDate(String valueDate) {
            this.valueDate = valueDate;
        }

        public String getTransactionDetails() {
            return transactionDetails;
        }

        public void setTransactionDetails(String transactionDetails) {
            this.transactionDetails = transactionDetails;
        }

        public String getDebits() {
            return debits;
        }

        public void setDebits(String debits) {
            this.debits = debits;
        }

        public String getCredit() {
            return credit;
        }

        public void setCredit(String credit) {
            this.credit = credit;
        }

        public String getBalance() {
            return balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        @Override
        public String toString() {
            return "TableDataSet{" +
                    "transactionDate='" + transactionDate + '\'' +
                    ", valueDate='" + valueDate + '\'' +
                    ", transactionDetails='" + transactionDetails + '\'' +
                    ", debits='" + debits + '\'' +
                    ", credit='" + credit + '\'' +
                    ", balance='" + balance + '\'' +
                    '}';
        }

}
