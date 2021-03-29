package cz.vlastni.eshop.dto;


public class PridejZmenPlatbaDto {
    private Long id;
    private String popis;
    private Double prevod;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
