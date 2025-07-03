package dev.golf_app.repository;

import dev.golf_app.dto.RoundRequestDTO;
import dev.golf_app.models.Round;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoundRepository extends JpaRepository<Round, Integer> {

  public Optional<Round> findById(@Param("id") int id);

  @Query(value = """
        SELECT new dev.golf_app.dto.RoundRequestDTO(
          r.date,
          r.holes,
          r.totalPar,
          r.golfCourse.id,
          r.user.id
        )
        FROM Round r
        WHERE r.user.id = :id
    """
  )
  public List<RoundRequestDTO> findByUserId(@Param("id") int id);

}
