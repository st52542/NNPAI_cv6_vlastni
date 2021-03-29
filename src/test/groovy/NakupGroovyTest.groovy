package cz.vlastni.eshop

import cz.vlastni.eshop.dataFactory.Creator
import cz.vlastni.eshop.dataFactory.DopravaTestDataFactory;
import cz.vlastni.eshop.dataFactory.NakupTestDataFactory;
import cz.vlastni.eshop.dataFactory.PlatbaTestDataFactory;
import cz.vlastni.eshop.dataFactory.UzivatelTestDataFactory;
import cz.vlastni.eshop.entity.*;
import cz.vlastni.eshop.repository.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

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
        Assertions.assertTrue(all.size()==1);
    }


    @Test
    void saveMoreNakup() {
        nakupTestDataFactory.nakup1();
        nakupTestDataFactory.nakup2();
        List<Nakup> all = nakupRepository.findAll();
        Assertions.assertTrue(all.size()==2);
    }
    @Test
    void findNakupViaObjednavka() {
        nakupTestDataFactory.nakup1();
        nakupTestDataFactory.nakup2();
        Nakup search = nakupRepository.findByObjednavka(100);
        Assertions.assertTrue(search.getObjednavka()==100);
    }
    @Test
    void removeNakup() {
        nakupTestDataFactory.nakup1();
        nakupTestDataFactory.nakup2();
        Nakup removed = nakupRepository.findByObjednavka(25);
        nakupRepository.removeNakupById(removed.getId());
        List<Nakup> all = nakupRepository.findAll();
        Assertions.assertTrue(all.size()==2);
    }
    @Test
    void findUzivatelViaObjednavka() {
        nakupTestDataFactory.nakup1();
        nakupTestDataFactory.nakup2();
        Nakup search = nakupRepository.findByObjednavka(100);
        Assertions.assertTrue(search.getUzivatel().getEmail()=="vv@bbb.cz");
    }
    @Test
    void findPlatbaViaObjednavka() {
        nakupTestDataFactory.nakup1();
        nakupTestDataFactory.nakup2();
        Nakup search = nakupRepository.findByObjednavka(100);
        Assertions.assertTrue(search.getPlatba().getPrevod()==10.5);
    }

}
