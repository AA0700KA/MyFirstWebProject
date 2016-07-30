package finalproject.entity;

import finalproject.entity.services.Service;
import finalproject.entity.users.User;

/**
 * Payments of user
 *
 * @author Andrew
 * @version 1.0
 */
public class Payment {

    /**
     * id primary key autoincrement in payments
     */

    private int id;

    /**
     * Service coulumn in payment
     */

    private Service service;

    /**
     * User column in payment check
     */

    private User user;

    /**
     * price by tarif column price
     */

    private int price;

    /**
     * Status payment paid or not
     */

    private boolean paid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

}
