package finalproject.entity.users;

import finalproject.entity.users.Abonent;
import finalproject.entity.users.Administrator;
import finalproject.entity.users.User;

/**
 * User factory get user from data base into characteristic
 */
public class UserFactory {


    /**
     * Create user object
     *
     * @param isAdmin
     *               flag admin user or not
     * @param balance
     *               user balance of money
     * @param isBlocked
     *               user blocked or not
     *
     * @return user object
     */

    public static User getUser(boolean isAdmin, double balance, boolean isBlocked) {
        if (isAdmin) {
            return new Administrator();
        }
        Abonent abonent = new Abonent();
        abonent.setBalance(balance);
        abonent.setBlocked(isBlocked);
        return abonent;
    }

}
