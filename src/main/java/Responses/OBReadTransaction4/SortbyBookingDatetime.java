package Responses.OBReadTransaction4;

import Responses.OBReadAccounts3.Account;

import java.util.Comparator;

public class SortbyBookingDatetime implements Comparator<Transaction> {

    @Override
    public int compare(Transaction a, Transaction b)
    {
        return a.getBookingDateTime().compareTo(b.getBookingDateTime());
    }
}
