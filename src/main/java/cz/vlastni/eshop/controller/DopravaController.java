package cz.vlastni.eshop.controller;

import cz.vlastni.eshop.dto.PridejZmenDopravaDto;
import cz.vlastni.eshop.entity.Doprava;
import cz.vlastni.eshop.repository.DopravaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DopravaController {
    @Autowired
    DopravaRepository dopravaRepository;

    @ExceptionHandler(RuntimeException.class)
    public String ochranaChyb() {
        return "chyba";
    }
    @GetMapping("/doprava-detail/{id}")
    public String zobrazDetailyDopravy(@PathVariable(required = false) Long id, Model model) {
        model.addAttribute("doprava", dopravaRepository.findById(id).get());
        return "doprava-detail";
    }
    @GetMapping("/doprava")
    public String zobrazVsechnuDopravu(Model model){
        model.addAttribute("dopravaList",dopravaRepository.findAll());
        return "doprava-list";
    }
    @GetMapping(value = {"/doprava-reg-form", "/doprava-reg-form/{id}"})
    public String zobrazDopravu(@PathVariable(required = false) Long id, Model model) {
        if (id != null) {
            Doprava byId = dopravaRepository.findById(id).orElse(new Doprava());
            PridejZmenDopravaDto dto = new PridejZmenDopravaDto();
            dto.setId(byId.getId());
            dto.setPopis(byId.getPopis());
            dto.setCena(byId.getCena());
            model.addAttribute("doprava", byId);
        } else {
            model.addAttribute("doprava", new PridejZmenDopravaDto());
        }
        return "doprava-reg-form";
    }
    @PostMapping("/uloz-dopravu")
    public String zpracujDopravu(PridejZmenDopravaDto pridejZmenDopravaDto) {
        Doprava doprava = new Doprava();
        doprava.setId(pridejZmenDopravaDto.getId());
        doprava.setCena(pridejZmenDopravaDto.getCena());
        doprava.setPopis(pridejZmenDopravaDto.getPopis());
        dopravaRepository.save(doprava);
        return "redirect:/doprava";
    }
}
