package com.achhabra.finworld.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.achhabra.finworld.model.Frequency;
import com.achhabra.finworld.util.ConnectionUtil;

@Repository
public class FrequencyDaoImpl implements FrequencyDao {

	@Override
	public List<Frequency> getAllFequencies() {
		List<Frequency> frequencyList = new ArrayList<Frequency>();
		Session session= ConnectionUtil.getSessionFactory().openSession();
		try{
		Query query = session.createQuery("from Frequency f ");		
		frequencyList = query.list();
		}finally{
		session.close();
		}
		return frequencyList;
	}

	@Override
	public Frequency getFrequencyByCode(String code) {
		List<Frequency> frequencyList = new ArrayList<Frequency>();
		Session session= ConnectionUtil.getSessionFactory().openSession();
		try{
		Query query = session.createQuery("from Frequency f where f.code = :code");
		query.setParameter("code", code);
		frequencyList = query.list();
		if (frequencyList.size() > 0)
			return frequencyList.get(0);
		else
			return null;

		}finally{
		session.close();
		}
	}

	@Override
	public Frequency getFrequencyById(int id) {
		List<Frequency> frequencyList = new ArrayList<Frequency>();
		Session session= ConnectionUtil.getSessionFactory().openSession();
		try{
		Query query = session.createQuery("from Frequency f where f.id = :id");
		query.setParameter("id", id);
		frequencyList = query.list();
		if (frequencyList.size() > 0)
			return frequencyList.get(0);
		else
			return null;

		}finally{
		session.close();
		}
	}

	@Override
	public List<Frequency> getAllActiveFequencies() {
		List<Frequency> frequencyList = new ArrayList<Frequency>();
		Session session= ConnectionUtil.getSessionFactory().openSession();
		try{
			
		Query query = session.createQuery("from Frequency f where f.isActive = :active");
		query.setParameter("active", true);
		frequencyList = query.list();
		}finally{
		session.close();
		}
		return frequencyList;
	}

	@Override
	public Frequency getActiveFrequencyByCode(String Code) {
		List<Frequency> frequencyList = new ArrayList<Frequency>();
		Session session= ConnectionUtil.getSessionFactory().openSession();
		try{
		Query query = session.createQuery("from Frequency f where f.isActive = :active and f.code = :code");
		query.setParameter("code", Code);
		query.setParameter("active", true);
		frequencyList = query.list();
		if (frequencyList.size() > 0)
			return frequencyList.get(0);
		else
			return null;

		}finally{
		session.close();
		}
	}

	@Override
	public Frequency getActiveFrequencyById(int id) {
		List<Frequency> frequencyList = new ArrayList<Frequency>();
		Session session= ConnectionUtil.getSessionFactory().openSession();
		try{
		Query query = session.createQuery("from Frequency f where f.isActive = :active and f.id = :id");
		query.setParameter("id", id);
		query.setParameter("active", true);
		frequencyList = query.list();
		if (frequencyList.size() > 0)
			return frequencyList.get(0);
		else
			return null;

		}finally{
		session.close();
		}
	}

}
