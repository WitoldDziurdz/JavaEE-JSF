package pl.gda.pg.eti.kask.javaee.jsf.view;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import pl.gda.pg.eti.kask.javaee.jsf.CourierService;
import pl.gda.pg.eti.kask.javaee.jsf.entities.Pack;
import pl.gda.pg.eti.kask.javaee.jsf.entities.TypeSize;

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
public class EditPack implements Serializable {
    @ManagedProperty("#{courierService}")
    private CourierService courierService;

    @Getter
    @Setter
    private int packId;

    @Getter
    @Setter
    private Pack pack;

    private List<SelectItem> typeSizeAsSelectItems;
    private List<SelectItem> expressAsSelectItems;


    public void setCourierService(CourierService courierService) {
        this.courierService = courierService;
    }

    public List<SelectItem> getTypeSizeAsSelectItems() {
        if (typeSizeAsSelectItems == null) {
            typeSizeAsSelectItems = new ArrayList<>();
            for (TypeSize typeSize : TypeSize.values()) {
                typeSizeAsSelectItems.add(new SelectItem(typeSize, typeSize.toString()));
            }
        }
        return typeSizeAsSelectItems;
    }

    public List<SelectItem> getExpressAsSelectItems() {

        if (expressAsSelectItems == null) {
            expressAsSelectItems.add(new SelectItem(Boolean.TRUE, Boolean.TRUE.toString()));
            expressAsSelectItems.add(new SelectItem(Boolean.FALSE, Boolean.FALSE.toString()));
        }
        return expressAsSelectItems;
    }

    public void init() {
        if (pack == null && packId != 0) {
            pack = courierService.findPack(packId);
        } else if (pack == null && packId == 0) {
            pack = new Pack();
        }
        if (pack == null) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("error/404.xhtml");
            } catch (IOException ex) {
                log.log(Level.SEVERE, null, ex);
            }
        }
    }

    public String savePack(){
        courierService.savePack(pack);
        return  "list_pack?faces-redirect=true";
    }
}
