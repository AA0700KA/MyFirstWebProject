package finalproject.entity.services;

import finalproject.entity.services.tarifs.Tarif;

/**
 * Abstract class services table services
 *
 * @author Andrew
 * @version 1.0
 */
public abstract class Service {

    /**
     * Column id primary key autoincrement
     */

    private int id;

    /**
     * Column service or service_rus dependents locale
     */

    private String name;

    /**
     * tarif of prices
    */

    private Tarif tarif;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Tarif getTarif() {
        return tarif;
    }

    public void setTarif(Tarif tarif) {
        this.tarif = tarif;
    }

    /**
     * Method of price
     *
     * @return price of this service
    */

    public int getPrice() {
        return tarif.getPrice(this);
    }

    /**
     * Characteristics of this service
     *
     * @return string value dependings of encoding
    */

    public abstract String getCharacteristics();

}
