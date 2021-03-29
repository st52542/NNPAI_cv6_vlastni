package cz.vlastni.eshop;

import cz.vlastni.eshop.entity.Doprava;
import cz.vlastni.eshop.repository.DopravaRepository;
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
class DopravaTests {

    @Autowired
    private DopravaRepository dopravaRepository;

    @Test
    void saveDoprava() {
        Doprava deliver = new Doprava();
        deliver.setCena(500);
        dopravaRepository.save(deliver);
        List<Doprava> all = dopravaRepository.findAll();
        Assertions.assertTrue(all.size()==1);
    }

    @Test
    void saveMoreDoprava() {
        Doprava deliver = new Doprava();
        deliver.setCena(500);
        dopravaRepository.save(deliver);
        Doprava deliver2 = new Doprava();
        deliver2.setCena(55);
        deliver2.setPopis("blabla");
        dopravaRepository.save(deliver2);
        Doprava deliver3 = new Doprava();
        deliver3.setCena(10);
        deliver3.setPopis("blabla3");
        dopravaRepository.save(deliver3);
        List<Doprava> all = dopravaRepository.findAll();
        Assertions.assertTrue(all.size()==3);
    }
    @Test
    void findDopravaViaCena() {
        Doprava deliver = new Doprava();
        deliver.setCena(500);
        dopravaRepository.save(deliver);
        Doprava deliver2 = new Doprava();
        deliver2.setCena(55);
        deliver2.setPopis("blabla");
        dopravaRepository.save(deliver2);
        Doprava deliver3 = new Doprava();
        deliver3.setCena(10);
        deliver3.setPopis("blabla3");
        dopravaRepository.save(deliver3);
        Doprava result = dopravaRepository.findByCena(55);
        Assertions.assertTrue(result.getCena()==55);
    }
    @Test
    void removeDopravaViaCena() {
        Doprava deliver = new Doprava();
        deliver.setCena(500);
        dopravaRepository.save(deliver);
        Doprava deliver2 = new Doprava();
        deliver2.setCena(55);
        deliver2.setPopis("blabla");
        dopravaRepository.save(deliver2);
        Doprava deliver3 = new Doprava();
        deliver3.setCena(10);
        deliver3.setPopis("blabla3");
        dopravaRepository.save(deliver3);
        Doprava result = dopravaRepository.findByCena(55);
        dopravaRepository.removeDopravaById(result.getId());
        Assertions.assertTrue(dopravaRepository.findAll().size()==2);
    }

}
