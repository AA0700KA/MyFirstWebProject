package finalproject.entity.services.tarifs;

import finalproject.entity.services.Service;

/**
 * Tarif main inteface of change price service
 * pattern visitor
 *
 * @author Andrew
 * @version 1.0
 */

public interface Tarif {

    /**
     *
     * @param service
     *               set logic to price this service
     * @return price of service
     */

    int getPrice(Service service);

}
