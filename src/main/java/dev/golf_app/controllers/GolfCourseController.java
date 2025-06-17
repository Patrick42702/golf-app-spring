package dev.golf_app.controllers;

import dev.golf_app.models.GolfCourse;
import dev.golf_app.repository.GolfCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/golf-courses")
public class GolfCourseController {

  @Autowired
  private GolfCourseRepository golfCourseRepository;

  @GetMapping
  public List<GolfCourse> getAllCourses() {
    return golfCourseRepository.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<GolfCourse> getCourse(@PathVariable Integer id) {
    return golfCourseRepository.findById(id)
      .map(ResponseEntity::ok)
      .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public GolfCourse createCourse(@RequestBody GolfCourse course) {
    return golfCourseRepository.save(course);
  }

  @PutMapping("/{id}")
  public ResponseEntity<GolfCourse> updateCourse(@PathVariable Integer id, @RequestBody GolfCourse updated) {
    return golfCourseRepository.findById(id).map(course -> {
      course.setName(updated.getName());
      course.setAddress(updated.getAddress());
      course.setZip(updated.getZip());
      return ResponseEntity.ok(golfCourseRepository.save(course));
    }).orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCourse(@PathVariable Integer id) {
    if (!golfCourseRepository.existsById(id)) return ResponseEntity.notFound().build();
    golfCourseRepository.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
