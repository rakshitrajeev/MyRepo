package com.example.demo.services;

import org.springframework.stereotype.Service;

import com.example.demo.Interfaces.IService;
import com.example.demo.databasemodels.Attendee;
import com.example.demo.databasemodels.AttendeeTalk;
import com.example.demo.databasemodels.Talk;
import com.example.demo.repositories.AttendeeRepository;
import com.example.demo.repositories.AttendeeTalkRepository;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AttendeeService implements IService<Attendee>{
	@Autowired
    private AttendeeRepository attendeeRepository;

    @Autowired
    private AttendeeTalkRepository attendeeTalkRepository;

    public List<Attendee> findAll() {
        return attendeeRepository.findAll();
    }

    public List<Attendee> findAllAttendiesForTalk(Talk talk) {
        List<AttendeeTalk> at = attendeeTalkRepository.findAllByTalkId(talk.getId());

        return at.stream()
                .map(e -> attendeeRepository.findById(e.getAttendeeId()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

}
