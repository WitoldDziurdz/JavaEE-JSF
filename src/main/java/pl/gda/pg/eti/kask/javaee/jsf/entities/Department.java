package pl.gda.pg.eti.kask.javaee.jsf.entities;

import lombok.*;

import java.io.Serializable;

@ToString(of = {"id", "address"})
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@Getter
@Setter
public class Department implements Serializable {
    private int id;
    private int numberOfWorkers;
    private String address;
    private boolean isStorage;
}
