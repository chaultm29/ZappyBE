package com.example.springboot.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.springboot.entities.AWSConfigEntity;
import com.example.springboot.repositories.AWSConfigRepository;

@Service
public class AWSConfigService {

	@Autowired
	AWSConfigRepository awsConfigRepository;
	
	public List<AWSConfigEntity> getAWSConfig(){
		return awsConfigRepository.findAll();	
	}
}
