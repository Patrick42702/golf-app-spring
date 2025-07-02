package dev.golf_app.controllers;

import dev.golf_app.dto.GolfCourseDTO;
import dev.golf_app.service.GolfCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/golf-courses")
public class GolfCourseController {

  @Autowired
  private GolfCourseService golfCourseService;

  @GetMapping
  public ResponseEntity<Page<GolfCourseDTO>> getAllCourses(
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "20") int size) {

      return ResponseEntity.ok(golfCourseService.getAllCourses(page, size));
    }

  @GetMapping("/closest-courses")
  public ResponseEntity<Page<GolfCourseDTO>> getClosestCourses(
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "5") int size,
    @RequestParam() double latitude,
    @RequestParam() double longitude) {

    return ResponseEntity.ok(golfCourseService.getClosestCourses(latitude, longitude, page, size));
  }

  @GetMapping("/state")
  public ResponseEntity<Page<GolfCourseDTO>> getStateAbbrCourses(
    @RequestParam(defaultValue = "NY") String abbr,
    @RequestParam(defaultValue = "0") int page,
    @RequestParam(defaultValue = "10") int size){

    return ResponseEntity.ok(golfCourseService.getStateAbbr(abbr, page, size));
  }

}
