package dev.golf_app.repository;

import dev.golf_app.models.GolfCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

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
        LIMIT 10""",
    nativeQuery = true)
  ArrayList<GolfCourse> findNearestCourses(@Param("latitude") double latitude,
                                           @Param("longitude") double longitude);

  Iterable<GolfCourse> findAllByName(String name);

  Iterable<GolfCourse> findAllByZipOrderByName(String zip);
}
