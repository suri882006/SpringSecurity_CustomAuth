package com.fourbits.interview.service;

import java.util.List;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;

public interface InterviewResource {
	
	@Secured("ROLE_SUPERVISOR")
	public List<String> getSpringResource();

}
