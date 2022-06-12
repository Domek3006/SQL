package pl.poznan.put.Entities;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="restauracje")
public class Restaurant implements Serializable {
    @Id
    @Column(name="id_rest")
    private Long id;

    @Column(name="nazwa")
    private String name;

    @Column(name="przychody")
    private Double income;

    @Column(name="id_kas")
    private Long id_cas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public Long getId_cas() {
        return id_cas;
    }

    public void setId_cas(Long id_cas) {
        this.id_cas = id_cas;
    }
}

