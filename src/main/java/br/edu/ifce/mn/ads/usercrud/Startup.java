package br.edu.ifce.mn.ads.usercrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.resilience.annotation.EnableResilientMethods;

@SpringBootApplication
@EnableResilientMethods
public class Startup {

    static void main(String[] args) {
        SpringApplication.run(Startup.class, args);
    }

}
