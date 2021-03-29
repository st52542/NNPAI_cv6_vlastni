package cz.vlastni.eshop.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Uzivatel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 45,nullable = false)
    private String jmeno;
    @Column(length = 45,nullable = false)
    private String prijmeni;
    @Column(length = 100,nullable = false,unique = true)
    private String email;
    @Column(length = 100,nullable = false)
    private String heslo;
    @Column(length = 100,nullable = false)
    private String adresa;
    @Column(nullable = false)
    private Boolean admin;

    @OneToMany(mappedBy = "id")
    private Set<Nakup> nakup;


    public void setId(Long id) {
        this.id = id;
    }


    public Long getId() {
        return id;
    }

    public String getJmeno() {
        return jmeno;
    }

    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    public String getPrijmeni() {
        return prijmeni;
    }

    public void setPrijmeni(String prijmeni) {
        this.prijmeni = prijmeni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHeslo() {
        return heslo;
    }

    public void setHeslo(String heslo) {
        this.heslo = heslo;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public Set<Nakup> getNakup() {
        return nakup;
    }

    public void setNakup(Set<Nakup> nakup) {
        this.nakup = nakup;
    }
}
