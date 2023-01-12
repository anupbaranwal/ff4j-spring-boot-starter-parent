package org.ff4j.sample.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class Greeting {

  @GetMapping()
  public String greeting() {
    return "<ul>" +
            "<li><a href=\"/swagger-ui/index.html\">Swagger</a>" +
            "<li><a href=\"/api/v1/ff4j\">Web Api</a>" +
            "<li><a href=\"/ff4j-web\">Web Console (admin/admin)</a>";
  }

}