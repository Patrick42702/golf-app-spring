package dev.golf_app.controllers;

import dev.golf_app.dto.RoundRequestDTO;
import dev.golf_app.models.Round;
import dev.golf_app.service.RoundService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(path = "/api/round")
public class RoundController {
  private final Logger logger = LoggerFactory.getLogger(RoundController.class);
  @Autowired
  private RoundService roundService;

  @GetMapping
  public ResponseEntity<Page<Round>> getRounds(@RequestParam int page,
                               @RequestParam int size){
    Page<Round> rounds = roundService.getRounds(page, size);
    return ResponseEntity.ok(rounds);
  }

  @PostMapping("/create")
  public ResponseEntity<Round> createRound(@RequestBody RoundRequestDTO roundRequestDTO) {
    Round round = roundService.createRound(roundRequestDTO);

    URI location = ServletUriComponentsBuilder
      .fromCurrentRequest()
      .path("/{id}")
      .buildAndExpand(round.getId())
      .toUri();

    return ResponseEntity.created(location).body(round);
  }

  @GetMapping("/user")
  public ResponseEntity<List<RoundRequestDTO>> getUserRound(@RequestParam int id){
    List<RoundRequestDTO> round = roundService.getUserRound(id);
    return ResponseEntity.ok(round);
  }

}
