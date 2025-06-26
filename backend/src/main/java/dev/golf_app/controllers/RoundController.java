package dev.golf_app.controllers;

import dev.golf_app.repository.RoundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/round")
public class RoundController {

  @Autowired
  private RoundRepository roundRepository;



}
