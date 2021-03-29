package cz.vlastni.eshop.repository;

import cz.vlastni.eshop.entity.Uzivatel;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UzivatelRepository extends JpaRepository<Uzivatel,Long> {

    Optional<Uzivatel> findById(Long id);

    Uzivatel findByJmeno(String jmeno);

    Uzivatel findByEmail(String email);

    void removeUzivatelById(Long id);
}
