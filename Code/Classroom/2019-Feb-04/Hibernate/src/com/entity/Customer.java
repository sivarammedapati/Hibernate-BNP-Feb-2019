package com.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author anilj
 *
 *	Customer
 *		id: int PK
 *		name: varchar
 *		createDate: date
 *		city
 *		state
 *
 */

@Entity
@Table
@NamedQuery(name="fetchByCity", query="from Customer as c where c.address.city= :city")
public class Customer {

	@Id @Column @GeneratedValue( strategy=GenerationType.IDENTITY)
	private int id;
	
	@Basic @Column
	private String name;
	
	@Temporal(TemporalType.DATE)
	@Column
	private Date createdDate;
	
	/*Value Type*/
	@Embedded //optional
	private Address address;
	
	/*Value Type Collection*/
	@ElementCollection(fetch=FetchType.LAZY)
	@CollectionTable(name="CUST_EMAILS", joinColumns= {@JoinColumn(name="CUSTID")})
	@Column(name="EMAIL_ADDRESS")
	private Set<String> emails = new HashSet<>();
	
	/*Entity mapping One to Many*/
	
	
	/*@OneToMany(targetEntity=Account.class, cascade=CascadeType.ALL, 
							mappedBy="customer", fetch=FetchType.LAZY)*/
	
	/*@OneToMany(targetEntity=Account.class, cascade=CascadeType.ALL)
	@JoinColumn(name="CUST_ID")*/
	@OneToMany(targetEntity=Account.class, cascade=CascadeType.ALL, 
			mappedBy="customer", fetch=FetchType.LAZY)
	private Set<Account> accounts = new HashSet<>();

	public Customer() {
	
	}

	public Customer(String name, Date createdDate, Address address) {
		
		this.name = name;
		this.createdDate = createdDate;
		this.address = address;
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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	public Set<String> getEmails() {
		return emails;
	}

	public void setEmails(Set<String> emails) {
		this.emails = emails;
	}

	public Set<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(Set<Account> accounts) {
		this.accounts = accounts;
	}
	
}







