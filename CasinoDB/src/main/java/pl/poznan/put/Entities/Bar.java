package pl.poznan.put.Entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="bary")
public class Bar implements Serializable {
    @Id
    @Column(name="id_bar")
    private Long id_bar;

    @Column(name="nazwa")
    private String name;

    @Column(name="id_kas")
    private Long id_cas;

    public Long getId_bar() {
        return id_bar;
    }

    public void setId_bar(Long id_bar) {
        this.id_bar = id_bar;
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
}
