package cz.vlastni.eshop.repository;

import cz.vlastni.eshop.entity.Doprava;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DopravaRepository extends JpaRepository<Doprava,Long> {

    Optional<Doprava> findById(Long id);

    Doprava findByPopis(String popis);

    Doprava findByCena(Integer cena);

    void removeDopravaById(Long id);
}
