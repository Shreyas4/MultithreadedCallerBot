package com.orum.sundial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
public class SundialApplication {

    public static final Queue<String> PHONE_NUMBERS_QUEUE = new LinkedList<>();

    public static final TreeMap<String, String> PHONE_NO_STATUS_MAP = new TreeMap<>();

    public static final TreeMap<String, String> ID_PHONE_NO_MAP = new TreeMap<>();

    public static void main(String[] args) {
        SpringApplication.run(SundialApplication.class, args);
    }

}
