package finalproject.command;

import finalproject.entity.Payment;
import finalproject.entity.services.Service;
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
        Map<Integer, Service> services = (Map<Integer, Service>) wrapper.getSession().getAttribute("services");

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

            if (services != null) {
                if (toEng) {
                    upateServicesToEnglish(services);
                } else if (toRus) {
                    updateServicesToRussian(services);
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

            if (services != null) {
                updateServicesToRussian(services);
            }

        }

        String path = wrapper.getParameter("this_path");
        wrapper.getSession().setAttribute("language", language);
        return path;
    }

    /**
     * If admin himself account and located in payments page
     * from english to russian language
     */

    private void upadatePaymentsToRussian(List<Payment> payments) {
        for (Payment payment : payments) {
            double price = payment.getPrice();
            int round = (int) Math.round((price * 15) * 100.0);
            payment.setPrice(round/100.0);
            Service service = payment.getService();
            updateServiceToRussian(service);
        }
    }

    /**
     * If admin himself account and located in payments page
     * from russian to english language
     */

    private void updatePaymentsToEnglish(List<Payment> payments) {
        for (Payment payment : payments) {
            double price = payment.getPrice();
            int round = (int) Math.round((price / 15) * 100.0);
            payment.setPrice(round/100.0);
            Service service = payment.getService();
            updateServiceToEnglish(service);
        }
    }

    /**
     * Update current service from russian to english
     */

    private void updateServiceToEnglish(Service service) {
        switch (service.getId()) {
            case 1 : service.setName("internet");
                break;
            case 2 : service.setName("telefony");
                break;
            case 3 : service.setName("ip-telefony");
                break;
            case 4 : service.setName("television");
        }
    }

    /**
     * Update current service from english to russian
     */

    private void updateServiceToRussian(Service service) {
        switch (service.getId()) {
            case 1 : service.setName("Интернет");
                break;
            case 2 : service.setName("Телефония");
                break;
            case 3 : service.setName("IP-Телефония");
                break;
            case 4 : service.setName("Телевидения");
        }
    }

    /**
     * Update user balance to russian value
     */

    private void updateUserToRussian(User user) {
        double balance = ((Abonent) user).getBalance();
        int round = (int) Math.round((balance*15)*100.0);
        ((Abonent) user).updateBalance(round/100.0);
    }

    /**
     * Update user balance to English valute
     */

    private void updateUserToEnglish(User user) {
        double balance = ((Abonent) user).getBalance();
        int round = (int) Math.round((balance/15)*100.0);
        ((Abonent) user).updateBalance(round/100.0);
    }

    /**
     * If admin himself account and located in all users page
     * update all users balance
     * from english to russian language
     */

    private void updateUsersToRussian(List<User> users) {
        for (User user : users) {
            updateUserToRussian(user);
        }
    }

    /**
     * If admin himself account and located in all users page
     * update all users balance
     * from russian to english language
     */

    private void updateUsersToEnglish(List<User> users) {
        for (User user : users) {
            updateUserToEnglish(user);
        }
    }

    /**
     * Update all users service to russian
     */

    private void updateServicesToRussian(Map<Integer, Service> services) {
        for (Integer id : services.keySet()) {
            Service service = services.get(id);
            updateServiceToRussian(service);
        }
    }

    /**
     * Update all users service to english
     */

    private void upateServicesToEnglish(Map<Integer, Service> services) {
        for (Integer id : services.keySet()) {
            Service service = services.get(id);
            updateServiceToEnglish(service);
        }
    }

}
