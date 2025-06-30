package dev.golf_app.repository;

import dev.golf_app.models.Round;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoundRepository extends JpaRepository<Round, Integer> {

  public Optional<Round> findById(Integer id);

}
