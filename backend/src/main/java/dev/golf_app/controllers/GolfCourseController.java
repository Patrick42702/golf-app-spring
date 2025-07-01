package dev.golf_app.controllers;

import dev.golf_app.dto.GolfCourseDTO;
import dev.golf_app.repository.GolfCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/golf-courses")
public class GolfCourseController {

  @Autowired
  private GolfCourseRepository golfCourseRepository;

  @GetMapping
  public ResponseEntity<Page<GolfCourseDTO>> getAllCourses(
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "20") int size) {

      // Enforce maximum page size
      size = Math.min(size, 50);
      Page<GolfCourseDTO> courses = golfCourseRepository.findAllAsDto(PageRequest.of(page, size));
      return ResponseEntity.ok(courses);
    }

  @GetMapping("/closest-courses")
  public ResponseEntity<Page<GolfCourseDTO>> getClosestCourses(
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "5") int size,
    @RequestParam() double latitude,
    @RequestParam() double longitude) {

    size = Math.min(size, 5);
    Page<GolfCourseDTO> courses = golfCourseRepository.findNearestCourses(
      latitude,
      longitude,
      PageRequest.of(page, size));
    return ResponseEntity.ok(courses);
  }

  @GetMapping("/state")
  public ResponseEntity<Page<GolfCourseDTO>> getStateAbbrCourses(
    @RequestParam(defaultValue = "NY") String abbr,
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "10") int size){

    Page<GolfCourseDTO> courses = golfCourseRepository.findByStateAbbr(abbr, PageRequest.of(page, size));
    return ResponseEntity.ok(courses);
  }

}
