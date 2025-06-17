package dev.golf_app.repository;

import dev.golf_app.models.Score;
import org.springframework.data.repository.CrudRepository;

public interface ScoreRepository extends CrudRepository<Score, Integer> {
}
