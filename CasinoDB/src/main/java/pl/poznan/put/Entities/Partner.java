package pl.poznan.put.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name="wspolpracownicy")
public class Partner {
    @Id
    @Column(name="id_wspol")
    private Long id;

    @Column(name="nazwa")
    private String name;

    @Column(name="usluga")
    private String service;

    @Column(name="koszt")
    private Double cost;

    @Column(name="koniec_umowy")
    private Date ends;

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

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Date getEnds() {
        return ends;
    }

    public void setEnds(Date ends) {
        this.ends = ends;
    }

    public Long getId_cas() {
        return id_cas;
    }

    public void setId_cas(Long id_cas) {
        this.id_cas = id_cas;
    }
}
