package core.interfaces;

import estorePojo.exceptions.InsufficientBalanceException;
import estorePojo.exceptions.UnknownAccountException;

public interface IBank {
    void transfert(String from, String to, double amount) throws InsufficientBalanceException, UnknownAccountException;
}
