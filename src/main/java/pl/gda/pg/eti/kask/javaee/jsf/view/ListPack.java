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
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;

@ViewScoped
@ManagedBean
@Log
public class ListPack implements Serializable {

    @Getter
    @Setter
    private int courierId;

    @Getter
    @Setter
    private Courier courier;

    private List<Pack> packs;


    @ManagedProperty("#{courierService}")
    private CourierService courierService;

    public void setCourierService(CourierService courierService) {
        this.courierService = courierService;
    }

    public void init() {
        if (courier == null && courierId != 0) {
            courier = courierService.findCourier(courierId);
        }
        if (courier == null && courierId != 0) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("error/404.xhtml");
            } catch (IOException ex) {
                log.log(Level.SEVERE, null, ex);
            }
        }
    }

    public List<Pack> getPacks() {
        if (packs == null) {
            if (courier != null) {
                packs = courier.getPacks();
            } else {
                packs = courierService.findAllPacks();
            }
        }
        return packs;
    }

    public void removePack(Pack pack) {
        courierService.removePack(pack);
        packs.remove(pack);
    }

    public String getTitle() {
        if (courier != null) {
            return courier.toString();
        } else {
            return "Paczki";
        }
    }
}
