package dev.golf_app.controllers;

import dev.golf_app.dto.RoundRequestDTO;
import dev.golf_app.models.GolfCourse;
import dev.golf_app.models.Round;
import dev.golf_app.repository.GolfCourseRepository;
import dev.golf_app.repository.RoundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(path = "/api/round")
public class RoundController {

  @Autowired
  private RoundRepository roundRepository;
  @Autowired
  private GolfCourseRepository golfCourseRepository;

  @GetMapping
  public Page<Round> getRounds(@RequestParam int page,
                               @RequestParam int size){
    return roundRepository.findAll(PageRequest.of(page, size));
  }

  @PostMapping("/create")
  public ResponseEntity<Round> createRound(@RequestBody RoundRequestDTO roundRequestDTO) {
    GolfCourse golfCourse = golfCourseRepository.findById(roundRequestDTO.getGolfCourseId()).get();

    Round savedRound = new Round().toBuilder()
      .holes(roundRequestDTO.getHoles())
      .totalPar(roundRequestDTO.getTotalPar())
      .golfCourse(golfCourse)
      .date(roundRequestDTO.getDate())
      .build();
    roundRepository.save(savedRound);

    URI location = ServletUriComponentsBuilder
      .fromCurrentRequest()
      .path("/{id}")
      .buildAndExpand(savedRound.getId())
      .toUri();

    return ResponseEntity.created(location).body(savedRound);
  }

}
