package cz.vlastni.eshop

import cz.vlastni.eshop.dataFactory.Creator;
import cz.vlastni.eshop.dataFactory.PlatbaTestDataFactory;
import cz.vlastni.eshop.entity.Platba;
import cz.vlastni.eshop.repository.PlatbaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import([Creator.class])
class PlatbaGroovyTests {

    @Autowired
    private PlatbaRepository platbaRepository;

    @Autowired
    private Creator creator;

    @Test
    void savePlatba() {
        Platba platba =new Platba();
        creator.save(platba);
        List<Platba> all = platbaRepository.findAll();
        Assertions.assertTrue(all.size() == 1);
    }


    @Test
    void saveMorePlatba() {
        Platba platba =new Platba();
        creator.save(platba);
        Platba platba2 =new Platba();
        creator.save(platba2);
        Platba platba3 =new Platba();
        creator.save(platba3);
        List<Platba> all = platbaRepository.findAll();
        Assertions.assertTrue(all.size() == 3);
    }


    @Test
    void findPlatabaViaPrevod() {
        Platba platba =new Platba();
        creator.save(platba);
        Platba platba2 =new Platba(prevod: 15.0);
        creator.save(platba2);
        Platba platba3 =new Platba();
        creator.save(platba3);
        Platba result = platbaRepository.findByPrevod(15.0);
        Assertions.assertTrue(result.getPrevod() == 15.0);
    }


    @Test
    void removePlatabaViaPrevod() {
        Platba platba =new Platba();
        creator.save(platba);
        Platba platba2 =new Platba(prevod:  15.0);
        creator.save(platba2);
        Platba platba3 =new Platba();
        creator.save(platba3);
        Platba result = platbaRepository.findByPrevod(15.0);
        platbaRepository.removePlatbaById(result.getId());
        Assertions.assertTrue(platbaRepository.findAll().size() == 2);
    }

}
