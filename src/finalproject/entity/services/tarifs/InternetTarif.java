package finalproject.entity.services.tarifs;

import finalproject.entity.services.Internet;
import finalproject.entity.services.Service;
import finalproject.exceptions.NotCurrentServiceException;

/**
 * InternetTarif impliments tarif {@link Tarif}
 *
 * @author Andrew
 * @version 1.0
 *
 */
public class InternetTarif implements Tarif {

    @Override
    public double getPrice(Service service) {
        if (service instanceof Internet) {
            return ((Internet)service).getSpeed()*5;
        }

        throw new NotCurrentServiceException(service.getName() + " isn't internet");
    }

}
