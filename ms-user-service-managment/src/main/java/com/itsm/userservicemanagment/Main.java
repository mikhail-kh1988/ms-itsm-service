package com.itsm.userservicemanagment;



import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class Main {
    public static void main(String[] args) {

        org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Main.class);

        SpringApplication.run(Main.class, args);

    }
}