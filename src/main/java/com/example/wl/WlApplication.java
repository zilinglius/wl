package com.example.wl;

import com.example.wl.datamodel.dao.Library;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WlApplication {

    public static void main(String[] args) {

        Library.initialize();

        SpringApplication.run(WlApplication.class, args);
    }
}
