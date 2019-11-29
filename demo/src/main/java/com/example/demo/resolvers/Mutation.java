package com.example.demo.resolvers;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.example.demo.databasemodels.Speaker;
import com.example.demo.pojos.SpeakerInput;
import com.example.demo.services.SpeakerService;

import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Mutation implements GraphQLMutationResolver{
	
	private final SpeakerService speakerService;
	 public Speaker addSpeaker(SpeakerInput speakerInput, DataFetchingEnvironment dfEnvt) {
		 Map<String, Object> map = dfEnvt.getArguments();
		 Set keys = map.keySet();
		 List<String> list = (List<String>)keys.stream().collect(Collectors.toList());
		 String s = list.get(0);
		 int k = keys.size();
		 Collection<Object> values = map.values();
		 Speaker speaker = new Speaker();
		 speaker.setName(speakerInput.getName());
		 speaker.setTwitter(speakerInput.getTwitter());
		 return speakerService.save(speaker);
	 }

}
