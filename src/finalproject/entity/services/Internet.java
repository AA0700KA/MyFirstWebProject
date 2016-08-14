package finalproject.entity.services;

import finalproject.entity.services.tarifs.InternetTarif;

import java.util.ResourceBundle;

/**
 * Entity internet, extends service{@link Service}
 *
 * @author Andrew
 * @version 1.0 23/07/2016
 */
public class Internet extends Service {

    /**
     * Speed column in table internet_characteristic
     *
     */

    private int speed;

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        if (speed > 100) {
            speed = 100;
        }
        this.speed = speed;
    }

    @Override
    public String getCharacteristics() {
        ResourceBundle bundle = ResourceBundle.getBundle("finalproject/properties/text");
        String speedTxt = bundle.getString("characteristic.internet.speed");
        String metricTxt = bundle.getString("characteristic.internet.metric");
        return speedTxt + " " + speed + " " +  metricTxt;
    }

}
