package com.pluralsight.example.conferencedemo.controllers;


import com.pluralsight.example.conferencedemo.models.Session;
import com.pluralsight.example.conferencedemo.repositories.SessionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/sessions")
public class SessionsController {

    @Autowired
    private SessionRepository sessionRepository;

    //tell which HTTP verb to use
    @GetMapping
    public List<Session> list() {
        //JPA build this method to query all the data to a list of Session objects
        //Spring MVC will pass that over to Jackson(serialization library)
        //which will turn those sessions into JSON and return back to the caller
        var m = sessionRepository.findAll();
        var n = new ArrayList<Session>();
        n.add(new Session());
        return m;
    }

    @GetMapping
    //add additional id to the URL from the original URL
    @RequestMapping("{id}")
    public Session get(@PathVariable Long id) {
        var m =sessionRepository.getOne(id);
        return m;
    }

    @PostMapping
    public Session create(@RequestBody final Session session) {
        //save -> save to db, flush -> commit db
        return sessionRepository.saveAndFlush(session);
    }

    //"method = RequestMethod.DELETE give the same endpoint of get session by id
    // so need to pass in a HTTP verb to distinguish
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        //Also need to check for children records before deleting
        sessionRepository.deleteById(id);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public Session update(@PathVariable Long id, @RequestBody Session session) {
        //because this is a PUT, we expect all attributes to be passed in
        //TODO: Add validation that all attributes are passed in, otherwise return a 400 bad payload
        Session existingSession = sessionRepository.getReferenceById(id);
        BeanUtils.copyProperties(session, existingSession, "session_id");
        return sessionRepository.saveAndFlush(existingSession);
    }

}
