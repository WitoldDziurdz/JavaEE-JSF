package pl.gda.pg.eti.kask.javaee.jsf.entities;


import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@Getter
@Setter
public class Courier implements Serializable {
    private int id;
    private String name;
    private String surname;
    private String phone;
    private int age;
    List<Pack> packs = new ArrayList<>();

    @Override
    public String toString(){
        return  this.name + " " + this.surname;
    }
}
