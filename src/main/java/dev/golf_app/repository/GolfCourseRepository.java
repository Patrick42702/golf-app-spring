package dev.golf_app.repository;
import dev.golf_app.models.GolfCourse;
import dev.golf_app.models.Users;
import org.springframework.data.repository.CrudRepository;

public interface GolfCourseRepository extends CrudRepository<GolfCourse, Integer> {

}
