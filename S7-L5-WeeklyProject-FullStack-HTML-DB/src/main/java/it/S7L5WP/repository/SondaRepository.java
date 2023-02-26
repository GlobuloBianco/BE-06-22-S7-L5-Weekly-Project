package it.S7L5WP.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import it.S7L5WP.models.Sonda;

@Repository
public interface SondaRepository extends JpaRepository<Sonda, Integer> {
}
