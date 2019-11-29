package com.example.demo.resolvers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.example.demo.databasemodels.Speaker;
import com.example.demo.databasemodels.Talk;
import com.example.demo.services.SpeakerService;

import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TalkResolver implements GraphQLResolver<Talk>{

	@Autowired
	private final SpeakerService speakerService;
	
	public List<Speaker> speakers(Talk talk, DataFetchingEnvironment dfEnvt){
		Talk talkSource = dfEnvt.getSource();
		Map<String, Object> map = dfEnvt.getArguments();
		int mapSize = map.size();
		return speakerService.findAllSpeakersForTalk(talk);
	}
}
