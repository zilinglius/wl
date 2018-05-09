package com.example.wl.datamodel.dao;

import com.example.wl.datamodel.domainmodel.Book;
import com.example.wl.datamodel.domainmodel.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashMap;

public class Library {
    public static HashMap<Integer, Book> lib = new HashMap<Integer, Book>();
    public static HashMap<String, User> users = new HashMap<String, User>();

    public static void initialize(){

        lib.put(1, new Book(1, "book one", "Tom"));
        lib.put(2, new Book(2, "book two", "Tom"));

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        users.put("Tom", new User("Tom", encoder.encode("tom"), "ROLE_ADMIN"));
        users.put("Jack", new User("Jack", encoder.encode("jack"), "ROLE_USER"));
    }
}
