package pl.gda.pg.eti.kask.javaee.jsf.view;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import pl.gda.pg.eti.kask.javaee.jsf.CourierService;
import pl.gda.pg.eti.kask.javaee.jsf.entities.Courier;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;

@ViewScoped
@ManagedBean
@Log
public class ViewCourier implements Serializable {

    @ManagedProperty("#{courierService}")
    private CourierService courierService;

    @Getter
    @Setter
    private int courierId;

    @Getter
    @Setter
    private Courier courier;

    public void setCourierService(CourierService courierService){
        this.courierService = courierService;
    }

    public void init(){
        if(courier == null){
            courier = courierService.findCourier(courierId);
        }
        if(courier == null){
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("error/404.xhtml");
            } catch (IOException ex) {
                log.log(Level.SEVERE, null, ex);
            }
        }
    }
}
