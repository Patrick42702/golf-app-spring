package dev.golf_app.repository;

import dev.golf_app.models.Round;
import org.springframework.data.repository.CrudRepository;

public interface RoundRepository extends CrudRepository<Round, Integer> {
}
