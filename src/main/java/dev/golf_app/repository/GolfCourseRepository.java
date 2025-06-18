package dev.golf_app.repository;

import dev.golf_app.models.GolfCourse;
import dev.golf_app.models.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GolfCourseRepository extends CrudRepository<GolfCourse, Integer> {
}
