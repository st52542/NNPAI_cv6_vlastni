package cz.vlastni.eshop.controller;

import cz.vlastni.eshop.dto.PridejZmenUzivatelDto;
import cz.vlastni.eshop.entity.Uzivatel;
import cz.vlastni.eshop.repository.UzivatelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UzivatelController {

    @Autowired
    UzivatelRepository uzivatelRepository;

    @ExceptionHandler(RuntimeException.class)
    public String ochranaChyb() {
        return "chyba";
    }

    @GetMapping("/uzivatel-detail/{id}")
    public String zobrazDetailyUzivatele(@PathVariable(required = false) Long id, Model model) {
        model.addAttribute("uzivatel", uzivatelRepository.findById(id).get());
        return "uzivatel-detail";
    }

    @GetMapping("/uzivatel")
    public String zobrazVsechnyUzivatele(Model model) {
        model.addAttribute("uzivatelList", uzivatelRepository.findAll());
        return "uzivatel-list";
    }

    @GetMapping(value = {"/uzivatel-reg-form", "/uzivatel-reg-form/{id}"})
    public String zobrazUzivatele(@PathVariable(required = false) Long id, Model model) {
        if (id != null) {
            Uzivatel byId = uzivatelRepository.findById(id).orElse(new Uzivatel());
            PridejZmenUzivatelDto dto = new PridejZmenUzivatelDto();
            dto.setId(byId.getId());
            dto.setJmeno(byId.getJmeno());
            dto.setPrijmeni(byId.getPrijmeni());
            dto.setHeslo(byId.getHeslo());
            dto.setEmail(byId.getEmail());
            dto.setAdresa(byId.getAdresa());
            dto.setAdmin(byId.getAdmin());
            model.addAttribute("uzivatel", byId);
        } else {
            model.addAttribute("uzivatel", new PridejZmenUzivatelDto());
        }
        return "uzivatel-reg-form";
    }

    @PostMapping("/uloz-uzivatele")
    public String zpracujUzivatele(PridejZmenUzivatelDto pridejZmenUzivatelDto) {
        Uzivatel uziv = new Uzivatel();
        uziv.setId(pridejZmenUzivatelDto.getId());
        uziv.setJmeno(pridejZmenUzivatelDto.getJmeno());
        uziv.setPrijmeni(pridejZmenUzivatelDto.getPrijmeni());
        uziv.setHeslo(pridejZmenUzivatelDto.getHeslo());
        uziv.setAdresa(pridejZmenUzivatelDto.getAdresa());
        uziv.setEmail(pridejZmenUzivatelDto.getEmail());
        uziv.setAdmin(pridejZmenUzivatelDto.getAdmin());
        uzivatelRepository.save(uziv);
        return "redirect:/";
    }
}
