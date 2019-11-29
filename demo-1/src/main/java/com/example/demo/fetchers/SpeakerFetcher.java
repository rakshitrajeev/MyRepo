package com.example.demo.fetchers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.databasemodels.Speaker;
import com.example.demo.databasemodels.Talk;
import com.example.demo.services.SpeakerService;

import graphql.schema.DataFetcher;

@Component
public class SpeakerFetcher {
	
	@Autowired
	private SpeakerService speakerService;
	
	public DataFetcher fetchAll() {
		return d -> speakerService.findAll();
	}
	
	public DataFetcher fetchAllSpeakersForTalk(){
		
		return d -> {
			Talk talk = d.getSource();
			return speakerService.findAllSpeakersForTalk(talk);
		};
		
	}

}
