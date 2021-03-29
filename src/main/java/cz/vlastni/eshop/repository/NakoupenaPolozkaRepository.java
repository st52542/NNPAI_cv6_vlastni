package cz.vlastni.eshop.repository;

import cz.vlastni.eshop.entity.NakoupenaPolozka;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NakoupenaPolozkaRepository extends JpaRepository<NakoupenaPolozka,Long> {

    @EntityGraph(attributePaths = "Nakup")
    Optional<NakoupenaPolozka> findById(Long id);

    NakoupenaPolozka findByMnozstvi(Integer mnozstvi);

    void removeNakoupenaPolozkaById(Long id);
}
