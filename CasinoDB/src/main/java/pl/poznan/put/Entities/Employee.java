package pl.poznan.put.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name="pracownicy")
public class Employee {
    @Id
    @Column(name="id_prac")
    private Long id;

    @Column(name="nazwisko")
    private String name;

    @Column(name="placa")
    private Double salary;

    @Column(name="funkcja")
    private String function;

    @Column(name="zatrudniony")
    private Date hired;

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

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public Date getHired() {
        return hired;
    }

    public void setHired(Date hired) {
        this.hired = hired;
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
