package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.databasemodels.Speaker;
@Repository
public interface SpeakerRepository extends JpaRepository<Speaker, Long>{

}
