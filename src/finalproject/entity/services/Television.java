package finalproject.entity.services;


/**
 * Television class extends {@link Service}
 *
 * @author Andrew
 * @version 1.0
 */

public class Television extends Service {

    /**
     * count channels column in television_characteristic
     */

    private int countChanels;

    public int getCountChanels() {
        return countChanels;
    }

    public void setCountChanels(int countChanels) {
        this.countChanels = countChanels;
    }

    @Override
    public String getCharacteristics() {
        return "Count chanels: " + countChanels;
    }

}
