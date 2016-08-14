package finalproject.entity.users;


import finalproject.entity.services.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Abonent extends user {@link User}
 *
 * @author Andrew
 * @version 1.0
 *
 */

public class Abonent extends User {

    /**
     * Abonent blance column balance or  balance_rus dependents locale
     *
     */

    private double balance;

    /**
     * Abonent ststus blocked or unblocked column in users
     *
     */

    private boolean blocked;


    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public double getBalance() {
        return balance;
    }

    public void updateBalance(double balance) {
        this.balance = balance;
    }

    public void setBalance(double balance) {
        this.balance += balance;
    }

}
