package com.achhabra.finworld.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.achhabra.finworld.constants.CashFlowTypeEnum;
import com.achhabra.finworld.model.CashFlowType;
import com.achhabra.finworld.model.Frequency;
import com.achhabra.finworld.util.ConnectionUtil;

@Repository
public class CashFlowTypeDaoImpl implements CashFlowTypeDao {

	@Override
	public List<CashFlowType> getAllCashFlowType() {
		List<CashFlowType> list = new ArrayList<CashFlowType>();
		Session session= ConnectionUtil.getSessionFactory().openSession();
		try{
		Query query = session.createQuery("from CashFlowType t ");		
		list = query.list();
		}finally{
		session.close();
		}
		return list;
	}

	@Override
	public CashFlowType getCashFlowTypeByCode(String code) {
		List<CashFlowType> list = new ArrayList<CashFlowType>();
		Session session= ConnectionUtil.getSessionFactory().openSession();
		try{
		Query query = session.createQuery("from CashFlowType t where t.code = :code");
		query.setParameter("code", code);
		list = query.list();
		if (list.size() > 0)
			return list.get(0);
		else
			return null;

		}finally{
		session.close();
		}
	}

	@Override
	public CashFlowType getCashFlowTypeById(int id) {
		List<CashFlowType> list = new ArrayList<CashFlowType>();
		Session session= ConnectionUtil.getSessionFactory().openSession();
		try{
		Query query = session.createQuery("from CashFlowType t where t.id = :id");
		query.setParameter("id", id);
		list = query.list();
		if (list.size() > 0)
			return list.get(0);
		else
			return null;

		}finally{
		session.close();
		}
	}

	@Override
	public List<CashFlowType> getAllActiveCashFlowType() {
		List<CashFlowType> frequencyList = new ArrayList<CashFlowType>();
		Session session= ConnectionUtil.getSessionFactory().openSession();
		try{
		Query query = session.createQuery("from CashFlowType t where t.isActive = true");		
		frequencyList = query.list();
		}finally{
		session.close();
		}
		return frequencyList;
	}

	@Override
	public CashFlowType getActiveCashFlowTypeByCode(String code) {
		List<CashFlowType> list = new ArrayList<CashFlowType>();
		Session session= ConnectionUtil.getSessionFactory().openSession();
		try{
		Query query = session.createQuery("from CashFlowType t where t.isActive = :active and t.code = :code");
		query.setParameter("code", code);
		query.setParameter("active", true);
		list = query.list();
		if (list.size() > 0)
			return list.get(0);
		else
			return null;

		}finally{
		session.close();
		}
	}

	@Override
	public CashFlowType getActiveCashFlowTypeById(int id) {
		List<CashFlowType> list = new ArrayList<CashFlowType>();
		Session session= ConnectionUtil.getSessionFactory().openSession();
		try{
		Query query = session.createQuery("from CashFlowType t where t.isActive = :active and t.id = :id");
		query.setParameter("active", true);
		query.setParameter("id", id);
		list = query.list();
		if (list.size() > 0)
			return list.get(0);
		else
			return null;

		}finally{
		session.close();
		}
	}

	@Override
	public List<CashFlowType> getActiveCashFlowTypeByType(String cashFlowType) {
		List<CashFlowType> list = new ArrayList<CashFlowType>();
		Session session= ConnectionUtil.getSessionFactory().openSession();
		try{
		Query query = session.createQuery("from CashFlowType t where t.isActive = :active and t.cashFlowType = :type");
		//	Query query = session.createQuery("from CashFlowType t where t.isActive = :active ");
		query.setParameter("active", true);
		query.setParameter("type", cashFlowType);
		list = query.list();
		return list;

		}finally{
		session.close();
		}
	}
}
