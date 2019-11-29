package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.databasemodels.Talk;
import com.example.demo.interfaces.IService;
import com.example.demo.repositories.TalkRepository;

@Service
public class TalkService implements IService<Talk>{

	@Autowired
	private TalkRepository talkRepository;
	
	@Override
	public List<Talk> findAll() {

		return talkRepository.findAll();
	}
}