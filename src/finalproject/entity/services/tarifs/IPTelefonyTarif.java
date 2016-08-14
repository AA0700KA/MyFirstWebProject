package finalproject.entity.services.tarifs;

import finalproject.entity.services.IPTelefony;
import finalproject.entity.services.Service;
import finalproject.exceptions.NotCurrentServiceException;

/**
 * IPTelefonyTarif impliments tarif {@link Tarif}
 *
 * @author Andrew
 * @version 1.0
 *
 */

public class IPTelefonyTarif implements Tarif {

    @Override
    public double getPrice(Service service) {
        if (service instanceof IPTelefony) {
            boolean videoCall = ((IPTelefony) service).isVideoCall();
            int roumingMinutes = ((IPTelefony) service).getRoumingMinutes();
            int videoCallCount = 0;

            if (videoCall) {
                videoCallCount = 30;
            }

            return 15 + roumingMinutes + videoCallCount;
        }

        throw new NotCurrentServiceException(service.getName() + " isn't ip-telefony");
    }

}
