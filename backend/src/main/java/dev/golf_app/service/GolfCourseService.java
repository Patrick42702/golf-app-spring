package dev.golf_app.service;

import dev.golf_app.dto.GolfCourseDTO;
import dev.golf_app.repository.GolfCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GolfCourseService {

  @Autowired
  private GolfCourseRepository golfCourseRepository;

  @Transactional(readOnly = true)
  public Page<GolfCourseDTO> getAllCourses(int page, int size){
    return golfCourseRepository.findAllAsDto(PageRequest.of(page, size));
  }

  @Transactional(readOnly = true)
  public Page<GolfCourseDTO> getClosestCourses(double latitude, double longitude, int page, int size){
    return golfCourseRepository.findNearestCourses(latitude, longitude, PageRequest.of(page, size));
  }

  @Transactional(readOnly = true)
  public Page<GolfCourseDTO> getStateAbbr(String abbr, int page, int size){
    return golfCourseRepository.findByStateAbbr(abbr, PageRequest.of(page, size));
  }

}
