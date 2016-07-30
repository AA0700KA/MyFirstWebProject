package finalproject.entity.services;


import finalproject.entity.services.tarifs.IPTelefonyTarif;
import finalproject.entity.services.tarifs.InternetTarif;
import finalproject.entity.services.tarifs.TelevisionTarif;

public class ServiceFactory {

    public static Service getService(int id) {
        Service service = null;
        switch (id) {
            case 1 : service = new Internet();
                service.setTarif(new InternetTarif());
                break;
            case 3 : service = new IPTelefony();
                service.setTarif(new IPTelefonyTarif());
                break;
            case 4 : service = new Television();
                service.setTarif(new TelevisionTarif());
                break;
            case 2 : service = new SimpleTelefony();
        }

        if (service != null) {
            service.setId(id);
        }

        return service;
    }

}
