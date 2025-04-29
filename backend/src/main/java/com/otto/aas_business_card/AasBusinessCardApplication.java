package com.otto.aas_business_card;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class AasBusinessCardApplication {
  public static void main(String[] args) {
    SpringApplication.run(AasBusinessCardApplication.class, args);
  }
}
