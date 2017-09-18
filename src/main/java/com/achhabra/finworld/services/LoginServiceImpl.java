package com.achhabra.finworld.services;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.achhabra.finworld.constants.CashFlowTypeEnum;
import com.achhabra.finworld.model.CashFlowType;
import com.achhabra.finworld.model.ServiceConsumer;
import com.achhabra.finworld.util.ConnectionUtil;
@Service
public class LoginServiceImpl implements LoginService
 {
	
	public boolean authenticateCredentials(String userId,String password){
		Session session =null;
		try{
		String queryString = "from User where userid = ?";
		session = ConnectionUtil.getSessionFactory().openSession();
		Transaction transaction = session.beginTransaction();
		ServiceConsumer user = new ServiceConsumer();
		Query query=session.createQuery(queryString);
		query.setParameter(0, userId);
		List list = query.list();
		transaction.commit();
		
		if(list.size()>0){
			return true;
		}
		return false;
		}finally{
			if(session!=null){
    			try{
    				session.close();
    			}catch(Exception exception){
    				System.out.println(exception.getMessage());
    			}
			}			
		}
	}

}
