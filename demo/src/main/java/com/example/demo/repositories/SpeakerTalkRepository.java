package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.databasemodels.SpeakerTalk;
@Repository
public interface SpeakerTalkRepository extends JpaRepository<SpeakerTalk, Long>{
	List<SpeakerTalk> findAllBySpeakerId(Long speakerId);
	List<SpeakerTalk> findAllByTalkId(Long talkId);

}
