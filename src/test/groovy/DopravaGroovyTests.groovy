package cz.vlastni.eshop

import cz.vlastni.eshop.dataFactory.Creator;
import cz.vlastni.eshop.entity.Doprava;
import cz.vlastni.eshop.repository.DopravaRepository
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
class DopravaGroovyTests {

    @Autowired
    private DopravaRepository dopravaRepository;

    @Autowired
    private Creator creator;

    @Test
    void saveDoprava() {
        Doprava novaDoprava = new Doprava();
        creator.save(novaDoprava);
        def all = dopravaRepository.findAll();
        Assertions.assertTrue(all.size() == 1);
    }


    @Test
    void saveMoreDoprava() {
        Doprava novaDoprava = new Doprava();
        Doprava novaDoprava2 = new Doprava();
        Doprava novaDoprava3 = new Doprava();
        creator.save(novaDoprava);
        creator.save(novaDoprava2);
        creator.save(novaDoprava3);
        List<Doprava> all = dopravaRepository.findAll();
        Assertions.assertTrue(all.size() == 3);
    }


    @Test
    void findDopravaViaCena() {
        Doprava testDoprava = new Doprava(cena: 565, popis: "testDoprava")
        creator.save(testDoprava);
        Doprava novaDoprava2 = new Doprava();
        Doprava novaDoprava3 = new Doprava();
        creator.save(novaDoprava2);
        creator.save(novaDoprava3);
        def jedenHledany = dopravaRepository.findByCena(565);
        Assertions.assertTrue(jedenHledany.popis == "testDoprava");
    }


    @Test
    void removeDopravaViaCena() {
        Doprava testDoprava = new Doprava(cena: 109)
        creator.save(testDoprava);
        Doprava novaDoprava2 = new Doprava();
        Doprava novaDoprava3 = new Doprava();
        creator.save(novaDoprava2);
        creator.save(novaDoprava3);
        Doprava result = dopravaRepository.findByCena(109);
        dopravaRepository.removeDopravaById(result.getId());
        Assertions.assertTrue(dopravaRepository.findAll().size() == 2);
    }

}
