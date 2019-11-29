package com.example.demo.databasemodels;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
public class SpeakerTalk {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	private Long speakerId;
	private Long talkId;
	/*
	 * public Long getSpeakerId() { return speakerId; } public void
	 * setSpeakerId(Long speakerId) { this.speakerId = speakerId; } public Long
	 * getTalkId() { return talkId; } public void setTalkId(Long talkId) {
	 * this.talkId = talkId; }
	 */

}
