package com.app.repository.Maestro.Catalogo;
import com.app.model.Maestro.Catalogo.Familia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface FamiliaRepository extends JpaRepository<Familia, Long> {

}
