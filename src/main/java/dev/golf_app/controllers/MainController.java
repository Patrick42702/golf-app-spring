package dev.golf_app.controllers;

import dev.golf_app.repository.UserRepository;
import dev.golf_app.models.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/demo")
public class MainController {
  private static final Logger log = LoggerFactory.getLogger(MainController.class);
  @Autowired
  private UserRepository userRepository;

  @PostMapping(path="/add")
  public @ResponseBody String addNewUser (@RequestParam String name, @RequestParam String email){
    Users n = new Users();
    log.atDebug().log(name);
    n.setName(name);
    n.setEmail(email);
    userRepository.save(n);
    return "Saved\n";
  }

  @GetMapping(path = "/all")
  public @ResponseBody Iterable<Users> getAllUsers(){
    return userRepository.findAll();
  }

}
