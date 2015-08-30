package com.mudigal.support.services;

import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mudigal.support.domains.CaseCommentsEntity;
import com.mudigal.support.domains.CaseEntity;
import com.mudigal.support.managedObjects.CaseMO;
import com.mudigal.support.managedObjects.UpdateCaseMO;

@SuppressWarnings("restriction")
@Service("caseService")
@Transactional
public class CaseServiceImpl implements CaseService {
	
protected static Logger logger = Logger.getLogger("Case Service");
	
	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;

	public Collection<CaseMO> getCaseByParam(String status, String commentBody, String date) {
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		String queryString = "SELECT c.CASE_ID, c.STATUS, cc.BODY, c.CREATED_DATE FROM CASES c, CASE_COMMENTS cc, CASES_CASE_COMMENTS ccc WHERE (cc.ID = ccc.comments_ID AND ccc.CASES_CASE_ID = c.CASE_ID) AND (cc.BODY LIKE '%" + commentBody + "%' OR c.STATUS LIKE '%"+ status +"%' OR c.CREATED_DATE like '"+ date +"%')";		
		Query query = session.createSQLQuery(queryString);
		
		@SuppressWarnings("unchecked")
		List<CaseMO> list = query.list();
		
		return list;
	}

	public Integer updateCaseByfield(UpdateCaseMO updateCaseMO) {
		Integer status = 0;
		
		logger.debug("Editing existing case");
		
		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();
		
		int start = updateCaseMO.getStartId();
		int end = updateCaseMO.getEndId();
		String field = updateCaseMO.getField();
		String value = updateCaseMO.getValue();
		
		logger.debug("Field:" + field + "\nValue:" + value);
		
		for(int caseId = start; caseId <= end; caseId++){
			
			// Save updates
			Object obj = null;
			
			// Retrieve existing case by id
			CaseEntity existingCase = (CaseEntity) session.get(CaseEntity.class, caseId);
			
			// Assign updated values to this case
			if(field.equals("status")) {
				existingCase.setStatus(value.split(",")[0]);
				obj = session.save(existingCase);
				if(obj != null && status != -1)
					status++;
				else
					status = -1;
			} else if(field.equals("comment")) {
				Collection<CaseCommentsEntity> comments = existingCase.getComments();
				for(CaseCommentsEntity caseCommentsEntity: comments) {
					caseCommentsEntity.setBody(value.split(",")[1]);
					obj = session.save(caseCommentsEntity);
					if(obj != null && status != -1)
						status++;
					else
						status = -1;
				}
			}
		}
		return status;
	}
}
