package cz.vlastni.eshop;

import cz.vlastni.eshop.dataFactory.PlatbaTestDataFactory;
import cz.vlastni.eshop.entity.Platba;
import cz.vlastni.eshop.repository.PlatbaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
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
@Import(PlatbaTestDataFactory.class)
class PlatbaTests {

    @Autowired
    private PlatbaRepository platbaRepository;

    @Autowired
    private PlatbaTestDataFactory platbaTestDataFactory;

    @Test
    void savePlatba() {
        platbaTestDataFactory.addPlatba1();
        List<Platba> all = platbaRepository.findAll();
        Assertions.assertTrue(all.size() == 1);
    }


    @Test
    void saveMorePlatba() {
        platbaTestDataFactory.addPlatba1();
        platbaTestDataFactory.addPlatba2();
        platbaTestDataFactory.addPlatba3();
        List<Platba> all = platbaRepository.findAll();
        Assertions.assertTrue(all.size() == 3);
    }


    @Test
    void findPlatabaViaPrevod() {
        platbaTestDataFactory.addPlatba1();
        platbaTestDataFactory.addPlatba2();
        platbaTestDataFactory.addPlatba3();
        Platba result = platbaRepository.findByPrevod(15.0);
        Assertions.assertTrue(result.getPrevod() == 15.0);
    }


    @Test
    void removePlatabaViaPrevod() {
        platbaTestDataFactory.addPlatba1();
        platbaTestDataFactory.addPlatba2();
        platbaTestDataFactory.addPlatba3();
        Platba result = platbaRepository.findByPrevod(15.0);
        platbaRepository.removePlatbaById(result.getId());
        Assertions.assertTrue(platbaRepository.findAll().size() == 2);
    }

}
