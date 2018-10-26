package pl.gda.pg.eti.kask.javaee.jsf.entities;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@Getter
@Setter
public class Pack implements Serializable {
    private int id;
    private String address;
    private TypeSize typeSize;
    private double price;
    private boolean express;

    @Override
    public String toString(){
        return  this.id + " " + this.address;
    }
}
