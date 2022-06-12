package pl.poznan.put.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="recenzje")
public class Review {
    @Id
    @Column(name="id_rec")
    private Long id;

    @Column(name="id_gosc")
    private Long id_g;

    @Column(name="opis")
    private String desc;

    @Column(name="ocena")
    private Integer stars;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_g() {
        return id_g;
    }

    public void setId_g(Long id_g) {
        this.id_g = id_g;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }
}
