package cz.vlastni.eshop;

import cz.vlastni.eshop.entity.NakoupenaPolozka;
import cz.vlastni.eshop.repository.NakoupenaPolozkaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class NakoupenaPlozkaTests {

    @Autowired
    private NakoupenaPolozkaRepository nakoupenaPolozkaRepository;

    @Test
    void saveNakoupenaPolozka() {
        NakoupenaPolozka nakoupenaPolozka = new NakoupenaPolozka();
        nakoupenaPolozka.setMnozstvi(10);
        nakoupenaPolozka.setPlatnost(true);
        nakoupenaPolozkaRepository.save(nakoupenaPolozka);
        List<NakoupenaPolozka> all = nakoupenaPolozkaRepository.findAll();
        Assertions.assertTrue(all.size()==1);
    }
    @Test
    void saveMoreNakoupenaPolozka() {
        NakoupenaPolozka nakoupenaPolozka = new NakoupenaPolozka();
        nakoupenaPolozka.setMnozstvi(10);
        nakoupenaPolozka.setPlatnost(true);
        nakoupenaPolozkaRepository.save(nakoupenaPolozka);
        NakoupenaPolozka nakoupenaPolozka1 = new NakoupenaPolozka();
        nakoupenaPolozka1.setMnozstvi(10);
        nakoupenaPolozka1.setPlatnost(true);
        nakoupenaPolozkaRepository.save(nakoupenaPolozka1);
        NakoupenaPolozka nakoupenaPolozka2 = new NakoupenaPolozka();
        nakoupenaPolozka2.setMnozstvi(10);
        nakoupenaPolozka2.setPlatnost(true);
        nakoupenaPolozkaRepository.save(nakoupenaPolozka2);
        List<NakoupenaPolozka> all = nakoupenaPolozkaRepository.findAll();
        Assertions.assertTrue(all.size()==3);
    }
    @Test
    void findNakoupenaPolozkaViaMnozstvi() {
        NakoupenaPolozka nakoupenaPolozka = new NakoupenaPolozka();
        nakoupenaPolozka.setMnozstvi(5);
        nakoupenaPolozka.setPlatnost(true);
        nakoupenaPolozkaRepository.save(nakoupenaPolozka);
        NakoupenaPolozka nakoupenaPolozka1 = new NakoupenaPolozka();
        nakoupenaPolozka1.setMnozstvi(10);
        nakoupenaPolozka1.setPlatnost(true);
        nakoupenaPolozkaRepository.save(nakoupenaPolozka1);
        NakoupenaPolozka nakoupenaPolozka2 = new NakoupenaPolozka();
        nakoupenaPolozka2.setMnozstvi(15);
        nakoupenaPolozka2.setPlatnost(true);
        nakoupenaPolozkaRepository.save(nakoupenaPolozka2);
        NakoupenaPolozka result = nakoupenaPolozkaRepository.findByMnozstvi(10);
        Assertions.assertTrue(result.getMnozstvi()==10);
    }
    @Test
    void removeNakoupenaPolozkaViaMnozstvi() {
        NakoupenaPolozka nakoupenaPolozka = new NakoupenaPolozka();
        nakoupenaPolozka.setMnozstvi(5);
        nakoupenaPolozka.setPlatnost(true);
        nakoupenaPolozkaRepository.save(nakoupenaPolozka);
        NakoupenaPolozka nakoupenaPolozka1 = new NakoupenaPolozka();
        nakoupenaPolozka1.setMnozstvi(10);
        nakoupenaPolozka1.setPlatnost(true);
        nakoupenaPolozkaRepository.save(nakoupenaPolozka1);
        NakoupenaPolozka nakoupenaPolozka2 = new NakoupenaPolozka();
        nakoupenaPolozka2.setMnozstvi(15);
        nakoupenaPolozka2.setPlatnost(true);
        nakoupenaPolozkaRepository.save(nakoupenaPolozka2);
        NakoupenaPolozka result = nakoupenaPolozkaRepository.findByMnozstvi(10);
        nakoupenaPolozkaRepository.removeNakoupenaPolozkaById(result.getId());
        Assertions.assertTrue(nakoupenaPolozkaRepository.findAll().size()==2);
    }

}
