package cz.vlastni.eshop.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Doprava {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 45)
    private String popis;
    @Column(nullable = false)
    private Integer cena;

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

    public Integer getCena() {
        return cena;
    }

    public void setCena(Integer cena) {
        this.cena = cena;
    }

    public Set<Nakup> getNakup() {
        return nakup;
    }

    public void setNakup(Set<Nakup> nakup) {
        this.nakup = nakup;
    }
}
