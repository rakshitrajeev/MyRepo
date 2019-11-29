package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.databasemodels.Attendee;
import com.example.demo.interfaces.IService;
import com.example.demo.repositories.AttendeeRepository;
@Service
public class AttendeeService implements IService<Attendee>{

	@Autowired
	private AttendeeRepository attendeeRepository;
	
	@Override
	public List<Attendee> findAll() {

		return attendeeRepository.findAll();
	}

}
