package com.example.wl.controller;

import com.example.wl.datamodel.dao.Library;
import com.example.wl.datamodel.domainmodel.Book;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@EnableAutoConfiguration
public class BookController {

    @RequestMapping(value = "/book/{id}", method = RequestMethod.GET)
    public Book getBook(@PathVariable int id){

        return Library.lib.get(id);
    }

    @RequestMapping(value = "/book/{id}", method = RequestMethod.POST, consumes = "Application/json")
    @PreAuthorize("hasRole('ROLE_ADMIN') or #book.getOwner() == authentication.name")
    public Book updateBook(@PathVariable int id, @RequestBody Book book){

        Library.lib.put(id, book);

        return Library.lib.get(id);
    }

    @RequestMapping(value = "/book", method = RequestMethod.PUT, consumes = "Application/json")
    public Book createBook(@RequestBody Book book, Principal principal) {

        book.setOwner(principal.getName());

        Library.lib.put(book.getId(), book);

        return Library.lib.get(book.getId());
    }

    @RequestMapping(value = "/book/{id}", method = RequestMethod.DELETE)
    public Book deleteBook(@PathVariable int id) {

        return Library.lib.remove(id);
    }
}
