package pl.poznan.put.Entities;

import java.io.Serializable;
import java.util.Objects;

public class DishId implements Serializable {
    private String name;
    private Long id_rest;
    private Long id_cas;

    public DishId(){};

    public DishId(String name, Long id_rest, Long id_cas){
        this.name = name;
        this.id_rest = id_rest;
        this.id_cas = id_cas;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DishId dishId = (DishId) o;
        return name.equals(dishId.name) &&
                id_rest.equals(dishId.id_rest) &&
                id_cas.equals(dishId.id_cas);
    }

    @Override
    public int hashCode(){
        return Objects.hash(name, id_rest, id_cas);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
