/**
 * 
 */
package com.achhabra.finworld.dao;

import java.util.List;

import com.achhabra.finworld.model.Frequency;

/**
 * @author achhabra
 *
 */
public interface FrequencyDao {

	public List<Frequency> getAllFequencies();
	
	public Frequency getFrequencyByCode(String Code);
	
	public Frequency getFrequencyById(int id);

	public List<Frequency> getAllActiveFequencies();
	
	public Frequency getActiveFrequencyByCode(String Code);
	
	public Frequency getActiveFrequencyById(int id);

}
