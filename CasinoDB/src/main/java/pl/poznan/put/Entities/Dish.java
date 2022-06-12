package pl.poznan.put.Entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="dania")
@IdClass(DishId.class)
public class Dish implements Serializable {
    @Id
    @Column(name="nazwa")
    private String name;

    @Column(name="popularnosc")
    private Integer popularity;

    @Id
    @Column(name="id_rest")
    private Long id_rest;

    @Id
    @Column(name="id_kas")
    private Long id_cas;

    public Dish(){};

    public Dish(Long cas, Long rest){
        id_cas = cas;
        id_rest = rest;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPopularity() {
        return popularity;
    }

    public void setPopularity(Integer popularity) {
        this.popularity = popularity;
    }

    public Long getId_rest() {
        return id_rest;
    }

    public void setId_rest(Long id_rest) {
        this.id_rest = id_rest;
    }

    public Long getId_cas() {
        return id_cas;
    }

    public void setId_cas(Long id_cas) {
        this.id_cas = id_cas;
    }
}
