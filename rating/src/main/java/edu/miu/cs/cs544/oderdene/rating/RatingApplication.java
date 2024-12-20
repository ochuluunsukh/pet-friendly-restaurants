package edu.miu.cs.cs544.oderdene.rating;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class RatingApplication {

    public static void main(String[] args) {
        SpringApplication.run(RatingApplication.class, args);
    }

}
