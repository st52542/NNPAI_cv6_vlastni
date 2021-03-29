package cz.vlastni.eshop;

import cz.vlastni.eshop.entity.Uzivatel;
import cz.vlastni.eshop.repository.UzivatelRepository;
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
class UzivatelTests {

    @Autowired
    private UzivatelRepository uzivatelRepository;

    @Test
    void saveUzivatel() {
        Uzivatel user = new Uzivatel();
        user.setJmeno("abc");
        user.setPrijmeni("cba");
        user.setEmail("aa@bbb.cz");
        user.setHeslo("pass");
        user.setAdresa("konecna 123");
        user.setAdmin(true);
        uzivatelRepository.save(user);
        List<Uzivatel> all = uzivatelRepository.findAll();
        Assertions.assertTrue(all.size() == 1);
    }

    @Test
    void saveMoreUzivatel() {
        Uzivatel user = new Uzivatel();
        user.setJmeno("abc");
        user.setPrijmeni("cba");
        user.setEmail("aa@bb.cz");
        user.setHeslo("pass");
        user.setAdresa("konecna 123");
        user.setAdmin(true);
        uzivatelRepository.save(user);
        Uzivatel user1 = new Uzivatel();
        user1.setJmeno("abc");
        user1.setPrijmeni("cba");
        user1.setEmail("aa@bcb.cz");
        user1.setHeslo("pass");
        user1.setAdresa("konecna 123");
        user1.setAdmin(true);
        uzivatelRepository.save(user1);
        Uzivatel user2 = new Uzivatel();
        user2.setJmeno("abc");
        user2.setPrijmeni("cba");
        user2.setEmail("aa@bbb.cz");
        user2.setHeslo("pass");
        user2.setAdresa("konecna 123");
        user2.setAdmin(true);
        uzivatelRepository.save(user2);
        List<Uzivatel> all = uzivatelRepository.findAll();
        Assertions.assertTrue(all.size() == 3);
    }

    @Test
    void findUzivatelViaMail() {
        Uzivatel user = new Uzivatel();
        user.setJmeno("abc");
        user.setPrijmeni("cba");
        user.setEmail("aa@bab.cz");
        user.setHeslo("pass");
        user.setAdresa("konecna 123");
        user.setAdmin(true);
        uzivatelRepository.save(user);
        Uzivatel userS = new Uzivatel();
        userS.setJmeno("abcd");
        userS.setPrijmeni("cba");
        userS.setEmail("vv@bbb.cz");
        userS.setHeslo("pass");
        userS.setAdresa("konecna 123");
        userS.setAdmin(true);
        Uzivatel user2 = new Uzivatel();
        user2.setJmeno("abc");
        user2.setPrijmeni("cba");
        user2.setEmail("aa@blb.cz");
        user2.setHeslo("pass");
        user2.setAdresa("konecna 123");
        user2.setAdmin(true);
        uzivatelRepository.save(user2);
        uzivatelRepository.save(userS);
        Uzivatel result = uzivatelRepository.findByEmail("vv@bbb.cz");
        Assertions.assertTrue(result.getJmeno() == "abcd");
    }

    @Test
    void removeUzivatel() {
        Uzivatel user = new Uzivatel();
        user.setJmeno("abc");
        user.setPrijmeni("cba");
        user.setEmail("aa@beb.cz");
        user.setHeslo("pass");
        user.setAdresa("konecna 123");
        user.setAdmin(true);
        uzivatelRepository.save(user);
        Uzivatel userS = new Uzivatel();
        userS.setJmeno("abcd");
        userS.setPrijmeni("cba");
        userS.setEmail("vv@bbb.cz");
        userS.setHeslo("pass");
        userS.setAdresa("konecna 123");
        userS.setAdmin(true);
        Uzivatel user2 = new Uzivatel();
        user2.setJmeno("abc");
        user2.setPrijmeni("cba");
        user2.setEmail("aa@bbb.cz");
        user2.setHeslo("pass");
        user2.setAdresa("konecna 123");
        user2.setAdmin(true);
        uzivatelRepository.save(user2);
        uzivatelRepository.save(userS);
        Uzivatel result = uzivatelRepository.findByEmail("vv@bbb.cz");
        uzivatelRepository.removeUzivatelById(result.getId());
        Assertions.assertTrue(uzivatelRepository.findAll().size() == 2);
    }

}
