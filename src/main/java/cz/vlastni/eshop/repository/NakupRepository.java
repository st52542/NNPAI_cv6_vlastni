package cz.vlastni.eshop.repository;

import cz.vlastni.eshop.entity.Nakup;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NakupRepository extends JpaRepository<Nakup,Long> {

    Optional<Nakup> findById(Long id);

    Nakup getNakupById(Long id);

    Nakup findByObjednavka(Integer objednavka);

    void removeNakupById(Long id);
}
