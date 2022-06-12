package pl.poznan.put.Entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="Zet_Kasyno")
@IdClass(Chip_Cas.class)
public class Chip_Cas implements Serializable {
    @Id
    @Column(name="id_zet")
    private Long id_chip;

    @Id
    @Column(name="id_kas")
    private Long id_cas;

    public Long getId_chip() {
        return id_chip;
    }

    public void setId_chip(Long id_chip) {
        this.id_chip = id_chip;
    }

    public Long getId_cas() {
        return id_cas;
    }

    public void setId_cas(Long id_cas) {
        this.id_cas = id_cas;
    }
}
