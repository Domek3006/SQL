package pl.poznan.put.Entities;

import javax.persistence.*;

@Entity
@Table(name = "kasyna")
public class Casino {
    @Id
    @Column(name="id_kas")
    private Long id_cas;

    @Column(name="nazwa")
    private String name;

    @Column(name="miasto")
    private String city;

    @Column(name="miejsca")
    private Integer seats;

    public Casino(){};

    public Casino(String nazwa, String miasto, int miejsca){
        this.id_cas = Long.parseLong("1");
        this.name = nazwa;
        this.city = miasto;
        this.seats = miejsca;
    }

    public Long getId_cas(){
        return id_cas;
    }
    public void setId_cas(Long id){
        this.id_cas = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String nazwa) {
        this.name = nazwa;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String miasto) {
        this.city = miasto;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer miejsca) {
        this.seats = miejsca;
    }
}
