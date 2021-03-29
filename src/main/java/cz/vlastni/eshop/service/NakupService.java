package cz.vlastni.eshop.service;

import cz.vlastni.eshop.entity.Doprava;
import cz.vlastni.eshop.entity.Nakup;
import cz.vlastni.eshop.entity.Platba;
import cz.vlastni.eshop.entity.Uzivatel;
import cz.vlastni.eshop.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.*;

@Service
@SessionScope
public class NakupService implements INakupService {
    private Map<Doprava, Integer> kosik;
    private final NakupRepository nakupRepository;
    private final DopravaRepository dopravaRepository;
    private final PlatbaRepository platbaRepository;
    private final UzivatelRepository uzivatelRepository;

    public NakupService(NakupRepository nakupRepository, DopravaRepository dopravaRepository, PlatbaRepository platbaRepository, UzivatelRepository uzivatelRepository) {
        this.nakupRepository = nakupRepository;
        this.dopravaRepository = dopravaRepository;
        this.platbaRepository = platbaRepository;
        this.uzivatelRepository = uzivatelRepository;
        this.kosik = new HashMap<>();
    }

    @Override
    public Map<Doprava, Integer> getKosik() {
        return kosik;
    }

    @Override
    public void checkout(String platba, String doprava, String uzivatel) {
        Nakup nakup = new Nakup();
        nakup.setDatumVytvoreni(new Date());
        nakup.setObjednavka((int) ((int)(long)((int) ((new Date().getTime()/1000)))*1000L));
        nakup.setStav(true);
        Platba platbaFrom = platbaRepository.findByPopis(platba);
        Uzivatel uzivatelFrom = uzivatelRepository.findByJmeno(uzivatel);
        Doprava dopravaFrom = dopravaRepository.findByPopis(doprava);
        nakup.setPlatba(platbaFrom);
        nakup.setUzivatel(uzivatelFrom);
        nakup.setDoprava(dopravaFrom);
        nakupRepository.save(nakup);
        kosik.clear();
    }
}
