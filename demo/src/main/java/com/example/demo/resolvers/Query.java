package com.example.demo.resolvers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.demo.databasemodels.Attendee;
import com.example.demo.databasemodels.Speaker;
import com.example.demo.databasemodels.Talk;
import com.example.demo.services.AttendeeService;
import com.example.demo.services.SpeakerService;
import com.example.demo.services.TalkService;

import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Query implements GraphQLQueryResolver{

    private final TalkService talkService;
    
    private final AttendeeService attendeeService;
    
    private final SpeakerService speakerService;
	
	public List<Talk> allTalks(DataFetchingEnvironment dfEnvt){

		return talkService.findAll();
	}
	
	public List<Attendee> allAttendees(){
		return attendeeService.findAll();
	}
	
	public List<Speaker> allSpeakers(){
		return speakerService.findAll();
	}
	
	public List<Object> allAll(){
		List listOne = speakerService.findAll();
		List listTwo = talkService.findAll();
		listTwo.addAll(listOne);
		return listTwo;
	}
	
	public List<Object> allHumans(){
		List listOne = speakerService.findAll();
		List listTwo = attendeeService.findAll();
		listTwo.addAll(listOne);
		return listTwo;
	}
}
