package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.databasemodels.Attendee;
import com.example.demo.databasemodels.AttendeeTalk;
@Repository
public interface AttendeeTalkRepository extends JpaRepository<AttendeeTalk, Long>{

	List<AttendeeTalk> findAllByAttendeeId(Long id);

	List<AttendeeTalk> findAllByTalkId(Long id);

}
