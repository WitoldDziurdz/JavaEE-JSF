package pl.gda.pg.eti.kask.javaee.jsf;

import pl.gda.pg.eti.kask.javaee.jsf.entities.Courier;
import pl.gda.pg.eti.kask.javaee.jsf.entities.Pack;
import pl.gda.pg.eti.kask.javaee.jsf.entities.TypeSize;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.*;

@ManagedBean
@ApplicationScoped
public class CourierService implements Serializable {
    private final SortedMap<Integer, Courier> couriers;
    private final SortedMap<Integer, Pack> packs;

    public  CourierService(){
        Pack p1 = new Pack(1,
                "Gdansk wyspianskiego 9",
                TypeSize.LARGE,
                10.22,
                false );
        Pack p2 = new Pack(2,
                "Gdansk wyspianskiego 12",
                TypeSize.SMALL,
                5,
                true );
        Pack p3 = new Pack(3,
                "Gdansk wyspianskiego 22",
                TypeSize.MEDIUM,
                5,
                true );
        Courier c1 = new Courier(1,"Hubert", "Polak","570434267",22,asList(p1,p2));
        Courier c2 = new Courier(2,"Piotr", "Majewski","570434211",44,asList(p3));
        couriers = new TreeMap<>();
        packs = new TreeMap<>();
        packs.put(p1.getId(), p1);
        packs.put(p2.getId(), p2);
        packs.put(p3.getId(), p3);
        couriers.put(c1.getId(), c1);
        couriers.put(c2.getId(), c2);
    }

    private List<Pack> asList(Pack... packs){
        List<Pack> list = new ArrayList<>();
        list.addAll(Arrays.asList(packs));
        return list;
    }

    public List<Pack> findAllPacks(){
        return new ArrayList<Pack>(packs.values());
    }

    public List<Courier> findAllCouriers(){
        return new ArrayList<Courier>(couriers.values());
    }

    public Pack findPack(int id){
        return packs.get(id);
    }

    public Courier findCourier(int id){
        return couriers.get(id);
    }

    public void removePack(Pack pack){
        packs.remove(pack.getId());
    }

    public void removeCourier(Courier courier){
        couriers.remove(courier.getId());
    }

    public void savePack(Pack pack){
        if(pack.getId() == 0){
            pack.setId(packs.lastKey() + 1);
        }
        packs.put(pack.getId(),pack);
    }

    public void saveCourier(Courier courier){
        if(courier.getId() == 0){
            courier.setId(couriers.lastKey() + 1);
        }
        couriers.put(courier.getId(),courier);
    }

}
