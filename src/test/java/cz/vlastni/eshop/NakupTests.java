package cz.vlastni.eshop;

import cz.vlastni.eshop.entity.*;
import cz.vlastni.eshop.repository.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class NakupTests {

    @Autowired
    private NakupRepository nakupRepository;
    @Autowired
    private DopravaRepository dopravaRepository;
    @Autowired
    private NakoupenaPolozkaRepository nakoupenaPolozkaRepository;
    @Autowired
    private PlatbaRepository platbaRepository;
    @Autowired
    private UzivatelRepository uzivatelRepository;

    @Test
    void saveNakup() {
        Uzivatel user = new Uzivatel();
        user.setJmeno("abc");
        user.setPrijmeni("cba");
        user.setEmail("aa@bbb.cz");
        user.setHeslo("pass");
        user.setAdresa("konecna 123");
        user.setAdmin(true);
        uzivatelRepository.save(user);
        Platba pay = new Platba();
        pay.setPrevod(5.0);
        platbaRepository.save(pay);
        NakoupenaPolozka nakoupenaPolozka = new NakoupenaPolozka();
        nakoupenaPolozka.setMnozstvi(5);
        nakoupenaPolozka.setPlatnost(true);
        nakoupenaPolozkaRepository.save(nakoupenaPolozka);
        Doprava deliver = new Doprava();
        deliver.setCena(500);
        dopravaRepository.save(deliver);
        Nakup buy = new Nakup();
        buy.setDatumVytvoreni(new Date(2021,3,14));
        buy.setStav(true);
        buy.setObjednavka(50);
        buy.setDoprava(deliver);
        //buy.setNakoupenaPolozka(nakoupenaPolozka);
        buy.setPlatba(pay);
        buy.setUzivatel(user);
        nakupRepository.save(buy);
        List<Nakup> all = nakupRepository.findAll();
        Assertions.assertTrue(all.size()==1);
    }
    @Test
    void saveMoreNakup() {
        Uzivatel user = new Uzivatel();
        user.setJmeno("abc");
        user.setPrijmeni("cba");
        user.setEmail("aa@bbb.cz");
        user.setHeslo("pass");
        user.setAdresa("konecna 123");
        user.setAdmin(true);
        uzivatelRepository.save(user);
        Platba pay = new Platba();
        pay.setPrevod(5.0);
        platbaRepository.save(pay);
        NakoupenaPolozka nakoupenaPolozka = new NakoupenaPolozka();
        nakoupenaPolozka.setMnozstvi(5);
        nakoupenaPolozka.setPlatnost(true);
        nakoupenaPolozkaRepository.save(nakoupenaPolozka);
        Doprava deliver = new Doprava();
        deliver.setCena(500);
        dopravaRepository.save(deliver);
        Nakup buy = new Nakup();
        buy.setDatumVytvoreni(new Date(2021,3,14));
        buy.setStav(true);
        buy.setObjednavka(50);
        buy.setDoprava(deliver);
       // buy.setNakoupenaPolozka(nakoupenaPolozka);
        buy.setPlatba(pay);
        buy.setUzivatel(user);
        nakupRepository.save(buy);
        Nakup buy2 = new Nakup();
        buy2.setDatumVytvoreni(new Date(2021,3,14));
        buy2.setStav(true);
        buy2.setObjednavka(25);
        buy2.setDoprava(deliver);
       // buy2.setNakoupenaPolozka(nakoupenaPolozka);
        buy2.setPlatba(pay);
        buy2.setUzivatel(user);
        nakupRepository.save(buy2);
        Nakup buy3 = new Nakup();
        buy3.setDatumVytvoreni(new Date(2021,3,14));
        buy3.setStav(true);
        buy3.setObjednavka(3);
        buy3.setDoprava(deliver);
      //  buy3.setNakoupenaPolozka(nakoupenaPolozka);
        buy3.setPlatba(pay);
        buy3.setUzivatel(user);
        nakupRepository.save(buy3);
        List<Nakup> all = nakupRepository.findAll();
        Assertions.assertTrue(all.size()==3);
    }
    @Test
    void findNakupViaObjednavka() {
        Uzivatel user = new Uzivatel();
        user.setJmeno("abc");
        user.setPrijmeni("cba");
        user.setEmail("aa@bbb.cz");
        user.setHeslo("pass");
        user.setAdresa("konecna 123");
        user.setAdmin(true);
        uzivatelRepository.save(user);
        Platba pay = new Platba();
        pay.setPrevod(5.0);
        platbaRepository.save(pay);
        NakoupenaPolozka nakoupenaPolozka = new NakoupenaPolozka();
        nakoupenaPolozka.setMnozstvi(5);
        nakoupenaPolozka.setPlatnost(true);
        nakoupenaPolozkaRepository.save(nakoupenaPolozka);
        Doprava deliver = new Doprava();
        deliver.setCena(500);
        dopravaRepository.save(deliver);
        Nakup buy = new Nakup();
        buy.setDatumVytvoreni(new Date(2021,3,14));
        buy.setStav(true);
        buy.setObjednavka(50);
        buy.setDoprava(deliver);
    //    buy.setNakoupenaPolozka(nakoupenaPolozka);
        buy.setPlatba(pay);
        buy.setUzivatel(user);
        nakupRepository.save(buy);
        Nakup buy2 = new Nakup();
        buy2.setDatumVytvoreni(new Date(2021,3,14));
        buy2.setStav(true);
        buy2.setObjednavka(25);
        buy2.setDoprava(deliver);
      //  buy2.setNakoupenaPolozka(nakoupenaPolozka);
        buy2.setPlatba(pay);
        buy2.setUzivatel(user);
        nakupRepository.save(buy2);
        Nakup buy3 = new Nakup();
        buy3.setDatumVytvoreni(new Date(2021,3,14));
        buy3.setStav(true);
        buy3.setObjednavka(3);
        buy3.setDoprava(deliver);
      //  buy3.setNakoupenaPolozka(nakoupenaPolozka);
        buy3.setPlatba(pay);
        buy3.setUzivatel(user);
        nakupRepository.save(buy3);
        Nakup search = nakupRepository.findByObjednavka(25);
        Assertions.assertTrue(search.getObjednavka()==25);
    }
    @Test
    void removeNakup() {
        Uzivatel user = new Uzivatel();
        user.setJmeno("abc");
        user.setPrijmeni("cba");
        user.setEmail("aa@bbb.cz");
        user.setHeslo("pass");
        user.setAdresa("konecna 123");
        user.setAdmin(true);
        uzivatelRepository.save(user);
        Platba pay = new Platba();
        pay.setPrevod(5.0);
        platbaRepository.save(pay);
        NakoupenaPolozka nakoupenaPolozka = new NakoupenaPolozka();
        nakoupenaPolozka.setMnozstvi(5);
        nakoupenaPolozka.setPlatnost(true);
        nakoupenaPolozkaRepository.save(nakoupenaPolozka);
        Doprava deliver = new Doprava();
        deliver.setCena(500);
        dopravaRepository.save(deliver);
        Nakup buy = new Nakup();
        buy.setDatumVytvoreni(new Date(2021,3,14));
        buy.setStav(true);
        buy.setObjednavka(50);
        buy.setDoprava(deliver);
      //  buy.setNakoupenaPolozka(nakoupenaPolozka);
        buy.setPlatba(pay);
        buy.setUzivatel(user);
        nakupRepository.save(buy);
        Nakup buy2 = new Nakup();
        buy2.setDatumVytvoreni(new Date(2021,3,14));
        buy2.setStav(true);
        buy2.setObjednavka(25);
        buy2.setDoprava(deliver);
       // buy2.setNakoupenaPolozka(nakoupenaPolozka);
        buy2.setPlatba(pay);
        buy2.setUzivatel(user);
        nakupRepository.save(buy2);
        Nakup buy3 = new Nakup();
        buy3.setDatumVytvoreni(new Date(2021,3,14));
        buy3.setStav(true);
        buy3.setObjednavka(3);
        buy3.setDoprava(deliver);
       // buy3.setNakoupenaPolozka(nakoupenaPolozka);
        buy3.setPlatba(pay);
        buy3.setUzivatel(user);
        nakupRepository.save(buy3);
        Nakup removed = nakupRepository.findByObjednavka(25);
        nakupRepository.removeNakupById(removed.getId());
        List<Nakup> all = nakupRepository.findAll();
        Assertions.assertTrue(all.size()==2);
    }
    @Test
    void findUzivatelViaObjednavka() {
        Uzivatel user = new Uzivatel();
        user.setJmeno("abc");
        user.setPrijmeni("cba");
        user.setEmail("aa@bbb.cz");
        user.setHeslo("pass");
        user.setAdresa("konecna 123");
        user.setAdmin(true);
        uzivatelRepository.save(user);
        Platba pay = new Platba();
        pay.setPrevod(5.0);
        platbaRepository.save(pay);
        NakoupenaPolozka nakoupenaPolozka = new NakoupenaPolozka();
        nakoupenaPolozka.setMnozstvi(5);
        nakoupenaPolozka.setPlatnost(true);
        nakoupenaPolozkaRepository.save(nakoupenaPolozka);
        Doprava deliver = new Doprava();
        deliver.setCena(500);
        dopravaRepository.save(deliver);
        Nakup buy = new Nakup();
        buy.setDatumVytvoreni(new Date(2021,3,14));
        buy.setStav(true);
        buy.setObjednavka(50);
        buy.setDoprava(deliver);
     //   buy.setNakoupenaPolozka(nakoupenaPolozka);
        buy.setPlatba(pay);
        buy.setUzivatel(user);
        nakupRepository.save(buy);
        Nakup buy2 = new Nakup();
        buy2.setDatumVytvoreni(new Date(2021,3,14));
        buy2.setStav(true);
        buy2.setObjednavka(25);
        buy2.setDoprava(deliver);
     //   buy2.setNakoupenaPolozka(nakoupenaPolozka);
        buy2.setPlatba(pay);
        buy2.setUzivatel(user);
        nakupRepository.save(buy2);
        Nakup buy3 = new Nakup();
        buy3.setDatumVytvoreni(new Date(2021,3,14));
        buy3.setStav(true);
        buy3.setObjednavka(3);
        buy3.setDoprava(deliver);
     //   buy3.setNakoupenaPolozka(nakoupenaPolozka);
        buy3.setPlatba(pay);
        buy3.setUzivatel(user);
        nakupRepository.save(buy3);
        Nakup search = nakupRepository.findByObjednavka(25);
        Assertions.assertTrue(search.getUzivatel().getEmail()=="aa@bbb.cz");
    }
    @Test
    void findPlatbaViaObjednavka() {
        Uzivatel user = new Uzivatel();
        user.setJmeno("abc");
        user.setPrijmeni("cba");
        user.setEmail("aa@bbb.cz");
        user.setHeslo("pass");
        user.setAdresa("konecna 123");
        user.setAdmin(true);
        uzivatelRepository.save(user);
        Platba pay = new Platba();
        pay.setPrevod(5.0);
        platbaRepository.save(pay);
        NakoupenaPolozka nakoupenaPolozka = new NakoupenaPolozka();
        nakoupenaPolozka.setMnozstvi(5);
        nakoupenaPolozka.setPlatnost(true);
        nakoupenaPolozkaRepository.save(nakoupenaPolozka);
        Doprava deliver = new Doprava();
        deliver.setCena(500);
        dopravaRepository.save(deliver);
        Nakup buy = new Nakup();
        buy.setDatumVytvoreni(new Date(2021,3,14));
        buy.setStav(true);
        buy.setObjednavka(50);
        buy.setDoprava(deliver);
     //   buy.setNakoupenaPolozka(nakoupenaPolozka);
        buy.setPlatba(pay);
        buy.setUzivatel(user);
        nakupRepository.save(buy);
        Nakup buy2 = new Nakup();
        buy2.setDatumVytvoreni(new Date(2021,3,14));
        buy2.setStav(true);
        buy2.setObjednavka(25);
        buy2.setDoprava(deliver);
      //  buy2.setNakoupenaPolozka(nakoupenaPolozka);
        buy2.setPlatba(pay);
        buy2.setUzivatel(user);
        nakupRepository.save(buy2);
        Nakup buy3 = new Nakup();
        buy3.setDatumVytvoreni(new Date(2021,3,14));
        buy3.setStav(true);
        buy3.setObjednavka(3);
        buy3.setDoprava(deliver);
       // buy3.setNakoupenaPolozka(nakoupenaPolozka);
        buy3.setPlatba(pay);
        buy3.setUzivatel(user);
        nakupRepository.save(buy3);
        Nakup search = nakupRepository.findByObjednavka(25);
        Assertions.assertTrue(search.getPlatba().getPrevod()==5.0);
    }

}
