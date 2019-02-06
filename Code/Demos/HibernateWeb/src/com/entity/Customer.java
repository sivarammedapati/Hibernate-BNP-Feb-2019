package com.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.types.Address;

@Entity
@Table(name="customer")
public class Customer {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private int id;
	
	@Basic @Column
	private String name;
	
	@Temporal(TemporalType.DATE)
	@Column
	private Date createdDate;
	
	@Embedded
	private Address billingAddress;
	
	@ElementCollection
	@CollectionTable(name="cust_emails", joinColumns={@JoinColumn(name="custId")})
	@Column(name="email_address")
	private Set<String> emails = new HashSet<>();
	
	

	public Customer() {
		
	}

	public Customer(String name, Date createdDate, Address billingAddress) {
		
		this.name = name;
		this.createdDate = createdDate;
		this.billingAddress = billingAddress;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Address getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}
	public Set<String> getEmails() {
		return emails;
	}

	public void setEmails(Set<String> emails) {
		this.emails = emails;
	}
	
}
