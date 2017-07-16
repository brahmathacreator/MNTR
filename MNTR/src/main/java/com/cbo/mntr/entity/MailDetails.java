package com.cbo.mntr.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "MAIL_DETAILS")
public class MailDetails implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MAIL_REF_ID", nullable = false, unique = true)
	private Integer mailRefId;

	@Column(name = "FROM_ADDRESS", nullable = false)
	private String fromEmailAddr;

	@Column(name = "TO_ADDRESS", nullable = false)
	private String toEmailAddr;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "SENT_DT", nullable = false)
	private Date sentDT;

	@Column(name = "DELIVERY_STATUS", nullable = false)
	private String deliveryStatus;

	public Integer getMailRefId() {
		return mailRefId;
	}

	public void setMailRefId(Integer mailRefId) {
		this.mailRefId = mailRefId;
	}

	public String getFromEmailAddr() {
		return fromEmailAddr;
	}

	public void setFromEmailAddr(String fromEmailAddr) {
		this.fromEmailAddr = fromEmailAddr;
	}

	public String getToEmailAddr() {
		return toEmailAddr;
	}

	public void setToEmailAddr(String toEmailAddr) {
		this.toEmailAddr = toEmailAddr;
	}

	public Date getSentDT() {
		return sentDT;
	}

	public void setSentDT(Date sentDT) {
		this.sentDT = sentDT;
	}

	public String getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

}
