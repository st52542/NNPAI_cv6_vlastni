package cz.vlastni.eshop.controller;

import cz.vlastni.eshop.dto.PridejZmenDopravaDto;
import cz.vlastni.eshop.dto.PridejZmenPlatbaDto;
import cz.vlastni.eshop.entity.Doprava;
import cz.vlastni.eshop.entity.Platba;
import cz.vlastni.eshop.repository.DopravaRepository;
import cz.vlastni.eshop.repository.PlatbaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PlatbaController {
    @Autowired
    PlatbaRepository platbaRepository;

    @ExceptionHandler(RuntimeException.class)
    public String ochranaChyb() {
        return "chyba";
    }

    @GetMapping("/platba-detail/{id}")
    public String zobrazDetailyPlatby(@PathVariable(required = false) Long id, Model model) {
        model.addAttribute("platba", platbaRepository.findById(id).get());
        return "platba-detail";
    }
    @GetMapping("/platba")
    public String zobrazVsechnyPlatby(Model model) {
        model.addAttribute("platbaList", platbaRepository.findAll());
        return "platba-list";
    }

    @GetMapping(value = {"/platba-reg-form", "/platba-reg-form/{id}"})
    public String zobrazPlatbu(@PathVariable(required = false) Long id, Model model) {
        if (id != null) {
            Platba byId = platbaRepository.findById(id).orElse(new Platba());
            PridejZmenPlatbaDto dto = new PridejZmenPlatbaDto();
            dto.setId(byId.getId());
            dto.setPopis(byId.getPopis());
            dto.setPrevod(byId.getPrevod());
            model.addAttribute("platba", byId);
        } else {
            model.addAttribute("platba", new PridejZmenPlatbaDto());
        }
        return "platba-reg-form";
    }

    @PostMapping("/uloz-platbu")
    public String zpracujPlatbu(PridejZmenPlatbaDto pridejZmenPlatbaDto) {
        Platba platba = new Platba();
        platba.setId(pridejZmenPlatbaDto.getId());
        platba.setPopis(pridejZmenPlatbaDto.getPopis());
        platba.setPrevod(pridejZmenPlatbaDto.getPrevod());
        platbaRepository.save(platba);
        return "redirect:/platba";
    }
}
