package cz.vlastni.eshop

import cz.vlastni.eshop.dataFactory.Creator;
import cz.vlastni.eshop.dataFactory.DopravaTestDataFactory;
import cz.vlastni.eshop.dataFactory.UzivatelTestDataFactory;
import cz.vlastni.eshop.entity.Uzivatel;
import cz.vlastni.eshop.repository.UzivatelRepository;
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
class UzivatelGroovyTests {

    @Autowired
    private UzivatelRepository uzivatelRepository;

    @Autowired
    private Creator creator;

    @Test
    void saveUzivatel() {
        Uzivatel uzivatel = new Uzivatel();
        creator.save(uzivatel);
        List<Uzivatel> all = uzivatelRepository.findAll();
        Assertions.assertTrue(all.size() == 1);
    }

    @Test
    void saveMoreUzivatel() {
        Uzivatel uzivatel = new Uzivatel();
        creator.save(uzivatel);
        Uzivatel uzivatel2 = new Uzivatel();
        creator.save(uzivatel2);
        Uzivatel uzivatel3 = new Uzivatel();
        creator.save(uzivatel3);
        List<Uzivatel> all = uzivatelRepository.findAll();
        Assertions.assertTrue(all.size() == 3);
    }


    @Test
    void findUzivatelViaMail() {
        Uzivatel uzivatel = new Uzivatel();
        creator.save(uzivatel);
        Uzivatel uzivatel2 = new Uzivatel(email: "vv@bbb.cz", jmeno: "abcd");
        creator.save(uzivatel2);
        Uzivatel uzivatel3 = new Uzivatel();
        creator.save(uzivatel3);
        Uzivatel result = uzivatelRepository.findByEmail("vv@bbb.cz");
        Assertions.assertTrue(result.getJmeno() == "abcd");
    }

    @Test
    void removeUzivatel() {
        Uzivatel uzivatel = new Uzivatel();
        creator.save(uzivatel);
        Uzivatel uzivatel2 = new Uzivatel(email: "vv@bbb.cz");
        creator.save(uzivatel2);
        Uzivatel uzivatel3 = new Uzivatel();
        creator.save(uzivatel3);
        Uzivatel result = uzivatelRepository.findByEmail("vv@bbb.cz");
        uzivatelRepository.removeUzivatelById(result.getId());
        Assertions.assertTrue(uzivatelRepository.findAll().size() == 2);
    }

}
