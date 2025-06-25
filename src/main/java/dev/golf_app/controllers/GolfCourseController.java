package dev.golf_app.controllers;

import dev.golf_app.models.GolfCourse;
import dev.golf_app.repository.GolfCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/golf-courses")
public class GolfCourseController {

  @Autowired
  private GolfCourseRepository golfCourseRepository;

  @GetMapping
  public Page<GolfCourse> getAllCourses(
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "20") int size) {

      // Enforce maximum page size
      size = Math.min(size, 50);
      return golfCourseRepository.findAll(PageRequest.of(page, size));
    }

  @GetMapping("/closest-courses")
  public ArrayList<GolfCourse> getClosestCourses(
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "5") int size,
    @RequestParam() double latitude,
    @RequestParam() double longitude) {

    size = Math.min(size, 5);
    return golfCourseRepository.findNearestCourses(latitude, longitude);
  }

}
