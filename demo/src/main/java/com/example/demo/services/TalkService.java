package com.example.demo.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Interfaces.IService;
import com.example.demo.databasemodels.Attendee;
import com.example.demo.databasemodels.AttendeeTalk;
import com.example.demo.databasemodels.Speaker;
import com.example.demo.databasemodels.SpeakerTalk;
import com.example.demo.databasemodels.Talk;
import com.example.demo.repositories.AttendeeTalkRepository;
import com.example.demo.repositories.SpeakerTalkRepository;
import com.example.demo.repositories.TalkRepository;

@Service
public class TalkService implements IService<Talk>{
	@Autowired
    private TalkRepository talkRepository;

    @Autowired
    private SpeakerTalkRepository speakerTalkRepository;

    @Autowired
    private AttendeeTalkRepository attendeeTalkRepository;

    public List<Talk> findAll() {
        return talkRepository.findAll();
    }

    public List<Talk> findAllTalksBySpeaker(Speaker speaker) {
        List<SpeakerTalk> st = speakerTalkRepository.findAllBySpeakerId(speaker.getId());

        return st.stream()
                .map(e -> talkRepository.findById(e.getTalkId()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    public List<Talk> findAllTalksByAttendee(Attendee attendee) {
        List<AttendeeTalk> st = attendeeTalkRepository.findAllByAttendeeId(attendee.getId());

        return st.stream()
                .map(e -> talkRepository.findById(e.getTalkId()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());

    }

}
