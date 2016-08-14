package finalproject.entity.services;


import finalproject.entity.services.tarifs.IPTelefonyTarif;

import java.util.ResourceBundle;

/**
 * IPTelefony extends service {@link Service}
 *
 * @author Andrew
 * @version 1.0 23/07/2016
 */

public class IPTelefony extends Service {

    /**
     * Flag of videocall aviability column ip_telefony_characteristic
    */

    private boolean videoCall;

    /**
     * Count minutes in rouming coulumn in ip_telefony_characteristic
     */

    private int roumingMinutes;

    public boolean isVideoCall() {
        return videoCall;
    }

    public void setVideoCall(boolean videoCall) {
        this.videoCall = videoCall;
    }

    public int getRoumingMinutes() {
        return roumingMinutes;
    }

    public void setRoumingMinutes(int roumingMinutes) {
        this.roumingMinutes = roumingMinutes;
    }

    @Override
    public String getCharacteristics() {
        String videocallStatus = (videoCall) ? "+" : "-";
        ResourceBundle bundle = ResourceBundle.getBundle("finalproject/properties/text");
        String minutesTxt = bundle.getString("main.minutes.in.rouming");
        String videocallTxt = bundle.getString("main.videocall");
        return minutesTxt + " " + roumingMinutes + "\n" + videocallTxt + " " + videocallStatus;
    }

}
