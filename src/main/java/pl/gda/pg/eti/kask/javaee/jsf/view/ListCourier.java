package pl.gda.pg.eti.kask.javaee.jsf.view;


import pl.gda.pg.eti.kask.javaee.jsf.CourierService;
import pl.gda.pg.eti.kask.javaee.jsf.entities.Courier;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;

@ViewScoped
@ManagedBean
public class ListCourier implements Serializable {

    @ManagedProperty("#{courierService}")
    private CourierService courierService;

    public void setCourierService(CourierService courierService){
        this.courierService = courierService;
    }

    private List<Courier> couriers;

    public List<Courier> getCouriers(){
        if(couriers == null){
            couriers = courierService.findAllCouriers();
        }
        return couriers;
    }

    public void removeCourier(Courier courier){
        courierService.removeCourier(courier);
        couriers.remove(courier);
    }

}
