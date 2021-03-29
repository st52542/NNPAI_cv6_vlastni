package cz.vlastni.eshop.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class NakoupenaPolozka {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Integer mnozstvi;
    @Column(nullable = false)
    private Boolean platnost;

/*    @OneToMany(mappedBy = "id")
    private Set<Nakup> nakup;*/

    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }

    public Integer getMnozstvi() {
        return mnozstvi;
    }

    public void setMnozstvi(Integer mnozstvi) {
        this.mnozstvi = mnozstvi;
    }

    public Boolean getPlatnost() {
        return platnost;
    }

    public void setPlatnost(Boolean platnost) {
        this.platnost = platnost;
    }

/*    public Set<Nakup> getNakup() {
        return nakup;
    }

    public void setNakup(Set<Nakup> nakup) {
        this.nakup = nakup;
    }*/
}
