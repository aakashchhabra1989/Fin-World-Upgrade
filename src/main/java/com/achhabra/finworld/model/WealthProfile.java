package com.achhabra.finworld.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.achhabra.finworld.util.StaticConstants;

@Entity
@Table(name=StaticConstants.DB_PREFIX+"wealth_profile")
public class WealthProfile {
	
	@Id
	@GeneratedValue
	private Integer id;

	@Column(unique = true)
	private String profileId;
	
	@Column(nullable = true)
	private String profileName;
	
	@Column(nullable = true)
	private String profileDescription;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@OneToMany(mappedBy="wealthProfile", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	private Set<WealthProfileCashFlow> wealthProfileCashFlows;
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the profileId
	 */
	public String getProfileId() {
		return profileId;
	}

	/**
	 * @param profileId the profileId to set
	 */
	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}

	/**
	 * @return the wealthProfileCashFlows
	 */
	public Set<WealthProfileCashFlow> getWealthProfileCashFlows() {
		return wealthProfileCashFlows;
	}

	/**
	 * @param wealthProfileCashFlows the wealthProfileCashFlows to set
	 */
	public void setWealthProfileCashFlows(
			Set<WealthProfileCashFlow> wealthProfileCashFlows) {
		this.wealthProfileCashFlows = wealthProfileCashFlows;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the profileName
	 */
	public String getProfileName() {
		return profileName;
	}

	/**
	 * @param profileName the profileName to set
	 */
	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	/**
	 * @return the profileDescription
	 */
	public String getProfileDescription() {
		return profileDescription;
	}

	/**
	 * @param profileDescription the profileDescription to set
	 */
	public void setProfileDescription(String profileDescription) {
		this.profileDescription = profileDescription;
	}

	
}
