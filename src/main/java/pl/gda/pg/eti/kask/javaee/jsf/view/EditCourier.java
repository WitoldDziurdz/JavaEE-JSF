package pl.gda.pg.eti.kask.javaee.jsf.view;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import pl.gda.pg.eti.kask.javaee.jsf.CourierService;
import pl.gda.pg.eti.kask.javaee.jsf.entities.Courier;
import pl.gda.pg.eti.kask.javaee.jsf.entities.Pack;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

@ViewScoped
@ManagedBean
@Log
public class EditCourier implements Serializable {
    @ManagedProperty("#{courierService}")
    private CourierService courierService;

    @Getter
    @Setter
    private int courierId;

    @Getter
    @Setter
    private Courier courier;

    private List<SelectItem> packsAsSelectItems;

    public void setCourierService(CourierService courierService) {
        this.courierService = courierService;
    }


    public List<SelectItem> getPacksAsSelectItems() {
        if (packsAsSelectItems == null) {
            packsAsSelectItems = new ArrayList<>();
            for (Pack pack : courierService.findAllPacks()) {
                packsAsSelectItems.add(new SelectItem(pack, pack.getId() + " " + pack.getAddress()));
            }
        }
        return packsAsSelectItems;
    }

    public void init(){
        if(courier == null && courierId != 0){
            courier = courierService.findCourier(courierId);
        }else if(courier == null && courierId == 0){
            courier = new Courier();
        }
       if(courier == null){
           try {
               FacesContext.getCurrentInstance().getExternalContext().redirect("error/404.xhtml");
           } catch (IOException ex) {
               log.log(Level.SEVERE, null, ex);
           }
       }
    }

    public String saveCourier(){
        courierService.saveCourier(courier);
        return  "list_courier?faces-redirect=true";
    }
}
