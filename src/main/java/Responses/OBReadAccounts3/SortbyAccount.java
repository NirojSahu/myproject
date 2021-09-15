package Responses.OBReadAccounts3;

import java.util.Comparator;

public class SortbyAccount implements Comparator<Account>
{
    @Override
    public int compare(Account a, Account b)
    {
        return a.getAccountId().compareTo(b.getAccountId());
    }
}
