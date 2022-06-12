package pl.poznan.put.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

@Entity
@Table(name="dluznicy")
public class Debtor {
    @Id
    @Column(name="id_gosc")
    private Long id;

    @Column(name="dlug")
    private Double debt;

    @Column(name="rata")
    private Double part;

    @Column(name="nast_rata")
    private Date next;

    @Column(name="id_wspol")
    private Long id_part;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getDebt() {
        return debt;
    }

    public void setDebt(Double debt) {
        this.debt = debt;
    }

    public Double getPart() {
        return part;
    }

    public void setPart(Double part) {
        this.part = part;
    }

    public Date getNext() {
        return next;
    }

    public void setNext(Date next) {
        this.next = next;
    }

    public Long getId_part() {
        return id_part;
    }

    public void setId_part(Long id_part) {
        this.id_part = id_part;
    }
}
