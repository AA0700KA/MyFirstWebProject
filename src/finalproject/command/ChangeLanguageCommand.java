package finalproject.command;

import com.sun.deploy.services.Service;
import finalproject.entity.Payment;
import finalproject.entity.users.Abonent;
import finalproject.entity.users.User;
import finalproject.wrappers.IRequestWrapper;

import java.util.List;
import java.util.Map;

/**
 * ChangeLanguageCommand is controller command which abonent or admin can change Language
 * Russian or English
 * its overall command
 *
 * @author Andrew
 * @version 1.1  23/07/2016
 */

public class ChangeLanguageCommand implements Command {

    /**
     * Describe type of command in ComandFactory and send action value
     * in request get or post
     *{@value #COMMAND}
     */

    public static final String COMMAND = "change_language";

    @Override
    public String execute(IRequestWrapper wrapper, boolean isTest) {
        String language = wrapper.getParameter("language");
        String sessionLanguage = (String)wrapper.getSession().getAttribute("language");
        User user = (User) wrapper.getSession().getAttribute("user");
        List<User> users = (List<User>) wrapper.getSession().getAttribute("all users");
        List<Payment> payments = (List<Payment>) wrapper.getSession().getAttribute("all payments");

        if (sessionLanguage != null) {

            boolean toEng = sessionLanguage.equals("ru-RU") && language.equals("en-EN");
            boolean toRus = sessionLanguage.equals("en-EN") && language.equals("ru-RU");

            if (user != null &&  user instanceof Abonent) {
                if (toEng) {
                    updateUserToEnglish(user);
                } else if (toRus) {
                    updateUserToRussian(user);
                }
            }

            if (users != null) {
                if (toEng) {
                    updateUsersToEnglish(users);
                } else if (toRus) {
                    updateUsersToRussian(users);
                }
            }

            if (payments != null) {
                if (toEng) {
                    updatePaymentsToEnglish(payments);
                } else if (toRus) {
                    upadatePaymentsToRussian(payments);
                }
            }
        } else if (language.equals("ru-RU")) {

            if (user != null && user instanceof Abonent) {
                updateUserToRussian(user);
            }

            if (users != null) {
                updateUsersToRussian(users);
            }

            if (payments != null) {
                upadatePaymentsToRussian(payments);
            }
        }

        String path = wrapper.getParameter("this_path");
        wrapper.getSession().setAttribute("language", language);
        return path;
    }

    private void upadatePaymentsToRussian(List<Payment> payments) {
        for (Payment payment : payments) {
            int price = payment.getPrice();
            payment.setPrice(price*15);
        }
    }

    private void updatePaymentsToEnglish(List<Payment> payments) {
        for (Payment payment : payments) {
            int price = payment.getPrice();
            payment.setPrice(price/15);
        }
    }

    private void updateUserToRussian(User user) {
        int balance = ((Abonent) user).getBalance();
        ((Abonent) user).updateBalance(balance * 15);
    }

    private void updateUserToEnglish(User user) {
        int balance = ((Abonent) user).getBalance();
        ((Abonent) user).updateBalance(balance / 15);
    }

    private void updateUsersToRussian(List<User> users) {
        for (User user : users) {
            updateUserToRussian(user);
        }
    }

    private void updateUsersToEnglish(List<User> users) {
        for (User user : users) {
            updateUserToEnglish(user);
        }
    }

}
