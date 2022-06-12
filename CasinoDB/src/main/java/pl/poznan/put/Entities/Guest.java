package pl.poznan.put.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;


@Entity
@Table(name="goscie")
public class Guest {
    @Id
    @Column(name="id_gosc")
    private Long id;

    @Column(name="nazwisko")
    private String name;

    @Column(name="rezerwacja")
    private Date reserved;

    @Column(name="wygrane")
    private Double winnings;

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

    public Date getReserved() {
        return reserved;
    }

    public void setReserved(Date reserved) {
        this.reserved = reserved;
    }

    public Double getWinnings() {
        return winnings;
    }

    public void setWinnings(Double winnings) {
        this.winnings = winnings;
    }

    public Long getId_cas() {
        return id_cas;
    }

    public void setId_cas(Long id_cas) {
        this.id_cas = id_cas;
    }
}
