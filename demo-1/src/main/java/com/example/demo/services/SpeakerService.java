package com.example.demo.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.databasemodels.Speaker;
import com.example.demo.databasemodels.SpeakerTalk;
import com.example.demo.databasemodels.Talk;
import com.example.demo.interfaces.IService;
import com.example.demo.repositories.SpeakerRepository;
import com.example.demo.repositories.SpeakerTalkRepository;

@Service
public class SpeakerService implements IService<Speaker>{

	@Autowired
	private SpeakerRepository speakerRepository;
	
	@Autowired
	private SpeakerTalkRepository speakerTalkRepository;
	
	@Override
	public List<Speaker> findAll() {

		return speakerRepository.findAll();
	}
	
	public List<Speaker> findAllSpeakersForTalk(Talk talk){
		List<SpeakerTalk> speakerTalks = speakerTalkRepository.findAllByTalkId(talk.getId());
		
		return speakerTalks.stream().map(s -> speakerRepository.findById(s.getSpeakerId()))
				.filter(Optional::isPresent)
                .map(Optional::get)
				.collect(Collectors.toList());
	}

}
