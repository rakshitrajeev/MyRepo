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
public class AttendeeTalk {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	private Long attendeeId;
	private Long talkId;
	/*
	 * public Long getAttendeeId() { return attendeeId; } public void
	 * setAttendeeId(Long attendeeId) { this.attendeeId = attendeeId; } public Long
	 * getTalkId() { return talkId; } public void setTalkId(Long talkId) {
	 * this.talkId = talkId; }
	 */

}
