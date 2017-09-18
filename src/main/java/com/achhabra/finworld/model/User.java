package com.achhabra.finworld.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.achhabra.finworld.util.StaticConstants;

@Entity(name = "User")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="Type" ,discriminatorType=DiscriminatorType.STRING)
@DiscriminatorValue(value=StaticConstants.USER)
@Table(name = "User")
public class User {
	
	@Id
	@Column(nullable=false)
	@GeneratedValue
	private long id;
	
	@Column(unique=true, nullable=false)
	private String userId;
		
	@OneToOne(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name="user_roles",
		joinColumns = {@JoinColumn(name="user_id", referencedColumnName="id")},
		inverseJoinColumns = {@JoinColumn(name="role_id", referencedColumnName="id")}
	)
	private Role role;

	@OneToMany(mappedBy="user", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	private Set<WealthProfile> wealthProfiles;
	
	private String password;
	
	private String userDetail;
		
	private boolean isActive;
	
	private Date lastActiveDate;	
	
	private byte[] profileImage;
	
	private String primaryPhoneNo;
	
	private String secondaryPhoneNo;
	
	private String primaryEmailId;
	
	private String secondaryEmailId;
	
	private String createdBy;
	
	private String updatedBy;
	
	private Date createdDate;
	
	private Date updatedDate;

	private String systemOfRecordsX;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the userDetail
	 */
	public String getUserDetail() {
		return userDetail;
	}

	/**
	 * @param userDetail the userDetail to set
	 */
	public void setUserDetail(String userDetail) {
		this.userDetail = userDetail;
	}

	/**
	 * @return the isActive
	 */
	public boolean isActive() {
		return isActive;
	}

	/**
	 * @param isActive the isActive to set
	 */
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}


	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the updatedBy
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}

	/**
	 * @param updatedBy the updatedBy to set
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the updatedDate
	 */
	public Date getUpdatedDate() {
		return updatedDate;
	}

	/**
	 * @param updatedDate the updatedDate to set
	 */
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	/**
	 * @return the systemOfRecordsX
	 */
	public String getSystemOfRecordsX() {
		return systemOfRecordsX;
	}

	/**
	 * @param systemOfRecordsX the systemOfRecordsX to set
	 */
	public void setSystemOfRecordsX(String systemOfRecordsX) {
		this.systemOfRecordsX = systemOfRecordsX;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the lastActiveDate
	 */
	public Date getLastActiveDate() {
		return lastActiveDate;
	}

	/**
	 * @param lastActiveDate the lastActiveDate to set
	 */
	public void setLastActiveDate(Date lastActiveDate) {
		this.lastActiveDate = lastActiveDate;
	}

	/**
	 * @return the profileImage
	 */
	public byte[] getProfileImage() {
		return profileImage;
	}

	/**
	 * @param profileImage the profileImage to set
	 */
	public void setProfileImage(byte[] profileImage) {
		this.profileImage = profileImage;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	/**
	 * @return the primaryPhoneNo
	 */
	public String getPrimaryPhoneNo() {
		return primaryPhoneNo;
	}

	/**
	 * @param primaryPhoneNo the primaryPhoneNo to set
	 */
	public void setPrimaryPhoneNo(String primaryPhoneNo) {
		this.primaryPhoneNo = primaryPhoneNo;
	}

	/**
	 * @return the secondaryPhoneNo
	 */
	public String getSecondaryPhoneNo() {
		return secondaryPhoneNo;
	}

	/**
	 * @param secondaryPhoneNo the secondaryPhoneNo to set
	 */
	public void setSecondaryPhoneNo(String secondaryPhoneNo) {
		this.secondaryPhoneNo = secondaryPhoneNo;
	}

	/**
	 * @return the primaryEmailId
	 */
	public String getPrimaryEmailId() {
		return primaryEmailId;
	}

	/**
	 * @param primaryEmailId the primaryEmailId to set
	 */
	public void setPrimaryEmailId(String primaryEmailId) {
		this.primaryEmailId = primaryEmailId;
	}

	/**
	 * @return the secondaryEmailId
	 */
	public String getSecondaryEmailId() {
		return secondaryEmailId;
	}

	/**
	 * @param secondaryEmailId the secondaryEmailId to set
	 */
	public void setSecondaryEmailId(String secondaryEmailId) {
		this.secondaryEmailId = secondaryEmailId;
	}

	/**
	 * @return the wealthProfiles
	 */
	public Set<WealthProfile> getWealthProfiles() {
		return wealthProfiles;
	}

	/**
	 * @param wealthProfiles the wealthProfiles to set
	 */
	public void setWealthProfiles(Set<WealthProfile> wealthProfiles) {
		this.wealthProfiles = wealthProfiles;
	}	
	
}
