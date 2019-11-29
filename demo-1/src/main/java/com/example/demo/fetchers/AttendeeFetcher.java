package com.example.demo.fetchers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.services.AttendeeService;

import graphql.schema.DataFetcher;

@Component
public class AttendeeFetcher {
	@Autowired
    AttendeeService attendeeService;

    public DataFetcher fetchAll() {
        return d -> attendeeService.findAll();
    }

}
