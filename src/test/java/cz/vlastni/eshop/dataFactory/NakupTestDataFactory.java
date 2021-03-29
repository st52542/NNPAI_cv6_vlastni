package cz.vlastni.eshop.dataFactory;

import cz.vlastni.eshop.entity.Doprava;
import cz.vlastni.eshop.entity.Nakup;
import cz.vlastni.eshop.entity.Platba;
import cz.vlastni.eshop.entity.Uzivatel;
import cz.vlastni.eshop.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class NakupTestDataFactory {

    @Autowired
    private NakupRepository nakupRepository;
    @Autowired
    private DopravaTestDataFactory dopravaTestDataFactory;
    @Autowired
    private PlatbaTestDataFactory platbaTestDataFactory;
    @Autowired
    private UzivatelTestDataFactory uzivatelTestDataFactory;

    public void nakup1() {
        Nakup buy = new Nakup();
        buy.setDatumVytvoreni(new Date(2021,3,14));
        buy.setStav(true);
        buy.setObjednavka(50);
        Doprava doprava = dopravaTestDataFactory.saveNewDoprava1();
        buy.setDoprava(doprava);
        Platba platba = platbaTestDataFactory.addPlatba1();
        buy.setPlatba(platba);
        Uzivatel uzivatel = uzivatelTestDataFactory.addUser1();
        buy.setUzivatel(uzivatel);
        nakupRepository.save(buy);
    }
    public void nakup2() {
        Nakup buy = new Nakup();
        buy.setDatumVytvoreni(new Date(2021,3,14));
        buy.setStav(true);
        buy.setObjednavka(100);
        Doprava doprava = dopravaTestDataFactory.saveNewDoprava2();
        buy.setDoprava(doprava);
        Platba platba = platbaTestDataFactory.addPlatba2();
        buy.setPlatba(platba);
        Uzivatel uzivatel = uzivatelTestDataFactory.addUser2();
        buy.setUzivatel(uzivatel);
        nakupRepository.save(buy);
    }
}
