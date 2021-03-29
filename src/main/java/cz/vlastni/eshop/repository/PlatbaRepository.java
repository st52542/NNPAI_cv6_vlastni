package cz.vlastni.eshop.repository;

import cz.vlastni.eshop.entity.Platba;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlatbaRepository extends JpaRepository<Platba,Long> {

    Optional<Platba> findById(Long id);

    Platba findByPopis(String popis);

    Platba findByPrevod(Double prevod);

    void removePlatbaById(Long id);
}
