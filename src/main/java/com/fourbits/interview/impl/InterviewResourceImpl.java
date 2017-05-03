package com.fourbits.interview.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import com.fourbits.interview.service.InterviewResource;

@Component
public class InterviewResourceImpl implements InterviewResource {

	
	@Override
	public List<String> getSpringResource() {
		return new ArrayList<String>(Arrays.asList("spring1", "Spring2"));
		
	}

}
