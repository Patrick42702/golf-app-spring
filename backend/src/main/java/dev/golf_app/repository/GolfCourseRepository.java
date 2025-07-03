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
    SELECT id, name, address, zip, longitude, latitude,
           classification, state_abbr, city, phone
    FROM golf_courses
    WHERE latitude IS NOT NULL AND longitude IS NOT NULL
    ORDER BY (
        6371 * ACOS(
            COS(RADIANS(:latitude)) *
            COS(RADIANS(latitude)) *
            COS(RADIANS(longitude) - RADIANS(:longitude)) +
            SIN(RADIANS(:latitude)) *
            SIN(RADIANS(latitude))
        )
    ) ASC
    """,
    countQuery = "SELECT count(*) FROM golf_courses",
    nativeQuery = true)
  Page<GolfCourseDTO> findNearestCourses(@Param("latitude") double latitude,
                                         @Param("longitude") double longitude,
                                         Pageable pageable);

  @Query("SELECT gc FROM GolfCourse gc WHERE gc.state_abbr = :stateAbbr ORDER BY gc.name ASC")
  Page<GolfCourseDTO> findByStateAbbr(@Param("stateAbbr") String stateAbbr,
                                   Pageable pageable);

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
    WHERE g.name = :name
    """)
  Iterable<GolfCourseDTO> findAllByName(String name);

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
    WHERE g.zip = :zip
    ORDER BY g.name
    """)
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
