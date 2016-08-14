package finalproject.entity.services;

/**
 * Standart product in services and extends it {@link Service}
 */

public class SimpleTelefony extends Service{

    /**
     * standart id in characteristic 2
     */

    private final int id = 2;

    /**
     * Standart price simple telefony{@link SimpleTelefony}
     */

    private double price = 15;

    /**
     * Overrade method price
     *
     * @return price equal 15
     */

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getCharacteristics() {
        return "";
    }

}
