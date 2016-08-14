package finalproject.entity.services;


import java.util.ResourceBundle;

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
        ResourceBundle bundle = ResourceBundle.getBundle("finalproject/properties/text");
        String countChannelsTxt = bundle.getString("main.count.channels");
        return countChannelsTxt + " " + countChanels;
    }

}
