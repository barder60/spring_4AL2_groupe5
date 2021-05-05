package com.gotta_watch_them_all.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/")
public class HealthCheckController {

  @GetMapping
  public String healthCheck() {
    return "Pour verifier";
  }
}
