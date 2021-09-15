package com.test.pages;

public class DDTableDataSet {
        public String originatorIdentificationNumber;
        public String payeeName;
        public String lastPayment;
        public String lastAmount;
        public String status;


        public DDTableDataSet(){}
        public DDTableDataSet(String originatorIdentificationNumber, String payeeName, String lastPayment, String lastAmount, String status){
            this.originatorIdentificationNumber =originatorIdentificationNumber;
            this.payeeName=payeeName;
            this.lastPayment=lastPayment;
            this.lastAmount=lastAmount;
            this.status=status;
        }

    public String getOriginatorIdentificationNumber() {
        return originatorIdentificationNumber;
    }

    public void setOriginatorIdentificationNumber(String originatorIdentificationNumber) {
        this.originatorIdentificationNumber = originatorIdentificationNumber;
    }

    public String getPayeeName() {
        return payeeName;
    }

    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName;
    }

    public String getLastPayment() {
        return lastPayment;
    }

    public void setLastPayment(String lastPayment) {
        this.lastPayment = lastPayment;
    }

    public String getLastAmount() {
        return lastAmount;
    }

    public void setLastAmount(String lastAmount) {
        this.lastAmount = lastAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "DDTableDataSet{" +
                "originatorIdentificationNumberWE='" + originatorIdentificationNumber + '\'' +
                ", payeeName='" + payeeName + '\'' +
                ", lastPayment='" + lastPayment + '\'' +
                ", lastAmount='" + lastAmount + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
