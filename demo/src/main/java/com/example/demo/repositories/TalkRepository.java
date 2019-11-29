package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.databasemodels.Talk;
@Repository
public interface TalkRepository extends JpaRepository<Talk, Long>{

}
