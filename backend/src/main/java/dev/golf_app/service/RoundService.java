package dev.golf_app.service;

import dev.golf_app.dto.RoundRequestDTO;
import dev.golf_app.models.GolfCourse;
import dev.golf_app.models.Round;
import dev.golf_app.models.Users;
import dev.golf_app.repository.GolfCourseRepository;
import dev.golf_app.repository.RoundRepository;
import dev.golf_app.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoundService {
  @Autowired
  private RoundRepository roundRepository;
  @Autowired
  private GolfCourseRepository golfCourseRepository;
  @Autowired
  private UserRepository userRepository;

  @Transactional
  public Round createRound(RoundRequestDTO roundRequestDTO){
    if (roundRequestDTO == null){
      throw new IllegalArgumentException("RoundRequestDTO cannot be null");
    }
    GolfCourse golfCourse = golfCourseRepository.findById(roundRequestDTO.getGolfCourseId())
      .orElseThrow(() -> new EntityNotFoundException(
        "Golf course not found with id: " + roundRequestDTO.getGolfCourseId()));
    Users user = userRepository.findById(roundRequestDTO.getUserId())
      .orElseThrow(() -> new EntityNotFoundException(
        "User not found with id: " + roundRequestDTO.getUserId()));
    Round round = new Round();
    round.setHoles(roundRequestDTO.getHoles());
    round.setDate(roundRequestDTO.getDate());
    round.setGolfCourse(golfCourse);
    round.setTotalPar(roundRequestDTO.getTotalPar());
    round.setUser(user);
    return roundRepository.save(round);
  }

  @Transactional(readOnly = true)
  public Page<Round> getRounds(int page, int size){
    return roundRepository.findAll(PageRequest.of(page, size));
  }

  @Transactional(readOnly = true)
  public List<RoundRequestDTO> getUserRound(int id){
    return roundRepository.findByUserId(id);
  }

}
