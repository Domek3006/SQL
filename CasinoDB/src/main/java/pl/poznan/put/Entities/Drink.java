package pl.poznan.put.Entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="drinki")
@IdClass(Drink.class)
public class Drink implements Serializable {
    @Id
    @Column(name="nazwa")
    private String name;

    @Id
    @Column(name="id_kas")
    private Long id_cas;

    @Id
    @Column(name="id_bar")
    private Long id_bar;

    public Drink(){};

    public Drink(String name, long bar, long cas){
        this.name = name;
        this.id_bar = bar;
        this.id_cas = cas;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId_cas() {
        return id_cas;
    }

    public void setId_cas(Long id_cas) {
        this.id_cas = id_cas;
    }

    public Long getId_bar() {
        return id_bar;
    }

    public void setId_bar(Long id_bar) {
        this.id_bar = id_bar;
    }
}
