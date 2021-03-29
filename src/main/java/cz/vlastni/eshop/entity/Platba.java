package cz.vlastni.eshop.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Platba {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 45)
    private String popis;
    @Column(nullable = false)
    private Double prevod;

    @OneToMany(mappedBy = "id")
    private Set<Nakup> nakup;

    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }

    public String getPopis() {
        return popis;
    }

    public void setPopis(String popis) {
        this.popis = popis;
    }

    public Double getPrevod() {
        return prevod;
    }

    public void setPrevod(Double prevod) {
        this.prevod = prevod;
    }

    public Set<Nakup> getNakup() {
        return nakup;
    }

    public void setNakup(Set<Nakup> nakup) {
        this.nakup = nakup;
    }
}
