package cz.vlastni.eshop.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Nakup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Date datumVytvoreni;
    @Column(nullable = false)
    private Integer objednavka;
    @Column(nullable = false)
    private Boolean stav;
    @ManyToOne
    private Uzivatel uzivatel;
    @ManyToOne
    private Doprava doprava;
    @ManyToOne
    private Platba platba;
/*    @ManyToOne
    private NakoupenaPolozka nakoupenaPolozka;*/

    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }

    public Date getDatumVytvoreni() {
        return datumVytvoreni;
    }

    public void setDatumVytvoreni(Date datumVytvoreni) {
        this.datumVytvoreni = datumVytvoreni;
    }

    public Integer getObjednavka() {
        return objednavka;
    }

    public void setObjednavka(Integer objednavka) {
        this.objednavka = objednavka;
    }

    public Boolean getStav() {
        return stav;
    }

    public void setStav(Boolean stav) {
        this.stav = stav;
    }

    public Uzivatel getUzivatel() {
        return uzivatel;
    }

    public void setUzivatel(Uzivatel uzivatel) {
        this.uzivatel = uzivatel;
    }

    public Doprava getDoprava() {
        return doprava;
    }

    public void setDoprava(Doprava doprava) {
        this.doprava = doprava;
    }

    public Platba getPlatba() {
        return platba;
    }

    public void setPlatba(Platba platba) {
        this.platba = platba;
    }

}
