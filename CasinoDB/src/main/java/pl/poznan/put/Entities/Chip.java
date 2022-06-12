package pl.poznan.put.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="zetony")
public class Chip {

    @Id
    @Column(name="id_zet")
    private Long id;

    @Column(name="wartosc")
    private Integer value;

    @Column(name="kolor")
    private String color;

    @Column(name="wielkosc")
    private String size;

    @Column(name="koszt")
    private Integer real_val;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getReal_val() {
        return real_val;
    }

    public void setReal_val(Integer real_val) {
        this.real_val = real_val;
    }
}
