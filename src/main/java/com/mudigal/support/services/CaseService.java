package com.mudigal.support.services;

import java.util.Collection;

import com.mudigal.support.managedObjects.CaseMO;
import com.mudigal.support.managedObjects.UpdateCaseMO;

public interface CaseService {
	
	public Collection<CaseMO> getCaseByParam(String status, String commentBody, String createdDate);
	public Integer updateCaseByfield(UpdateCaseMO updateCaseMO);

}
