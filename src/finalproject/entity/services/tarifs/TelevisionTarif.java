package finalproject.entity.services.tarifs;

import finalproject.entity.services.Service;
import finalproject.entity.services.Television;
import finalproject.exceptions.NotCurrentServiceException;

/**
 * Television Tarif impliments tarif {@link Tarif}
 *
 * @author Andrew
 * @version 1.0
 *
 */

public class TelevisionTarif implements Tarif {

    @Override
    public int getPrice(Service service) {
        if (service instanceof Television) {
            int countChanels = ((Television)service).getCountChanels();

            if (countChanels < 15) {
                return countChanels*3;
            }

            return countChanels*2;
        }

        throw new NotCurrentServiceException(service.getName() + " isn't ip-television");
    }

}
