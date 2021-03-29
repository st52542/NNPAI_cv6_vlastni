package cz.vlastni.eshop.dataFactory;

import cz.vlastni.eshop.entity.Uzivatel;
import cz.vlastni.eshop.repository.DopravaRepository;
import cz.vlastni.eshop.repository.UzivatelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Component;

@Component
public class UzivatelTestDataFactory {

    @Autowired
    private UzivatelRepository uzivatelRepository;

    public Uzivatel addUser1() {
        Uzivatel user = new Uzivatel();
        user.setJmeno("abc");
        user.setPrijmeni("cba");
        user.setEmail("aa@bbb.cz");
        user.setHeslo("pass");
        user.setAdresa("konecna 123");
        user.setAdmin(true);
        uzivatelRepository.save(user);
        return user;
    }

    public Uzivatel addUser2() {
        Uzivatel userS = new Uzivatel();
        userS.setJmeno("abcd");
        userS.setPrijmeni("cba");
        userS.setEmail("vv@bbb.cz");
        userS.setHeslo("pass");
        userS.setAdresa("konecna 123");
        userS.setAdmin(true);
        uzivatelRepository.save(userS);
        return userS;
    }
}
