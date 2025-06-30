package dev.golf_app.repository;

import dev.golf_app.dto.GolfCourseDTO;
import dev.golf_app.models.GolfCourse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GolfCourseRepository extends JpaRepository<GolfCourse, Integer>, QuerydslPredicateExecutor<GolfCourse> {

  @Query(value = """
        SELECT *, (6371 * ACOS(
            COS(RADIANS(:latitude)) *
            COS(RADIANS(latitude)) *
            COS(RADIANS(longitude) - RADIANS(:longitude)) +
            SIN(RADIANS(:latitude)) *
            SIN(RADIANS(latitude))
        )) AS distance
        FROM golf_courses
        ORDER BY distance ASC
        """,
    nativeQuery = true)
  Page<GolfCourseDTO> findNearestCourses(@Param("latitude") double latitude,
                                         @Param("longitude") double longitude,
                                         Pageable pageable);

  @Query("SELECT gc FROM GolfCourse gc WHERE gc.state_abbr = :stateAbbr ORDER BY gc.name ASC")
  Page<GolfCourseDTO> findByStateAbbr(@Param("stateAbbr") String stateAbbr,
                                   Pageable pageable);

  Iterable<GolfCourseDTO> findAllByName(String name);

  Iterable<GolfCourseDTO> findAllByZipOrderByName(String zip);

  @Query("""
    SELECT new dev.golf_app.dto.GolfCourseDTO(
        g.id,
        g.name,
        g.address,
        g.zip,
        g.longitude,
        g.latitude,
        g.classification,
        g.state_abbr,
        g.city,
        g.phone
    )
    FROM GolfCourse g
    """)
  Page<GolfCourseDTO> findAllAsDto(Pageable pageable);
}
