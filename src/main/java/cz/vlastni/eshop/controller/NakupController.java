package cz.vlastni.eshop.controller;

import cz.vlastni.eshop.entity.Nakup;
import cz.vlastni.eshop.repository.DopravaRepository;
import cz.vlastni.eshop.repository.NakupRepository;
import cz.vlastni.eshop.repository.PlatbaRepository;
import cz.vlastni.eshop.repository.UzivatelRepository;
import cz.vlastni.eshop.service.INakupService;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class NakupController {

    @Autowired
    NakupRepository nakupRepository;

    private final INakupService iNakupService;
    private final DopravaRepository dopravaRepository;
    private final PlatbaRepository platbaRepository;
    private final UzivatelRepository uzivatelRepository;

    public NakupController(INakupService iNakupService, DopravaRepository dopravaRepository, PlatbaRepository platbaRepository, UzivatelRepository uzivatelRepository) {
        this.iNakupService = iNakupService;
        this.dopravaRepository = dopravaRepository;
        this.platbaRepository = platbaRepository;
        this.uzivatelRepository = uzivatelRepository;
    }

    @GetMapping("/")
    public String zobrazVsechnyPlatby(Model model) {
        model.addAttribute("nakupList", nakupRepository.findAll());
        return "nakup-list";
    }

    @ExceptionHandler({RuntimeException.class, NotFound.class})
    public String ochranaChyb() {
        return "chyba";
    }


    @GetMapping("/nakup-detail/{id}")
    public String zobrazDetailyPlatby(@PathVariable Long id, Model model) {
        Nakup nakup =nakupRepository.findById(id).get();
        model.addAttribute("nakup", nakup);
        model.addAttribute("uzivatel",uzivatelRepository.findById(nakup.getUzivatel().getId()).get());
        model.addAttribute("platba",platbaRepository.findById(nakup.getPlatba().getId()).get());
        model.addAttribute("doprava",dopravaRepository.findById(nakup.getDoprava().getId()).get());
        return "nakup-detail";
    }

    @PostMapping("/checkout")
    public String checkoutPost(@RequestParam String platba,@RequestParam String doprava,@RequestParam String uzivatel,Model model){
        iNakupService.checkout(platba,doprava,uzivatel);
        model.addAttribute("nakupList", nakupRepository.findAll());
        return "redirect:/";
    }

    @GetMapping("/checkout")
    public String checkout(Model model) {
        model.addAttribute("dopravy",dopravaRepository.findAll());
        model.addAttribute("platby",platbaRepository.findAll());
        model.addAttribute("uzivately",uzivatelRepository.findAll());
        return "checkout";
    }
}
