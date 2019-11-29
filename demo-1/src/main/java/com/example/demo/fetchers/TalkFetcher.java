package com.example.demo.fetchers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.services.TalkService;

import graphql.schema.DataFetcher;

@Component
public class TalkFetcher {
	
	@Autowired
    private TalkService talkService;

    public DataFetcher fetchAll() {
        return d ->  talkService.findAll();
    }

}
