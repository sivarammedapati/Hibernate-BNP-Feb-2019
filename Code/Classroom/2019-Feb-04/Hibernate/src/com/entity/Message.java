package com.entity;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

/**
 * 
 * @author anilj
 *
 *	Message
 *		id: int primary key
 *		text: varchar
 *		description: varchar
 *		nextMessageId: int (FK)
 *
 */

@Entity //required
@Table(name="message")  //optional
@OptimisticLocking(type=OptimisticLockType.VERSION)
@DynamicUpdate
public class Message {

	@Column(name="id") //optional
	@Id 				// 	required
	private int id;
	
	@Column
	@Version
	private int version;
	
	@Column @Basic // optional
	private String text;
	
	@Column @Basic
	private String description;
	
	@ManyToOne(targetEntity=Message.class, cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="nextMessageId")
	private Message nextMessage;
	
	public Message() {
		
	}
	public Message(int id, String text, String description) {
		super();
		this.id = id;
		this.text = text;
		this.description = description;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Message getNextMessage() {
		return nextMessage;
	}
	public void setNextMessage(Message nextMessage) {
		this.nextMessage = nextMessage;
	}
	
	
}
