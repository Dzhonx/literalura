package com.example.literalura.repository;

import com.example.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    List<Autor> findByAnioNacimientoLessThanEqualAndAnioFallecimientoGreaterThanEqual(Integer nacimiento, Integer fallecimiento);

    List<Autor> findByAnioNacimientoLessThanEqualAndAnioFallecimientoIsNull(Integer nacimiento);
}
