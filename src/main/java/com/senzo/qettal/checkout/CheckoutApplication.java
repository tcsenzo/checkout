package com.senzo.qettal.checkout;

import java.util.UUID;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class CheckoutApplication {

    public static void main(String[] args) {
    	System.out.println(UUID.randomUUID().toString());
        SpringApplication.run(CheckoutApplication.class, args);
    }
}
