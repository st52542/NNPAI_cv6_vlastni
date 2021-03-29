package cz.vlastni.eshop;

import cz.vlastni.eshop.entity.Platba;
import cz.vlastni.eshop.repository.PlatbaRepository;
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
class PlatbaTests {

    @Autowired
    private PlatbaRepository platbaRepository;

    @Test
    void savePlatba() {
        Platba pay = new Platba();
        pay.setPrevod(5.0);
        platbaRepository.save(pay);
        List<Platba> all = platbaRepository.findAll();
        Assertions.assertTrue(all.size()==1);
    }
    @Test
    void saveMorePlatba() {
        Platba pay = new Platba();
        pay.setPrevod(5.0);
        platbaRepository.save(pay);
        Platba pay2 = new Platba();
        pay2.setPrevod(10.5);
        pay2.setPopis("ssdd");
        platbaRepository.save(pay2);
        Platba pay3 = new Platba();
        pay3.setPrevod(15.0);
        pay3.setPopis("ffdd");
        platbaRepository.save(pay3);
        List<Platba> all = platbaRepository.findAll();
        Assertions.assertTrue(all.size()==3);
    }
    @Test
    void findPlatabaViaPrevod() {
        Platba pay = new Platba();
        pay.setPrevod(5.0);
        platbaRepository.save(pay);
        Platba pay2 = new Platba();
        pay2.setPrevod(10.5);
        pay2.setPopis("ssdd");
        platbaRepository.save(pay2);
        Platba pay3 = new Platba();
        pay3.setPrevod(15.0);
        pay3.setPopis("ffdd");
        platbaRepository.save(pay3);
        Platba result = platbaRepository.findByPrevod(15.0);
        Assertions.assertTrue(result.getPrevod()==15.0);
    }
    @Test
    void removePlatabaViaPrevod() {
        Platba pay = new Platba();
        pay.setPrevod(5.0);
        platbaRepository.save(pay);
        Platba pay2 = new Platba();
        pay2.setPrevod(10.5);
        pay2.setPopis("ssdd");
        platbaRepository.save(pay2);
        Platba pay3 = new Platba();
        pay3.setPrevod(15.0);
        pay3.setPopis("ffdd");
        platbaRepository.save(pay3);
        Platba result = platbaRepository.findByPrevod(15.0);
        platbaRepository.removePlatbaById(result.getId());
        Assertions.assertTrue(platbaRepository.findAll().size()==2);
    }

}
