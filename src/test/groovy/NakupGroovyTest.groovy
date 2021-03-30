package cz.vlastni.eshop

import cz.vlastni.eshop.dataFactory.Creator
import cz.vlastni.eshop.dataFactory.DopravaTestDataFactory;
import cz.vlastni.eshop.dataFactory.NakupTestDataFactory;
import cz.vlastni.eshop.dataFactory.PlatbaTestDataFactory;
import cz.vlastni.eshop.dataFactory.UzivatelTestDataFactory;
import cz.vlastni.eshop.entity.*;
import cz.vlastni.eshop.repository.*;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import([Creator.class])
class NakupGrovyTests {

    @Autowired
    private Creator creator;

    @Autowired
    NakupRepository nakupRepository;


    @Test
    void saveNakup() {
        Nakup novy = new Nakup();
        creator.save(novy);
        List<Nakup> all = nakupRepository.findAll();
        Assertions.assertTrue(all.size() == 1);
    }


    @Test
    void saveMoreNakup() {
        Nakup novy = new Nakup();
        creator.save(novy);
        Nakup novy2 = new Nakup();
        creator.save(novy2);
        List<Nakup> all = nakupRepository.findAll();
        Assertions.assertTrue(all.size() == 2);
    }

    @Test
    void findNakupViaObjednavka() {
        Nakup novy3 = new Nakup();
        creator.save(novy3);
        Nakup novy = new Nakup(objednavka: 100);
        creator.save(novy);
        Nakup novy2 = new Nakup();
        creator.save(novy2);
        Nakup search = nakupRepository.findByObjednavka(100);
        Assertions.assertTrue(search.getObjednavka() == 100);
    }

    @Test
    void removeNakup() {
        Nakup novy3 = new Nakup();
        creator.save(novy3);
        Nakup novy = new Nakup(objednavka: 25);
        creator.save(novy);
        Nakup novy2 = new Nakup();
        creator.save(novy2);
        Nakup removed = nakupRepository.findByObjednavka(25);
        nakupRepository.removeNakupById(removed.getId());
        List<Nakup> all = nakupRepository.findAll();
        Assertions.assertTrue(all.size() == 2);
    }

}
